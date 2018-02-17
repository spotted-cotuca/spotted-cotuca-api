package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import static io.yawp.repository.Yawp.yawp;

public class AdminUtils {
    public static boolean isAdmin(String email) {
        try {
            return (yawp(Admin.class).where(
                    "email", "=", email).first() != null || email.equals("lorenzopincinato@gmail.com"));

        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
