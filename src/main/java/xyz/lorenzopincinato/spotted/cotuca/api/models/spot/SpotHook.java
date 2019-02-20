package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import io.yawp.repository.IdRef;
import io.yawp.repository.hooks.Hook;

public class SpotHook extends Hook<Spot> {
    @Override
    public void afterSave(Spot spot) {
    	checkCreation();
    	
    	IdRef<Spot> id = spot.getId();
    	if (id != null)
    		SpotAction.spots.put(id.toString(), spot);
    }
    
    @Override
    public void afterDestroy(IdRef<Spot> id) {
    	checkCreation();
    	SpotAction.spots.remove(id.toString());
    }
    
    public void checkCreation() {
    	if (SpotAction.spots == null) {
    		SpotAction.spots = new HashMap<String, Spot>();
    		SpotAction.LOGGER.log(Level.INFO, "criou");
			
			List<Spot> spotList = null;
			try {
				spotList = yawp(Spot.class).list();
			} catch (Exception e) {
				SpotAction.LOGGER.log(Level.SEVERE, "exceção: " + e);
				spotList = Collections.emptyList();
			}
			
			SpotAction.LOGGER.log(Level.INFO, "spots no banco: " + spotList.size());
			for (Spot fetchedSpot : spotList)
				SpotAction.spots.put(fetchedSpot.getId().toString(), fetchedSpot);
    	}
    }
}