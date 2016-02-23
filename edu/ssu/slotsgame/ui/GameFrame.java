package edu.ssu.slotsgame.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import edu.ssu.slotsgame.logic.BetManager;
import edu.ssu.slotsgame.logic.BetMediator;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.GameStateManager;

public class GameFrame extends JFrame implements ActionListener{
    int currentCredits;

    JLabel creditLabel;
    public static BetLabel betLabel;
    JLabel wonLabel;

    JButton helpButton;
    public static BetButton betUpButton;
    public static BetButton betDownButton;
    public static SpinButton spinButton;

    ReelLabel[] reels;
    public static ReelPanel reelPanel;
    
    final static BetMediator mediator = new BetMediator();

    public GameFrame(){
        super("SSU Slots");

        currentCredits = 100;
        creditLabel = new JLabel("Credits: " + currentCredits);

        betLabel = new BetLabel();

        wonLabel = new JLabel("Won: 0");
        wonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel meterPanel = new JPanel(new BorderLayout());
        meterPanel.add(creditLabel, BorderLayout.WEST);
        meterPanel.add(wonLabel, BorderLayout.CENTER);
        meterPanel.add(betLabel, BorderLayout.EAST);

        this.add(meterPanel, BorderLayout.NORTH);

        reelPanel = new ReelPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 50, 0, 50);

        reels = new ReelLabel[5];
        for(int i=0;i<reels.length;i++){
            reels[i] = new ReelLabel("A");
            reelPanel.add(reels[i], gbc);
        }

        this.add(reelPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        helpButton = new JButton("HELP");
        helpButton.addActionListener(this);
        
        spinButton = new SpinButton("SPIN");
        spinButton.addActionListener(this);

        JPanel betButtonPanel = new JPanel();
        betButtonPanel.setLayout(new BoxLayout(betButtonPanel, BoxLayout.Y_AXIS));

        betUpButton = new BetButton("BET UP");
        betUpButton.addActionListener(this);
        mediator.setBetUpButton(betUpButton);

        betDownButton = new BetButton("BET DOWN");
        betDownButton.addActionListener(this);
        mediator.setBetDownButton(betDownButton);

        betButtonPanel.add(betUpButton);
        betButtonPanel.add(betDownButton);

        buttonPanel.add(helpButton, BorderLayout.WEST);
        buttonPanel.add(spinButton, BorderLayout.CENTER);
        buttonPanel.add(betButtonPanel, BorderLayout.EAST);

        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()) {
            case "BET UP":
                BetManager.getInstance().increaseBet();
                mediator.setBetLabel(betLabel);
                mediator.notifyOfBet();
                break;
            case "BET DOWN":
                BetManager.getInstance().decreaseBet();
                mediator.setBetLabel(betLabel);
                mediator.notifyOfBet();
                break;
            case "SPIN":
                GameStateManager.getInstance().setCurrentState(GameState.SPIN);
                reelPanel.removeAll();
                reels = reelPanel.reels_;
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(0, 50, 0, 50);
                for(ReelLabel reel : reels)reelPanel.add(reel, gbc);
                reelPanel.repaint();
                this.setVisible(true);
                GameStateManager.getInstance().setCurrentState(GameState.READY);
                break;
        }
    }
    
    public static void main(String[] args){
        new GameFrame();
    }
}