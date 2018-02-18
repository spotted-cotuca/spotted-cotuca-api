package xyz.lorenzopincinato.spotted.cotuca.api.models.spot;

import java.util.Comparator;

public class SpotDateComparator implements Comparator<Spot> {
    @Override
    public int compare(Spot s1, Spot s2) {
        return s2.getDate().compareTo(s1.getDate());
    }
}