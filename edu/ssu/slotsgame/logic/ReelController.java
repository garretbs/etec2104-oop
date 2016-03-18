
package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.ui.ReelPanel;
import edu.ssu.slotsgame.ui.ReelStrip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ReelController implements Observer, ActionListener{
    
    
    public static ArrayList results_;
    public static int numReels;
    public static int numCells;
    public static int padding;
    
    public ReelStrip[] reelStrips_;
    
    public int reelX;
    public int reelY;
    
    private int delay_;
    private javax.swing.Timer animationTimer_;
    private int numStopped;
    
    public ReelController(){
        EventManager.getInstance().attach(this, GameEvent.CHANGE_STATE);
        EventManager.getInstance().attach(this, GameState.POSTSPIN);
        
        numReels = 3;
        numCells = 3;
        padding = 5;
        reelX = 100;
        reelY = 15;
        
        reelStrips_ = new ReelStrip[numReels];
        for(int i=0;i<numReels;i++){
            reelStrips_[i] = new ReelStrip();
            reelStrips_[i].x += (reelStrips_[i].symbolWidth + padding) * i;
            reelStrips_[i].rotateState = ReelStrip.ROTATE_DOWN;
        }
        
        delay_ = 20;
        animationTimer_ = new javax.swing.Timer(delay_, this);
    }

    
    public void spin(){
        numStopped = 0;
        Random randomizer = new Random();
        results_ = new ArrayList();
        
        for(int i=0;i<numReels;i++){
            reelStrips_[i].stopped = false;
            int t = (randomizer.nextInt(reelStrips_[i].numSymbols_-1)+1);
            reelStrips_[i].position_-=t;
            
            if(reelStrips_[i].position_ < 0)
                reelStrips_[i].position_ += reelStrips_[i].numSymbols_;
            results_.add(reelStrips_[i].symbolList_.get(reelStrips_[i].position_));
            
            reelStrips_[i].timeleft = t*reelStrips_[i].numSymbols_/numCells;
            reelStrips_[i].timeleft += reelStrips_[i].numSymbols_*reelStrips_[i].numSymbols_;
        }
        animationTimer_.start();
    }

    public void stop(){
        animationTimer_.stop();
    }

    public void notify(GameEvent e){
        if(e.getType() == GameState.SPIN){
            this.spin();
        }else if (e.getType() == GameState.POSTSPIN){
            this.stop();
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<3;i++){
            if(!reelStrips_[i].stopped){
                reelStrips_[i].increment(delay_);
                reelStrips_[i].timeleft--;
                if(reelStrips_[i].timeleft == 0){
                    reelStrips_[i].stopped = true;
                    numStopped++;
                }
            }
        }
        ReelPanel.getInstance().paintImmediately(0, 0, ReelPanel.getInstance().getWidth(), ReelPanel.getInstance().getHeight());
        if(numStopped == numReels){
            numStopped = 0;
            GameStateManager.getInstance().setCurrentState(GameState.POSTSPIN);
        }
    }
}
