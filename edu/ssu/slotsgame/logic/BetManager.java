package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.config.GameParameters;

public class BetManager{
    private final static BetManager instance_ = new BetManager();
    private int currentBet_;

    public static BetManager getInstance(){
        return instance_;
    }

    private BetManager(){
        currentBet_ = GameParameters.MIN_BET;
    }

    public int getCurrentBet(){
        return currentBet_;
    }

    public void increaseBet(){
        if(validateBet(currentBet_ + 1)){
            currentBet_++;
            updateBetButtons();
        }
    }

    public void decreaseBet(){
        if(validateBet(currentBet_ - 1)){
            currentBet_--;
            updateBetButtons();
        }
    }

    private void updateBetButtons(){
        EventManager.getInstance().notify(new BetEvent(currentBet_));
    }

    private boolean validateBet(int newBet){
        return (newBet <= GameParameters.MAX_BET && newBet >= GameParameters.MIN_BET);
    }
}