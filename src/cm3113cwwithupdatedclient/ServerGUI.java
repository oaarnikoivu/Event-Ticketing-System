/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm3113cwwithupdatedclient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * GUI for creating Server threads and handling Client connections
 * @author oaarnikoivu
 *
 */
public class ServerGUI extends javax.swing.JFrame {

    private TicketedEvent ticketedEvent;
    private Server server;
    private int portNum;
    private Timer timer;

    /**
     * Creates new form ServerGUI
     */
    public ServerGUI() {
        portNum = 8189;
        ticketedEvent = new TicketedEvent();
        initComponents();

        this.stopServerBtn.setEnabled(false);
        this.addEventBtn.setEnabled(false);
    }

    /**
     * Method for updating the number of tickets remaining, and number of customers
     * who have ordered tickets.
     * Method utilises a Timer which updates very 1/10th of a second for ensuring thread safety.
     * @param numTixRemaining number of tickets remaining
     * @param numCustomersOrderedTix number of customers who have ordered tickets
     */
    public void updateLiveEventInformation(String numTixRemaining, String numCustomersOrderedTix) {
        javax.swing.Timer timer = new javax.swing.Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticketsRemainingTextField.setText(numTixRemaining);
                numCustomersOrderedTextField.setText(numCustomersOrderedTix);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Method for adding Ticket information to the liveEventInfoComboBox.
     * Utilises a timer which updates every 1/10th of a second to ensure thread safety.
     * @param s String to add
     */
    public void updateLiveEventTicketList(String s) {
        javax.swing.Timer timer = new javax.swing.Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liveEventInfoComboBox.getModel().setSelectedItem(liveEventInfoComboBox.getItemAt(0));
                liveEventInfoComboBox.addItem(s);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Method for adding details of a user's connection into the connectionHistoryTextArea
     * Uses invokeLater(new Runnable(){}) to ensure thread safety.
     * @param s String to append
     */
    public void updateConnectionHistoryScrollPane(String s) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                connectionHistoryTextArea.append("\n" + s);
            }
        });
    }

    /**
     * Method for updating the starting time for when connections are being received.
     * Uses invokeLater(new Runnable(){}) to ensure thread safety.
     * @param s String to append
     */
    public void updateConnectionHistoryStartTime(String s) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (!connectionHistoryTextArea.getText().toLowerCase().contains("ticketing process started")) {
                    connectionHistoryTextArea.append(s);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        currentTimeLabel = new javax.swing.JLabel();
        currentTimeTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        eventSetupLabel = new javax.swing.JLabel();
        eventNameLabel = new javax.swing.JLabel();
        eventNameTextField = new javax.swing.JTextField();
        eventCapLabel = new javax.swing.JLabel();
        addEventBtn = new javax.swing.JButton();
        startServerBtn = new javax.swing.JButton();
        stopServerBtn = new javax.swing.JButton();
        eventCapSpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        liveEventInfoLabel = new javax.swing.JLabel();
        ticketsRemainingLabel = new javax.swing.JLabel();
        numCustomersOrderedLabel = new javax.swing.JLabel();
        ticketsRemainingTextField = new javax.swing.JTextField();
        numCustomersOrderedTextField = new javax.swing.JTextField();
        liveEventInfoComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        connectionHistoryLabel = new javax.swing.JLabel();
        connectionHistoryScrollPane = new javax.swing.JScrollPane();
        connectionHistoryTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        currentTimeLabel.setText("Current Time");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentTimeLabel)
                .addGap(23, 23, 23)
                .addComponent(currentTimeTextField)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentTimeLabel)
                    .addComponent(currentTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        eventSetupLabel.setText("Event Setup");

        eventNameLabel.setText("Event Name");

        eventCapLabel.setText("Event Capacity");

        addEventBtn.setText("Add New Event");
        addEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewEventActionPerformed(evt);
            }
        });

        startServerBtn.setText("Start Server");
        startServerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerActionPerformed(evt);
            }
        });

        stopServerBtn.setText("Stop Server");
        stopServerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(eventSetupLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eventNameLabel)
                            .addComponent(eventCapLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(eventCapSpinner))
                            .addComponent(eventNameTextField))
                        .addGap(18, 18, 18)
                        .addComponent(addEventBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startServerBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stopServerBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(77, 77, 77))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventSetupLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eventNameLabel)
                                    .addComponent(eventNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eventCapLabel)
                                    .addComponent(eventCapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(startServerBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stopServerBtn)))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(addEventBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        liveEventInfoLabel.setText("Live Event Information");

        ticketsRemainingLabel.setText("Tickets Remaining");

        numCustomersOrderedLabel.setText("Number of Customers who have ordered tickets");

        /*ticketsRemainingTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketsRemainingTextFieldActionPerformed(evt);
            }
        });

        numCustomersOrderedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numCustomersOrderedTextFieldActionPerformed(evt);
            }
        });*/

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(liveEventInfoLabel)
                        .addGap(836, 836, 836))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numCustomersOrderedLabel)
                            .addComponent(ticketsRemainingLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ticketsRemainingTextField)
                            .addComponent(numCustomersOrderedTextField))
                        .addGap(18, 18, 18)
                        .addComponent(liveEventInfoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(liveEventInfoLabel)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ticketsRemainingLabel)
                            .addComponent(ticketsRemainingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numCustomersOrderedLabel)
                            .addComponent(numCustomersOrderedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(liveEventInfoComboBox))
                .addGap(50, 50, 50))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        connectionHistoryLabel.setText("Connection History");

        connectionHistoryTextArea.setColumns(20);
        connectionHistoryTextArea.setRows(5);
        connectionHistoryScrollPane.setViewportView(connectionHistoryTextArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(connectionHistoryLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(connectionHistoryScrollPane))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionHistoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectionHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Add new event button
     * @param evt
     */
    private void addNewEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewEventActionPerformed
        String eventName = eventNameTextField.getText();
        int eventCapacity = (Integer) eventCapSpinner.getValue();
        ticketedEvent.setEventName(eventName);
        ticketedEvent.setCapacity(eventCapacity);
        ticketedEvent.setNumberTicketsRemaining(ticketedEvent.getCapacity());
        ticketsRemainingTextField.setText(String.valueOf(ticketedEvent.getNumberTicketsRemaining()));
        numCustomersOrderedTextField.setText(String.valueOf(0));
        addEventBtn.setEnabled(false);
    }//GEN-LAST:event_addNewEventActionPerformed

    /**
     * Start the server button
     * @param evt
     */
    private void startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerActionPerformed
        this.server = new Server(portNum, ticketedEvent, this);
        this.server.startServer();
        this.addEventBtn.setEnabled(true);
        this.startServerBtn.setEnabled(false);
        this.stopServerBtn.setEnabled(true);

        timer = new Timer();
        timer.schedule(new ServerTimerTask(currentTimeTextField), 0, 1000);

    }//GEN-LAST:event_startServerActionPerformed

    /**
     * Stop the server button
     * @param evt
     */
    private void stopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopServerActionPerformed
        this.server.stopServer();
        eventNameTextField.setText("");
        eventCapSpinner.setValue(0);
        this.startServerBtn.setEnabled(true);
        this.stopServerBtn.setEnabled(false);

        timer.cancel();
        currentTimeTextField.setText("");
    }//GEN-LAST:event_stopServerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventBtn;
    private javax.swing.JLabel connectionHistoryLabel;
    private javax.swing.JScrollPane connectionHistoryScrollPane;
    private javax.swing.JTextArea connectionHistoryTextArea;
    private javax.swing.JLabel currentTimeLabel;
    private javax.swing.JTextField currentTimeTextField;
    private javax.swing.JLabel eventCapLabel;
    private javax.swing.JSpinner eventCapSpinner;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JTextField eventNameTextField;
    private javax.swing.JLabel eventSetupLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> liveEventInfoComboBox;
    private javax.swing.JLabel liveEventInfoLabel;
    private javax.swing.JLabel numCustomersOrderedLabel;
    private javax.swing.JTextField numCustomersOrderedTextField;
    private javax.swing.JButton startServerBtn;
    private javax.swing.JButton stopServerBtn;
    private javax.swing.JLabel ticketsRemainingLabel;
    private javax.swing.JTextField ticketsRemainingTextField;
    // End of variables declaration//GEN-END:variables
}
