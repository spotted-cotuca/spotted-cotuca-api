package xyz.lorenzopincinato.spotted.cotuca.api.models.admin;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import xyz.lorenzopincinato.spotted.cotuca.api.utils.EndpointTestCase;

public class AdminTest extends EndpointTestCase {

    @Test
    public void testCreate() {
        // TODO Auto-generated method stub
        String json = post("/admins", "{}");
        Admin admin = from(json, Admin.class);

        assertNotNull(admin);
    }

}
