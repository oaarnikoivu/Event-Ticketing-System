package event_ticketing_system.models;

public class Ticket {
    private String contactName;
    private int numberOfPeople;
    private long ticketRef;
    
    private static long nextTicketRef;

    public Ticket(String c, int n){
        contactName = c;
        numberOfPeople = n;
        ticketRef = nextTicketRef++;
    }

    @Override
    public String toString() {
        return "Item{" + "description=" + contactName + ", highestBid=" + numberOfPeople + '}';
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public long getTicketRef() {
        return ticketRef;
    }

    public void setTicketRef(long ticketRef) {
        this.ticketRef = ticketRef;
    }
       
}
