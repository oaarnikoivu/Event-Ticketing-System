package cm3113cwwithupdatedclient;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Custom TimerTask for updating the ServerGUI current time text field.
 * @author oaarnikoivu
 *
 */
public class ServerTimerTask extends TimerTask {

    JTextField currentTime;

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
