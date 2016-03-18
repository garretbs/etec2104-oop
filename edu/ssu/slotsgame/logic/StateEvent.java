package edu.ssu.slotsgame.logic;

public class StateEvent extends GameEvent{
    private int prevState_;
    private int state_;

    public StateEvent(int prevState, int state){
        super(state);
        prevState_ = prevState;
        state_ = state;
    }

    public int getPrevState(){
        return prevState_;
    }

    public int getState(){
        return state_;
    }
}