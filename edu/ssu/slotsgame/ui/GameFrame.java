package edu.ssu.slotsgame.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import edu.ssu.slotsgame.logic.BetManager;
import edu.ssu.slotsgame.logic.BetMediator;
import edu.ssu.slotsgame.logic.CreditManager;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.GameStateManager;

public class GameFrame extends JFrame implements ActionListener{

    JLabel creditLabel;
    BetLabel betLabel;
    JLabel wonLabel;

    BetButton maxBetButton;
    BetButton betUpButton;
    BetButton betDownButton;
    SpinButton spinButton;

    ReelPanel reelPanel;
    
    BetMediator mediator;

    public GameFrame(){
        super("SSU Slots");
        
        mediator = new BetMediator();

        creditLabel = new JLabel("Credits: " + CreditManager.getInstance().getCurrentCredits());

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
        
        this.add(reelPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        maxBetButton = new BetButton("MAX BET");
        maxBetButton.addActionListener(this);
        
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

        buttonPanel.add(maxBetButton, BorderLayout.WEST);
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
            case "MAX BET":
                BetManager.getInstance().setMaxBet();
                mediator.setBetLabel(betLabel);
                mediator.notifyOfBet();
                break;
            case "SPIN":
                if(GameStateManager.getInstance().getCurrentState() != GameState.READY) return;
                GameStateManager.getInstance().setCurrentState(GameState.PRESPIN);
                
                if(BetManager.getInstance().getCurrentBet() <= CreditManager.getInstance().getCurrentCredits()){
                    CreditManager.getInstance().subCredits(BetManager.getInstance().getCurrentBet());
                    creditLabel.setText("Credits: " + CreditManager.getInstance().getCurrentCredits());
                    CreditManager.getInstance().setCreditLabel(creditLabel);
                    CreditManager.getInstance().setWinLabel(wonLabel);
                    GameStateManager.getInstance().setCurrentState(GameState.SPIN);
                }else{
                    GameStateManager.getInstance().setCurrentState(GameState.READY);
                }
                break;
        }
    }
    
    public static void main(String[] args){
        new GameFrame();
    }
}