package cm3113cwwithupdatedclient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Custom monitor for adding and removing Ticket objects from a buffer.
 * @author oaarnikoivu
 *
 */
public class TicketMonitor {

    private Lock lock;
    private Condition bufferNotEmpty;
    private Condition bufferNotFull;

    private Ticket[] buffer;
    private int bufferSize;
    private int in, out;
    private int numTickets;

    public TicketMonitor(int size) {
        lock = new ReentrantLock();
        bufferNotEmpty = lock.newCondition();
        bufferNotFull = lock.newCondition();

        bufferSize = size;
        in = 0;
        out = 0;
        numTickets = 0;
        buffer = new Ticket[bufferSize];
    }

    /**
     * Add Ticket's to the buffer
     * @param t Ticket to add into the buffer
     */
    public void add(Ticket t) {
        try {
            lock.lock();
            while (numTickets == bufferSize)
                try {
                    bufferNotFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            buffer[in] = t;
                in = (in + 1) % bufferSize;
                numTickets++;
                bufferNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Remove String from Ticket buffer
     * Used to update the live event information list of the ServerGUI
     * @return Ticket information in String format
     */
    public String remove() {
        try {
            lock.lock();
            while (numTickets == 0)
                try {
                    bufferNotEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Ticket t = buffer[out];
                out = (out + 1) % bufferSize;
                numTickets--;
                bufferNotEmpty.signal();
                return "Ticket Ref: " + t.getTicketRef()
                        + " - Customer Name: " + t.getContactName()
                        + " - Number of Tickets: " + t.getNumberOfPeople();
        } finally {
            lock.unlock();
        }
    }

}
