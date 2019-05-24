package cm3113cwwithupdatedclient;


import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

/**
 * CM3113 Session 2018-19 Starting point for Coursework
 * ConnectionHandler.java communicates with client to perform ticket transaction
 */
public class ConnectionHandler implements Runnable
{

    private Server server;
    private Socket incoming;
    private BufferedReader theInput;
    private PrintWriter theOutput;
    private TicketedEvent theEvent;
    private boolean go = true;

    /**
     * Creates a new instance of Class
     */
    public ConnectionHandler(Server serv, Socket incoming, TicketedEvent event 
    ){
        server = serv;
        this.incoming = incoming;
        this.theEvent = event;
    }

    public void close() {
        if (go) go = false;
    }

    public void closeConnections() throws IOException {
        theInput.close();
        theOutput.close();
        incoming.close();
    }

    public void sendToClient(String s) {
        theOutput.println(s);
    }

    public void run() {
        String remoteIPAddress = incoming.getLocalAddress().getHostName()
                + ":" + incoming.getLocalPort();
        LocalDateTime bidDateTime = LocalDateTime.now();
        try {
            // set up streams for bidirectional transfer across connection socket
            theInput = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            theOutput = new PrintWriter(incoming.getOutputStream(), true /* auto flush */);
            // acknowledge connection
            sendToClient("You are connected to ticket server: "
                    + remoteIPAddress + " at date / time " + bidDateTime + " \n");

            if (theEvent.isOpen()) {
                sendToClient("EVENT"
                        + "," + theEvent.getNumberTicketsRemaining()
                        + "," + theEvent.getEventName());
            }

            while (go) {
                if (theEvent.isOpen()) {
                    // read bid line and confirmation line
                    String line = theInput.readLine().trim();
                     System.out.println(line);
                     if (line.length() > 0) {
                         processMessage(line);
                     }
                     incoming.close();
                }
            }
            theOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                closeConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void processMessage(String message) {
        String bits[] = message.split(",");
        if (bits[0].toUpperCase().equals("ORDER")) {
            int numberTicketsRequested = Integer.parseInt(bits[1]);
            String contactName = bits[2];
            if (numberTicketsRequested <= theEvent.getNumberTicketsRemaining()) {
                Ticket ticket = theEvent.getTicket(contactName, numberTicketsRequested);
                // add current ticket to the TicketMonitor
                server.ticketMonitor.add(ticket);
                String reply = "Ticket issued to: " + contactName + " for " + numberTicketsRequested + " people "
                        + incoming.getInetAddress().getHostName()
                        + " on port " + incoming.getPort()
                        + " at " + LocalDateTime.now();
                System.out.println(reply);
                // add reply to the ConnectionMonitor
                server.connectionMonitor.add(reply);
                sendToClient("CONFIRM" 
                        + "," + ticket.getTicketRef()
                        + "," + ticket.getNumberOfPeople()
                        + "," + ticket.getContactName()
                );
            } else {
                sendToClient("SOLDOUT");
            }
            go = false;
        } else if (bits[0].toUpperCase().equals("ENQUIRE")) {
            sendToClient("UPDATE" 
                    + "," + theEvent.getNumberTicketsRemaining()
                    + "," + theEvent.getEventName()
            );
        } else {
            System.out.println(message);
        }
    }
}
