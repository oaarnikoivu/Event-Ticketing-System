package cm3113cwwithupdatedclient;

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CM3113 Session 2018-19 Starting point for Coursework
 * Server.java monitors a ServerSocket and creates Server Threads to handle client connections
 */
public class Server extends Thread {

    private ServerSocket serversocket;
    private int thePortNo;
    private TicketedEvent theEvent;
    private volatile boolean theStopFlag;
    private CopyOnWriteArrayList<ConnectionHandler> connections;
    private ServerGUI gui;

    // Thread Pool
    private static final int nThreads = 10;
    private ExecutorService threadPool;

    // Timers
    private LocalTime serverStart, ticketingStart, serverFinish;

    // Monitors
    public ConnectionMonitor connectionMonitor;
    public TicketMonitor ticketMonitor;

    public Server(int portNo, TicketedEvent event, ServerGUI g) {
        // share references to all the shared data
        thePortNo = portNo;
        theEvent = event;
        gui = g;
        // create data for managing server and set of connections
        theStopFlag = false;
        connections = new CopyOnWriteArrayList<>();
        threadPool = Executors.newFixedThreadPool(nThreads);
    }

    public void startServer() {
        this.start();
        serverStart = LocalTime.now();
        LocalDateTime sStart = LocalDateTime.now();
        gui.updateConnectionHistoryScrollPane("Server started at date / time " + sStart);
    }

    public void stopServer() {
        theStopFlag = true;
        serverFinish = LocalTime.now();
        LocalDateTime serverEndTime = LocalDateTime.now();

        // close all current connections
        try {
            for (ConnectionHandler connectionHandler : connections) {
                connectionHandler.close();
            }
            serversocket.close();
            updateConnectionHistoryWhenServerStopped(serverEndTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update ServerGUI connection history with server stop date/time, duration from server start time
     * to server stop time, ticketing process end date/time, number of customers, number of tickets issued
     * and number of tickets unissued.
     * @param dateTime Server stop date/time & ticketing process end date/time.
     */
    private void updateConnectionHistoryWhenServerStopped(LocalDateTime dateTime) {
        String s = "";
        if (numberConnections() > 0) {
            s += "\n\n**********************************************************************************************"
                    + "\n\nServer stopped at date / time "
                    + dateTime
                    + " after running for "
                    + Duration.between(serverStart, serverFinish).toMillis()
                    + " milliseconds."
                    + "\n\nTicketing process ended at date / time "
                    + dateTime
                    + " after running for "
                    + Duration.between(ticketingStart, serverFinish).toMillis()
                    + " milliseconds."
                    + "\n\nNumber of customers: "
                    + theEvent.getTickets().size()
                    + "\nNumber of tickets issued: "
                    + (theEvent.getCapacity() - theEvent.getNumberTicketsRemaining())
                    + "\nNumber of tickets unissued: "
                    + theEvent.getNumberTicketsRemaining();
        } else {
            s = "Server stopped at date / time: " + dateTime;
        }
        gui.updateConnectionHistoryScrollPane(s);
    }

    public int numberConnections() {
        return connections.size();
    }

    public void openServerSocket() {
        try {
            this.serversocket = new ServerSocket(this.thePortNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            openServerSocket();
            System.out.println("Server started!");
            while (!theStopFlag) {
                Socket incoming = null;
                // listen for a connection request on serversocket
                // incoming is the connection socket
                try {
                    incoming = serversocket.accept();
                } catch (IOException e) {
                    if (theStopFlag) {
                        System.out.println("Server stopped!");
                        break;
                    }
                }
                // start new thread to service client
                ConnectionHandler conn = new ConnectionHandler(this, incoming, theEvent);
                this.threadPool.execute(
                        conn
                );
                System.out.println("Connected to:" + incoming);

                // Show incoming connections in ServerGUI connection history w/start time
                LocalDateTime connected = LocalDateTime.now();
                LocalTime connectionTimerStart = LocalTime.now();
                gui.updateConnectionHistoryScrollPane("\nNew client connected at date / time: " + connected);
                connections.add(conn);

                // Initiate ConnectionMonitor & Ticket Monitor
                connectionMonitor = new ConnectionMonitor(this.connections.size());
                ticketMonitor = new TicketMonitor(this.connections.size());

                // Update the ServerGUI
                updateLiveEventInformation();
                updateConnectionHistory();

                // Show closing client connection with end time/duration from start time to end time
                LocalDateTime disconnected = LocalDateTime.now();
                LocalTime connectionTimerEnd = LocalTime.now();
                gui.updateConnectionHistoryScrollPane("Client was connected for "
                        + Duration.between(connectionTimerStart, connectionTimerEnd).toMillis()
                        + " milliseconds and disconnected at date / time " + disconnected);
            }
            this.threadPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Update the the ServerGUI live event information.
     * Note: The number of customers who have ordered tickets is being set to:
     * theEvent.getTickets().size() + 1 as we want the application to count starting from 1, as opposed to 0.
     */
    private void updateLiveEventInformation() {
        gui.updateLiveEventInformation(String.valueOf(theEvent.getNumberTicketsRemaining()),
                String.valueOf(theEvent.getTickets().size() + 1));
        gui.updateLiveEventTicketList(ticketMonitor.remove());
    }

    /**
     * Update the Server GUI connection history.
     */
    private void updateConnectionHistory() {
        ticketingStart = LocalTime.now();
        LocalDateTime tStart = LocalDateTime.now();
        gui.updateConnectionHistoryStartTime("\n\nTicketing process started at date / time " + tStart + "\n");
        gui.updateConnectionHistoryScrollPane(connectionMonitor.remove());
    }

    public static void main(String[] args) {
        TicketedEvent event = new TicketedEvent("Test event", 1000);
        Server server = new Server(8189, event, new ServerGUI());
        server.startServer();
    }
}

