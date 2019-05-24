package cm3113cwwithupdatedclient;

import org.junit.Test;

import javax.swing.*;

/**
 * Tests that the ServerTimerTask's run() method works as intended.
 * @author oaarnikoivu
 *
 */
public class ServerRuntimeTest {

    private JTextField currentTime;

    @Test
    public void serverRuntime() {
        ServerTimerTask timerTask = new ServerTimerTask(currentTime);
        timerTask.run();
    }
}
