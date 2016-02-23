package edu.ssu.slotsgame.ui;

import javax.swing.*;

import edu.ssu.slotsgame.config.GameParameters;

import edu.ssu.slotsgame.logic.EventManager;
import edu.ssu.slotsgame.logic.GameEvent;
import edu.ssu.slotsgame.logic.BetEvent;
import edu.ssu.slotsgame.logic.Observer;

public class BetLabel extends JLabel implements Observer{
    public BetLabel(){
        super("Bet: " + GameParameters.MIN_BET);
        EventManager.getInstance().attach(this, GameEvent.CHANGE_BET);
    }

    public void notify(GameEvent e){
        if (e.getType() == GameEvent.CHANGE_BET){
            BetEvent betEvt = (BetEvent)e;
            this.setText("Bet: " + betEvt.getBetAmount());
        }
    }
}