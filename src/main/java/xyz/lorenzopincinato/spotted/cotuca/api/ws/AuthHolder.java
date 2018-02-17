package xyz.lorenzopincinato.spotted.cotuca.api.ws;

public class AuthHolder {
    public static final ThreadLocal<String> email = new ThreadLocal<>();
}