package event_ticketing_system;

import event_ticketing_system.models.Ticket;
import event_ticketing_system.monitors.ConnectionMonitor;
import event_ticketing_system.monitors.TicketMonitor;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MonitorTest {

    private ConnectionMonitor connectionMonitor = new ConnectionMonitor(10);
    private TicketMonitor ticketMonitor = new TicketMonitor(10);
    private Ticket t = new Ticket("Test", 7);

    @Before
    public void addToConnectionMonitor() {
        connectionMonitor.add("Test");
    }

    @Test
    public void removeFromConnectionMonitor() {
        assertEquals("Should return 'Test'", "Test", connectionMonitor.remove());
    }

    @Before
    public void addToTicketMonitor() {
        ticketMonitor.add(t);
    }

    @Test
    public void removeFromTicketMonitor() {
        ticketMonitor.remove();
    }
}
