package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.commons.http.JsonResponse;
import io.yawp.commons.http.annotation.GET;
import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.actions.Action;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.FirebaseAuthFilter;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SpotAction extends Action<Spot> {
	public final static Logger LOGGER = Logger.getLogger(FirebaseAuthFilter.class.getName());
	public static HashMap<String, Spot> spots = null;
	
    @PUT("addPostId")
    public void addPostId(IdRef<Spot> id, Map<String, String> params) {
    	checkCreation();
    	boolean save = false;
    	
    	Spot spot = SpotAction.spots.get(id.toString());
        if (params.containsKey("fbPostId")) {
            spot.setFbPostId(params.get("fbPostId"));
            save = true;
        }

        if (params.containsKey("ttPostId")) {
            spot.setTtPostId(params.get("ttPostId"));
            save = true;
        }

        if (save) {
        	SpotAction.spots.put(id.toString(), spot);
            yawp.save(spot);
        }
    }

    @PUT("approve")
    public void approve(IdRef<Spot> id) {
    	checkCreation();
    	
        Spot spot = SpotAction.spots.get(id.toString());
        if (spot.getStatus() != SpotFinals.APPROVED) {
            spot.setStatus(SpotFinals.APPROVED);
            SpotAction.spots.put(id.toString(), spot);
            yawp.save(spot);
        }
    }

    @PUT("reject")
    public void reject(IdRef<Spot> id) {
    	checkCreation();
    	
    	Spot spot = SpotAction.spots.get(id.toString());
        spot.setStatus(SpotFinals.REJECTED);
        SpotAction.spots.put(id.toString(), spot);
        yawp.save(spot);
    }

    @GET("approved")
    public List<Spot> approved() {
    	checkCreation();
    	
        try {
            List<Spot> approvedSpotList = new ArrayList<>();
            List<Spot> spots = new ArrayList<Spot>(this.spots.values());
            
            for (Spot spot : spots)
            	if (spot.getStatus() == SpotFinals.APPROVED)
            		approvedSpotList.add(spot);
            
            approvedSpotList.sort(new SpotDateComparator());
            return approvedSpotList;
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @GET("rejected")
    public List<Spot> rejected() {
    	checkCreation();
    	
        try {
        	List<Spot> rejectedSpotList = new ArrayList<>();
            List<Spot> spots = new ArrayList<Spot>(this.spots.values());
            
            for (Spot spot : spots)
            	if (spot.getStatus() == SpotFinals.REJECTED)
            		rejectedSpotList.add(spot);
            
            rejectedSpotList.sort(new SpotDateComparator());
            return rejectedSpotList;
        } catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }

    @GET("pending")
    public List<Spot> pending() {
    	checkCreation();
    	
        try {
        	List<Spot> pendingSpotList = new ArrayList<>();
            List<Spot> spots = new ArrayList<Spot>(this.spots.values());
            
            for (Spot spot : spots)
            	if (spot.getStatus() == SpotFinals.PENDING)
            		pendingSpotList.add(spot);
            
            pendingSpotList.sort(new SpotDateComparator());
            return pendingSpotList;
        } catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }
    
    public void checkCreation() {
    	if (spots == null) {
			spots = new HashMap<String, Spot>();
			LOGGER.log(Level.INFO, "criou");
			
			List<Spot> spotList = null;
			try {
				spotList = yawp(Spot.class).list();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "exceção: " + e);
				spotList = Collections.emptyList();
			}
			
			LOGGER.log(Level.INFO, "spots no banco: " + spotList.size());
			for (Spot spot : spotList)
				this.spots.put(spot.getId().toString(), spot);
		}
    }
}