package event_ticketing_system;

import event_ticketing_system.models.Ticket;
import event_ticketing_system.models.TicketedEvent;
import event_ticketing_system.monitors.ConnectionMonitor;
import event_ticketing_system.monitors.TicketMonitor;
import event_ticketing_system.server.Server;
import event_ticketing_system.server.ServerGUI;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ServerTest {

    private TicketedEvent ticketedEvent = new TicketedEvent("Test event", 1000);
    private ServerGUI serverGUI = new ServerGUI();
    private Server server = new Server(8189, ticketedEvent, serverGUI);

    private ConnectionMonitor connectionMonitor;
    private TicketMonitor ticketMonitor;
    private Ticket ticket;

    @Test
    public void startServer() {
        server.startServer();
    }

    @Before
    public void setConnectionMonitor() {
        connectionMonitor = new ConnectionMonitor(10);
        connectionMonitor.add("Test");
    }

    @Test
    public void updateConnectionHistoryScrollPane() {
        serverGUI.updateConnectionHistoryScrollPane(connectionMonitor.remove());
    }

    @Test
    public void updateLiveEventInformation() {
        serverGUI.updateLiveEventInformation(String.valueOf(ticketedEvent.getNumberTicketsRemaining()),
                String.valueOf(ticketedEvent.getTickets().size()));
    }

    @Before
    public void setTicketMonitor() {
        ticketMonitor = new TicketMonitor(10);
        ticket = new Ticket("Test", 1);
    }

    @Test
    public void updateLiveEventTicketList() {
        ticketMonitor.add(ticket);
        serverGUI.updateLiveEventTicketList(ticketMonitor.remove());
    }

    @Test
    public void updateConnectionHistoryStartTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        serverGUI.updateConnectionHistoryStartTime(dateTime.toString());
    }

}
