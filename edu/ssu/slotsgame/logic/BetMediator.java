package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.logic.BetManager;
import edu.ssu.slotsgame.config.GameParameters;
import edu.ssu.slotsgame.ui.BetButton;
import edu.ssu.slotsgame.ui.BetLabel;

public class BetMediator{
    private BetButton betUp;
    private BetButton betDown;
    private BetLabel betLabel;

    public BetMediator(){
    }
    
    public void setBetUpButton(BetButton betUp){
        this.betUp = betUp;
    }
    
    public void setBetDownButton(BetButton betDown){
        this.betDown = betDown;
    }

    public void setBetLabel(BetLabel betLabel){
        this.betLabel =  betLabel;
    }

    public void notifyOfBet(){
        int currentBet = BetManager.getInstance().getCurrentBet();
        betLabel.setText("Bet: " + currentBet);

        if(currentBet >= GameParameters.MAX_BET){
            this.betUp.setEnabled(false);
        }else if(currentBet <= GameParameters.MIN_BET){
            this.betDown.setEnabled(false);
        }else{
            this.betUp.setEnabled(true);
            this.betDown.setEnabled(true);
        }
    }
}