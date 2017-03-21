package com.matthewchapman.ql.environment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by matt on 20/03/2017.
 */
public class ValueTableObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("change!");
    }
}
