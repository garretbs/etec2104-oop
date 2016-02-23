package edu.ssu.slotsgame.logic;

public class StateEvent extends GameEvent{
    private static int state_;
    private static int prevState_;
    
    public StateEvent(int state){
        super(state);
        state_ = state;
    }
    
    public static int getState(){
        return state_;
    }
    
    public static int getPrevState(){
        return prevState_;
    }
    
}