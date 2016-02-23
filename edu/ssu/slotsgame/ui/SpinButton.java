
package edu.ssu.slotsgame.ui;

import edu.ssu.slotsgame.logic.GameEvent;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.Observer;
import javax.swing.JButton;

public class SpinButton extends JButton implements Observer{
    
    public SpinButton(String name){
        super(name);
    }

    public void notify(GameEvent e) {
        if(e.getType() == GameState.SPIN)
            setEnabled(false);
        else if (e.getType() == GameState.READY) setEnabled(true);
    }
}
