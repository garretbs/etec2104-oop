
package edu.ssu.slotsgame.ui;

import edu.ssu.slotsgame.logic.GameEvent;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.Observer;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.JPanel;

public class ReelPanel extends JPanel implements Observer{
    ReelLabel[] reels_;
    
    public void spin(){
        reels_ = new ReelLabel[5];
        Random randomizer = new Random();
        int randomInt;
        String reelLetter;
        
        for(int i=0;i<reels_.length;i++){
            randomInt = randomizer.nextInt(25) + 65;
            reelLetter = Character.toString((char) randomInt);
            reels_[i] = new ReelLabel(reelLetter);
        }
    }
    
    public ReelPanel(GridBagLayout gbl){
        super(gbl);
    }

    public void notify(GameEvent e) {
        if(e.getType() == GameState.SPIN) spin();
    }
}
