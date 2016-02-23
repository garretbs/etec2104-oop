package edu.ssu.slotsgame.logic;

public class BetEvent extends GameEvent{
    private int betAmount_;
    private int prevBetAmount_;

    public BetEvent(int betAmount){
        super(GameEvent.CHANGE_BET);
        betAmount_ = betAmount;
    }

    public int getBetAmount(){
        return betAmount_;
    }
    
    public int getPrevBetAmount(){
        return prevBetAmount_;
    }
}