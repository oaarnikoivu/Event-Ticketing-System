package event_ticketing_system;

import event_ticketing_system.models.Ticket;
import event_ticketing_system.models.TicketedEvent;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
