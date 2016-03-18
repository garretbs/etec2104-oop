package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.logic.Subject;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Vector;

public class EventManager implements Subject{
    private final static EventManager instance_ = new EventManager();

    private HashMap<Integer, ArrayList<Observer>> observerMap_;
    private Vector<GameEvent> events_;

    public static EventManager getInstance(){
        return instance_;
    }

    private EventManager(){
        observerMap_ = new HashMap<Integer, ArrayList<Observer>>();
        events_ = new Vector<>();
    }

    public void attach(Observer o, int gameEventType){
        Integer type = new Integer(gameEventType);
        if(!observerMap_.containsKey(type)){
            observerMap_.put(type, new ArrayList<Observer>());
        }
        observerMap_.get(type).add(o);
    }

    public void detach(Observer o, int gameEventType){
        Integer type = new Integer(gameEventType);
        if(observerMap_.containsKey(type)){
            observerMap_.get(type).remove(o);
        }
    }


    public void notify(GameEvent e){
        if(events_.size() != 0){
            events_.add(e);
            return;
        }
        events_.add(e);

        while (events_.size() != 0){
            e = events_.get(0);

            Integer type = new Integer(e.getType());
            if(observerMap_.containsKey(type)){
                ArrayList<Observer> observerList = observerMap_.get(type);
                for(int i = 0; i < observerList.size(); ++i){
                    observerList.get(i).notify(e);
                }
            }
            events_.remove(0);
        }
    }
}
