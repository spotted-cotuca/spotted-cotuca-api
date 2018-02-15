package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;
import io.yawp.repository.annotations.Text;
import lombok.Data;

import java.util.Date;

@Data
@Endpoint(path = "/spots")
public class Spot {

    @Id
    private IdRef<Spot> id;

    @Text
    private String message;

    private Date date = new Date();

    private int status = 0; // 0: pending, -1: rejected, 1: approved
}
