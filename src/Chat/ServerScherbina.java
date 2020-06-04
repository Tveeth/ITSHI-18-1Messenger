package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ServerScherbina extends javax.swing.JFrame {

    final int SERVER_PORT = 9000;
    Server myServer;
   

    public ServerScherbina() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        btnStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        					.addGap(0, 245, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }

    
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        startStopServer();
    }

    public static void main(String args[]) {

       
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

      
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerScherbina().setVisible(true);
            }
        });
    }

    
    private void startStopServer() {
        if (btnStart.getText().equals("Start")) {
            btnStart.setText("Stop");
            myServer = new Server();
            new Thread(myServer).start();
        } else {
            btnStart.setText("Start");
            if (myServer != null) {
                myServer.stopServer();
            }
        }
    }

   
    public class Server implements Runnable {

        
        private final ArrayList<ClientThread> connectedClients;
       
        private ServerSocket serverSocket;

        public Server() {
            connectedClients = new ArrayList<>();
        }

        
        public void stopServer() {
            txtLog.append("Close server socket.\n");
            sendMessageAllClient("<Server is down>");
            closeAllClients();
            connectedClients.clear();
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerScherbina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

       
        private void closeAllClients() {
            synchronized (connectedClients) {
                for (ClientThread current : connectedClients) {
                    current.closeConnection();
                }
            }
        }

        public boolean isLegalName(String name) {
            synchronized (connectedClients) {
                if (name.equals("all")) {
                    return false;
                }
                for (ClientThread current : connectedClients) {
                    if (current.info.name.equals(name)) {
                        return false;
                    }
                }
                return true;
            }
        }

        
        public void sendMessageAllClient(String msg) {	
            synchronized (connectedClients) {
                for (ClientThread current : connectedClients) {
                    current.out.println(msg);
                }
            }
        }

       
        public synchronized boolean sendPrivateMsg(String senderName, String recipientName, String msg) {
            if (senderName.equals(recipientName)) {
                return false;
            }
            ClientThread recipient = getClientThread(recipientName);
            if (recipient == null) {
                return false;
            }
            recipient.out.println("<Private message from: " + senderName + "> " + msg);
            return true;
        }

       
        private ClientThread getClientThread(String clientsName) {
            synchronized (connectedClients) {
                for (ClientThread current : connectedClients) {
                    if (current.info.name.equals(clientsName)) {
                        return current;
                    }
                }
                return null;
            }
        }

       
        @Override
        public void run() {
            try {
                
                serverSocket = new ServerSocket(SERVER_PORT);
                txtLog.append("Server port " + SERVER_PORT + ".\n");
                while (true) {
                    Socket clientSocket = serverSocket.accept(); 
                    if (btnStart.getText().equals("Stop")) {
                        ClientThread clientThread = new ClientThread(clientSocket);
                        clientThread.start();
                    } else {
                        return;
                    }
                }
            } catch (IOException e) {
                if (btnStart.getText().equals("Stop")) {
                    txtLog.setText("An error occurred while starting the server. \n\tError: " + e.toString() + "\n");
                    btnStart.setText("Start");
                }
            }
        }

        public boolean isLegalName(String name, ArrayList<ClientThread> connectedClients) {
            synchronized (connectedClients) {
                if (name.equals("all")) {
                    return false;
                }
                for (ClientThread current : connectedClients) {
                    if (current.info.name.equals(name)) {
                        return false;
                    }
                }
                return true;
            }
        }
        
      
        public class ClientThread extends Thread {
            public Socket curClient; 
            public PrintWriter out; //
            public BufferedReader in; 
            ClientInfoScherbina info;
            public ClientThread(Socket curClient) {
                this.curClient = curClient;
                info = new ClientInfoScherbina();
            }
             public ClientThread(Socket curClient, String name) {
                this.curClient = curClient;
                info = new ClientInfoScherbina();
                info.name = name;
            }
            @Override
            public void run() {
                try {

                    out = new PrintWriter(curClient.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(curClient.getInputStream()));
                    String name = in.readLine();
                    if (isLegalName(name)) {
                        txtLog.append("User " + name + " connect.\n");
                        sendMessageAllClient("<User " + name + " connect>");
                        out.println("Hi, " + name);
                        connectedClients.add(this);
                        info.name = name;

                       
                        String serializedFromClient;
                        while ((serializedFromClient = in.readLine()) != null) {
                            try {
                                System.out.println(serializedFromClient);
                                info = (ClientInfoScherbina) SerializeDeserialize.fromString(serializedFromClient);   
                                if (info.showOnline) {
                                    out.println(getConnectedClients());
                                } 
                                else if (info.receiver.equals("all")) {
                                    sendMessageAllClient(info.name + ": " + info.message);
                                } 
                                else {
                                  
                                    if (!sendPrivateMsg(info.name, info.receiver, info.message)) {
                                        out.println("<Failed to send your message. " + info.receiver + ">");
                                    } else { 
                                        out.println("<Send: " + info.message + " Only for: " + info.receiver + ">");
                                    }
                                }
                            } catch (ClassNotFoundException e) {
                                System.out.println("Error in deserialization" + e.toString());
                            }
                        }
                        txtLog.append("User " + info.name + " Left\n");
                        sendMessageAllClient("<User " + info.name + " left>");
                    } else {
                        out.println("<\n" + "Connection refused because your name is \"all\" or your name is already taken>");
                    }
                } 
                catch (IOException e) {
                    if (btnStart.getText().equals("Stop")) {
                        txtLog.append("Lost Communication " + info.name + ".\n\tError: + " + e.toString() + "\n");
                        sendMessageAllClient("<User " + info.name + " left>");
                    }
                } finally {
                    synchronized (connectedClients) {
                        connectedClients.remove(this);
                    }
                    closeConnection();
                }
            }

          
            private String getConnectedClients() {
                synchronized (connectedClients) {
                    String allConnected = "";
                    allConnected = "<Now online:\n";
                    for (ClientThread current : connectedClients) {
                        allConnected += current.info.name + ", ";
                    }
                    return allConnected.substring(0, allConnected.length() - 2) + ">";
                }
            }

           
            public void closeConnection() {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (curClient != null) {
                        curClient.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerScherbina.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtLog;
    
}
