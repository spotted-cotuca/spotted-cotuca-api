package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import io.yawp.repository.shields.Shield;

public class SpotShield extends Shield<Spot> {

    @Override
    public void defaults() {
        // TODO Auto-generated method stub
        allow();
    }

}
