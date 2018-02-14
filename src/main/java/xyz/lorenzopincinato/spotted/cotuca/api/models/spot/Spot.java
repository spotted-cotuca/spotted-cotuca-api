package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;

@Endpoint(path = "/spots")
public class Spot {

    @Id
    IdRef<Spot> id;

}
