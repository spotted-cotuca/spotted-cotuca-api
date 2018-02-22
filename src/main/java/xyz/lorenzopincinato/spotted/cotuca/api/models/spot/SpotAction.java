package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import com.restfb.*;
import com.restfb.types.GraphResponse;
import io.yawp.commons.http.annotation.GET;
import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.actions.Action;
import xyz.lorenzopincinato.spotted.cotuca.api.fb.FacebookFinals;

import java.util.Collections;
import java.util.List;

public class SpotAction extends Action<Spot> {
    @PUT("approve")
    public void approve(IdRef<Spot> id) {
        Spot spot = id.fetch();
        if (spot.getStatus() != SpotFinals.APPROVED) {
            spot.setStatus(SpotFinals.APPROVED);
            yawp.save(spot);

            FacebookClient facebookClient = new DefaultFacebookClient(FacebookFinals.PAGE_ACCESS_TOKEN, Version.VERSION_2_5);

            try {
                GraphResponse publishMessageResponse =
                        facebookClient.publish("me/feed", GraphResponse.class,
                                Parameter.with("message", "\"" + spot.getMessage() + "\""));


                String postId = publishMessageResponse.getId().split("_")[1];
                System.out.println("Post ID: " + postId);

                spot.setPostId(postId);
                yawp.save(spot);

            } catch (Exception e) {

            }
        }
    }

    @PUT("reject")
    public void reject(IdRef<Spot> id) {
        Spot spot = id.fetch();
        spot.setStatus(SpotFinals.REJECTED);
        yawp.save(spot);
    }

    @GET("approved")
    public List<Spot> approved() {
        try {
            List<Spot> approvedSpotList = yawp(Spot.class).where("status", "=", SpotFinals.APPROVED).list();
            approvedSpotList.sort(new SpotDateComparator());
            return approvedSpotList;
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @GET("rejected")
    public List<Spot> rejected() {
        try {
            List<Spot> rejectedSpotList = yawp(Spot.class).where("status", "=", SpotFinals.REJECTED).list();
            rejectedSpotList.sort(new SpotDateComparator());
            return rejectedSpotList;
        } catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }

    @GET("pending")
    public List<Spot> pending() {
        try {
            List<Spot> pendingSpotList = yawp(Spot.class).where("status", "=", SpotFinals.PENDING).list();
            pendingSpotList.sort(new SpotDateComparator());
            return pendingSpotList;
        } catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }
}
