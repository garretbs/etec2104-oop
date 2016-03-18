package edu.ssu.slotsgame.logic;

import javax.swing.JLabel;


public class CreditManager{
    private final static CreditManager instance_ = new CreditManager();

    private int currentCredits_;
    private int currentWins_;
    private JLabel creditLabel_;
    private JLabel wonLabel_;

    public static CreditManager getInstance(){
        return instance_;
    }

    private CreditManager(){
        currentCredits_ = 100;
        currentWins_ = 0;
    }

    public int getCurrentCredits(){
        return currentCredits_;
    }

    public void addCredits(int credits){
        currentCredits_+=credits;
    }
    
    public void subCredits(int credits){
        currentCredits_-=credits;
    }
    
    public void setCreditLabel(JLabel creditLabel){
        creditLabel_ = creditLabel;
    }
    
    public int getCurrentWins(){
        return currentWins_;
    }
    
    public void addWins(){
        currentWins_++;
    }
    
    public void setWinLabel(JLabel wonLabel){
        wonLabel_ = wonLabel;
    }
    
    public void evaluate(){
        int multiplier = PayTable.getInstance().multiplier();
        if(multiplier > 0){
            addCredits(BetManager.getInstance().getCurrentBet() * multiplier);
            addWins();
            wonLabel_.setText("Won: " + currentWins_);
        }
        creditLabel_.setText("Credits: " + currentCredits_);
        GameStateManager.getInstance().setCurrentState(GameState.READY);
    }
}