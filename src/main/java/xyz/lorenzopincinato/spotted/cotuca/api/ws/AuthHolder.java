package xyz.lorenzopincinato.spotted.cotuca.api.ws;

import com.google.firebase.auth.FirebaseToken;
import io.yawp.commons.http.HttpException;

public class AuthHolder {
    public static final ThreadLocal<FirebaseToken> token = new ThreadLocal<>();
}