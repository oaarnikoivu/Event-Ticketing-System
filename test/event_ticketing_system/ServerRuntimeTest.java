package event_ticketing_system;

import event_ticketing_system.utils.ServerTimerTask;
import org.junit.Test;

import javax.swing.*;

public class ServerRuntimeTest {

    private JTextField currentTime;

    @Test
    public void serverRuntime() {
        ServerTimerTask timerTask = new ServerTimerTask(currentTime);
        timerTask.run();
    }
}
