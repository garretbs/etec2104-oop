
package edu.ssu.slotsgame.logic;

public class GameStateManager {
    private final static GameStateManager instance_ = new GameStateManager();
    private static int currentState_ = GameState.READY;
    
    private GameStateManager(){
    }
    
    public static int getCurrentState(){
        return currentState_;
    }
    
    public static void setCurrentState(int state){
        currentState_ = state;
        EventManager.getInstance().notify(new StateEvent(state));
    }
    
    public static GameStateManager getInstance(){
        return instance_;
    }
    
}
