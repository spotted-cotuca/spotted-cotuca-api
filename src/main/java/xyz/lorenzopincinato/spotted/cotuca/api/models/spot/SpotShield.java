package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.shields.Shield;
import xyz.lorenzopincinato.spotted.cotuca.api.models.admin.AdminUtils;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.AuthHolder;

import java.util.List;

public class SpotShield extends Shield<Spot> {

    @Override
    public void defaults() {
        String email = AuthHolder.email.get();

        if (email != null && AdminUtils.isAdmin(email))
            allow(true);

        allow(false);
    }

    @Override
    public void create(List<Spot> objects) {
        allow(true);
    }

    @GET("approved")
    public void approved() {
        allow(true);
    }
}
