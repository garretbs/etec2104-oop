
package edu.ssu.slotsgame.ui;
import edu.ssu.slotsgame.logic.GameState;
import edu.ssu.slotsgame.logic.StateEvent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SpinButtonTest {
    @Test
    public void shouldDisableWhenSpin(){
        SpinButton sbTest = new SpinButton("");
        sbTest.notify(new StateEvent(GameState.PRESPIN, GameState.SPIN));
        assertFalse(sbTest.isEnabled());
    }
    @Test
    public void shouldEnableWhenReady(){
        SpinButton sbTest = new SpinButton("");
        sbTest.notify(new StateEvent(GameState.PRESPIN, GameState.READY));
        assertTrue(sbTest.isEnabled());
    }
}
