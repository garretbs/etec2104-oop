
package edu.ssu.slotsgame.ui;

import edu.ssu.slotsgame.logic.GameEvent;
import edu.ssu.slotsgame.logic.Observer;
import javax.swing.JButton;

public class BetButton extends JButton implements Observer{
    
    public BetButton(String name){
        super(name);
    }
    
    public void notify(GameEvent e){
    }
}
