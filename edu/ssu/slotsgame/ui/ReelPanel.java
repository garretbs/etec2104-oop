package edu.ssu.slotsgame.ui;

import javax.swing.*;
import java.awt.*;

import edu.ssu.slotsgame.logic.ReelController;

public class ReelPanel extends JPanel{

    public ReelController reelController_;
    private static ReelPanel instance_;

    public ReelPanel(LayoutManager layout){
        super(layout);
        instance_ = this;
        reelController_ = new ReelController();
    }
    
    public static ReelPanel getInstance(){
        return instance_;
    }

    public void paint(Graphics g){
        g.setClip(reelController_.reelX, reelController_.reelY,
                reelController_.reelStrips_[0].symbolWidth * 3,
                reelController_.reelStrips_[0].symbolHeight * 3);

        for(int i=0;i<3;i++){
            reelController_.reelStrips_[i].paint(reelController_.reelX, reelController_.reelY, g);
        }
    }
}