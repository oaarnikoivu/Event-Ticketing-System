package event_ticketing_system.utils;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class ServerTimerTask extends TimerTask {

    private JTextField currentTime;

    public ServerTimerTask(JTextField currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DateFormat dateAndTime = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                currentTime.setText(dateAndTime.format(date));
            }
        });
    }
}
