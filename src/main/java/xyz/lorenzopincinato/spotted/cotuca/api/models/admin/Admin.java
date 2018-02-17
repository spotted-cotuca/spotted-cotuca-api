package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;
import io.yawp.repository.annotations.Index;
import lombok.Data;

@Data
@Endpoint(path = "/admins")
public class Admin {

    @Id
    private IdRef<Admin> id;

    @Index
    private String email;
}
