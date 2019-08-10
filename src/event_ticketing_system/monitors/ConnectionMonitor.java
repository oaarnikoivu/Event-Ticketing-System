package event_ticketing_system.monitors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionMonitor {

    private Lock lock;
    private Condition bufferNotEmpty;
    private Condition bufferNotFull;

    private String[] buffer;
    private int bufferSize;
    private int in, out;
    private int numItems;

    public ConnectionMonitor(int size) {
        lock = new ReentrantLock();
        bufferNotEmpty = lock.newCondition();
        bufferNotFull = lock.newCondition();

        bufferSize = size;
        in  = 0;
        out = 0;
        numItems = 0;
        buffer = new String[bufferSize];
    }

    /**
     * Add string to buffer
     * @param s String to add
     */
    public void add(String s) {
        try {
            lock.lock();
            while (numItems == bufferSize)
                try {
                    bufferNotFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            buffer[in] = s;
                in = (in + 1) % bufferSize;
                numItems ++;
                bufferNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Remove String from String buffer
     * Used to update ServerGUI connection history scroll pane
     * @return Client information in String format
     */
    public String remove() {
        try {
            lock.lock();
            while (numItems == 0)
                try {
                    bufferNotEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            String s = buffer[out];
                out = (out + 1) % bufferSize;
                numItems--;
                bufferNotEmpty.signal();
                return s;
        } finally {
            lock.unlock();
        }
    }
}
