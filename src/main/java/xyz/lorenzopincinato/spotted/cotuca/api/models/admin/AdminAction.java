package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import io.yawp.commons.http.JsonResponse;
import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.actions.Action;
import xyz.lorenzopincinato.spotted.cotuca.api.fb.FacebookFinals;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.AuthHolder;

public class AdminAction extends Action<Admin> {

    @GET("me")
    public JsonResponse me() {
        String email = AuthHolder.email.get();

        if (email == null || !AdminUtils.isAdmin(email))
            return new JsonResponse("{ \"success\": false }");

        return new JsonResponse("{\"success\":true}");
    }

    @GET("token")
    public JsonResponse token() {
        return new JsonResponse("{\"token\":\"" + FacebookFinals.PAGE_ACCESS_TOKEN + "\"}");
    }
}
