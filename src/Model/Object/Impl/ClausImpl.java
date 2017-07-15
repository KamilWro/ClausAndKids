package Model.Object.Impl;

import Model.Object.Claus;

/**
 * Created by kamil on 04.07.17.
 */

public class ClausImpl extends Person implements Claus {
    public ClausImpl(){
        String name="clous.png";
        img.set(name);
    }

    @Override
    public void randomLocation(){
        monitor.randomLocation(this);
    }
}
