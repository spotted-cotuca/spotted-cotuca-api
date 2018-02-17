package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import com.google.firebase.auth.FirebaseToken;
import io.yawp.commons.http.HttpException;
import io.yawp.repository.Yawp;
import io.yawp.repository.shields.Shield;
import static io.yawp.repository.Yawp.yawp;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.AuthHolder;

public class AdminShield extends Shield<Admin> {

    @Override
    public void defaults() {
        FirebaseToken token = AuthHolder.token.get();
        if (token == null) {
            throw new HttpException(403, "Must pass a firebase-token header to do that.");
        }

        if (!AdminUtils.isAdmin(token.getEmail())) {
            throw new HttpException(403, "Not authorized");
        }
        allow(true);
    }
}
