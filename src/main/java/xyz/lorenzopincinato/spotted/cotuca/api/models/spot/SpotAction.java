package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.actions.Action;

public class SpotAction extends Action<Spot> {

    @PUT("approve")
    public void approve(IdRef<Spot> id) {
        Spot spot = id.fetch();
        spot.setStatus(1);
        yawp.save(spot);
    }

    @PUT("reject")
    public void reject(IdRef<Spot> id) {
        Spot spot = id.fetch();
        spot.setStatus(-1);
        yawp.save(spot);
    }
}
