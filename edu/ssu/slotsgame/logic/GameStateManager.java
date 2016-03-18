package edu.ssu.slotsgame.logic;


public class GameStateManager{
    private final static GameStateManager instance_ = new GameStateManager();

    private int currentState_;

    public static GameStateManager getInstance(){
        return instance_;
    }

    private GameStateManager(){
        currentState_ = GameState.READY;
    }

    public int getCurrentState(){
        return currentState_;
    }

    public void setCurrentState(int state){
        StateEvent stateEvent = new StateEvent(currentState_, state);
        currentState_ = state;
        EventManager.getInstance().notify(stateEvent);
        if(currentState_ == GameState.POSTSPIN) CreditManager.getInstance().evaluate();
    }
}