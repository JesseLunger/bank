package com.solvd.bank.utils.patternsutil;

import java.util.ArrayList;

public class ExampleListeners {

    private ArrayList<ExampleListener> listeners = new ArrayList<>();

    public boolean subscribe(ExampleListener listener){
        return listeners.add(listener);
    }

    public boolean unsubscribe(ExampleListener listener){
        return listeners.remove(listener);
    }

    public void notifyListeners(String message){
        for (ExampleListener listener: listeners){
            listener.onEvent(message);
        }
    }
}