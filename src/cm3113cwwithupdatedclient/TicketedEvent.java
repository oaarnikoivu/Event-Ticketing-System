package cm3113cwwithupdatedclient;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CM3113 Session 2018-19 Starting point for Coursework TicketedEvent.java
 * encapsulated properties of an event that has tickets allocated to customers
 */
public class TicketedEvent {

    private String eventName;
    private int capacity;
    private AtomicInteger numberTicketsRemaining;
    private volatile boolean theEventIsOpen;
    private ConcurrentHashMap<String, Ticket> tickets;

    public TicketedEvent(String name, int cap) {
        this.eventName = name;
        this.capacity = cap;
        this.setNumberTicketsRemaining(cap);
        this.theEventIsOpen = true;
        tickets = new ConcurrentHashMap<>();
    }

    public TicketedEvent() {
        this.theEventIsOpen = true;
        tickets = new ConcurrentHashMap<>();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberTicketsRemaining() {
        return numberTicketsRemaining.get();
    }

    public void setNumberTicketsRemaining(int cap) {
        this.numberTicketsRemaining = new AtomicInteger(cap);
    }

    public ConcurrentHashMap<String, Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(String contact, Ticket ticket) {
        this.tickets.put(contact, ticket);
    }

    public Ticket getTicket(String contact, int number) {
        Ticket t = new Ticket(contact, number);
        this.tickets.put(contact, t);
        this.numberTicketsRemaining = new AtomicInteger(this.getNumberTicketsRemaining() - number);
        return t;
    }

    public boolean isOpen() {
        return theEventIsOpen;
    }
}
