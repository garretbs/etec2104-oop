
package edu.ssu.slotsgame.ui;

import edu.ssu.slotsgame.logic.EventManager;
import edu.ssu.slotsgame.logic.GameEvent;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.Observer;
import javax.swing.JButton;

public class SpinButton extends JButton implements Observer{
    
    public SpinButton(String name){
        super(name);
        EventManager.getInstance().attach(this, GameEvent.CHANGE_STATE);
        EventManager.getInstance().attach(this, GameState.READY);
    }

    public void notify(GameEvent e){
        if(e.getType() == GameState.SPIN)
            setEnabled(false);
        else if (e.getType() == GameState.READY) setEnabled(true);
    }
}
