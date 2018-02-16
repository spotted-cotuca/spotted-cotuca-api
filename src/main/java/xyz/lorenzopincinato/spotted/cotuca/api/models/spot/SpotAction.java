package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.commons.http.annotation.GET;
import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.actions.Action;

import java.util.Collections;
import java.util.List;

public class SpotAction extends Action<Spot> {

    @PUT("approve")
    public void approve(IdRef<Spot> id) {
        Spot spot = id.fetch();
        spot.setStatus(SpotUtils.APPROVED);
        yawp.save(spot);
    }

    @PUT("reject")
    public void reject(IdRef<Spot> id) {
        Spot spot = id.fetch();
        spot.setStatus(SpotUtils.REJECTED);
        yawp.save(spot);
    }

    @GET("approved")
    public List<Spot> approved() {
        if (!yawp(Spot.class).where("status", "=", SpotUtils.APPROVED).list().isEmpty())
            return yawp(Spot.class).where("status", "=", SpotUtils.APPROVED).list();
        return Collections.emptyList();
    }

    @GET("rejected")
    public List<Spot> rejected() {
        if (!yawp(Spot.class).where("status", "=", SpotUtils.REJECTED).list().isEmpty())
            return yawp(Spot.class).where("status", "=", SpotUtils.REJECTED).list();
        return Collections.emptyList();
    }

    @GET("pending")
    public List<Spot> pending() {
        if (!yawp(Spot.class).where("status", "=", SpotUtils.PENDING).list().isEmpty())
            return yawp(Spot.class).where("status", "=", SpotUtils.PENDING).list();
        return Collections.emptyList();
    }
}
