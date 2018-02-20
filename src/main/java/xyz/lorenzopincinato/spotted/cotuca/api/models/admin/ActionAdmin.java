package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import io.yawp.commons.http.JsonResponse;
import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.actions.Action;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.AuthHolder;

public class ActionAdmin extends Action<Admin> {

    @GET("me")
    public JsonResponse me() {
        String email = AuthHolder.email.get();

        if (email == null || !AdminUtils.isAdmin(email))
            return new JsonResponse("{ \"Success\": false }");

        return new JsonResponse("{ \"Success\": true }");
    }
}
