package cm3113cwwithupdatedclient;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests that the concurrent modifications to the TicketedEvent class cause no errors.
 * @author oaarnikoivu
 *
 */
public class TicketedEventTest {

    private TicketedEvent ticketedEvent = new TicketedEvent("Test", 1000);

    @Test
    public void testNumberOfTicketsRemaining() {
        assertEquals("Value should be 1000", 1000, ticketedEvent.getNumberTicketsRemaining());
    }

    @Test
    public void addTickets() {
        ticketedEvent.addTicket("Test", new Ticket("A", 7));
        ticketedEvent.addTicket("RGU", new Ticket("B", 27));
    }

    @Before
    public void addSomeTickets() {
        addTickets();
    }

    @Test
    public void testTicketsSize() {
        assertEquals("Value should be 2", 2, ticketedEvent.getTickets().size());
    }

}
