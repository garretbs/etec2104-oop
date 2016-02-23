package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.config.GameParameters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BetManagerTest{
    @Test
    public void shouldIncreaseBet(){
        int initial_bet = BetManager.getInstance().getCurrentBet();
	BetManager.getInstance().increaseBet();
        assertEquals(initial_bet + 1, BetManager.getInstance().getCurrentBet());
    }
    @Test
    public void shouldNotBeAboveMax(){
	BetManager.getInstance().increaseBet();
        assertTrue(BetManager.getInstance().getCurrentBet() <= GameParameters.MAX_BET);
    }
    @Test
    public void shouldDecreaseBet(){
	int initial_bet = BetManager.getInstance().getCurrentBet();
	BetManager.getInstance().decreaseBet();
        assertEquals(initial_bet - 1, BetManager.getInstance().getCurrentBet());
    }
    @Test
    public void shouldNotBeBelowMin(){
	BetManager.getInstance().decreaseBet();
        assertTrue(BetManager.getInstance().getCurrentBet() >= GameParameters.MIN_BET);
    }
}