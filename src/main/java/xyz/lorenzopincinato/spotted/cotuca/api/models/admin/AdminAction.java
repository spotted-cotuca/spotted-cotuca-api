package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import io.yawp.commons.http.JsonResponse;
import io.yawp.commons.http.annotation.GET;
import io.yawp.repository.actions.Action;
import xyz.lorenzopincinato.spotted.cotuca.api.social.*;
import xyz.lorenzopincinato.spotted.cotuca.api.ws.AuthHolder;

public class AdminAction extends Action<Admin> {

    @GET("me")
    public JsonResponse me() {
        String email = AuthHolder.email.get();

        if (email == null || !AdminUtils.isAdmin(email))
            return new JsonResponse("{ \"success\": false }");

        return new JsonResponse("{\"success\":true}");
    }

    @GET("tokens")
    public JsonResponse tokens() {
        return new JsonResponse(
          "{" +
            "\"fb_token_key\":\"" + FacebookFinals.PAGE_ACCESS_TOKEN + "\"," +
            "\"tt_consumer_key\":\"" + TwitterFinals.CONSUMER_KEY + "\"," +
            "\"tt_consumer_secrety\":\"" + TwitterFinals.CONSUMER_SECRET + "\"," +
            "\"tt_token_key\":\"" + TwitterFinals.ACCESS_TOKEN_KEY + "\"," +
            "\"tt_token_secret\":\"" + TwitterFinals.ACCESS_TOKEN_SECRET + "\"" +
          "}");
    }
}
