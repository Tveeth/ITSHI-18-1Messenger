package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class ClientScherbina extends javax.swing.JFrame {

    final int SERVER_PORT = 9000; 
    Socket socket = null;           
    PrintWriter out = null;         
    BufferedReader in = null;       
    ClientInfoScherbina info;    
   
    public ClientScherbina() {
    	getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnConnect = new javax.swing.JButton();
        btnConnect.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblMyName = new javax.swing.JLabel();
        lblMyName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        txtMyName = new javax.swing.JTextField();
        lblServerAddr = new javax.swing.JLabel();
        lblServerAddr.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        txtServerAddr = new javax.swing.JTextField();
        txtServerAddr.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnClear = new javax.swing.JButton();
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSendTo = new javax.swing.JTextField();
        txtSendTo.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtMyMSG = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnSend.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 13));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        lblMyName.setText("Login");

        lblServerAddr.setText("Server ip");

        txtServerAddr.setText("127.0.0.1");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtSendTo.setText("all");

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel3.setText("Whom");
        btnWhoOnline = new javax.swing.JButton();
        btnWhoOnline.setFont(new Font("Tahoma", Font.BOLD, 13));
        
                btnWhoOnline.setText("Online:");
                btnWhoOnline.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnWhoOnlineActionPerformed(evt);
                    }
                });
        txtChat = new javax.swing.JTextArea();
        
                txtChat.setColumns(10);
                txtChat.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(24)
        					.addComponent(jLabel3)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(txtSendTo, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        						.addComponent(txtMyName, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(40)
        					.addComponent(lblMyName)))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtMyMSG, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        				.addComponent(txtChat, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(btnWhoOnline, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        							.addComponent(txtServerAddr, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        						.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        					.addGap(84))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblServerAddr)
        					.addGap(102))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(txtChat, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(txtServerAddr, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        							.addGap(31)
        							.addComponent(btnWhoOnline, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        							.addGap(29)
        							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblServerAddr)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(lblMyName, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)))
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(6)
        							.addComponent(txtMyName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(btnConnect, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))))
        			.addGap(25)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel3)
        				.addComponent(txtSendTo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtMyMSG, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(30, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }

   
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
        conDisconOfServer();
    }

   
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
        sendMsg();
    }

    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearChat();
    }

  
    private void btnWhoOnlineActionPerformed(java.awt.event.ActionEvent evt) {
        showOnlineClients();
    }

    
    private void clearChat() {
        txtChat.setText("");
    }

   
    private void sendMsg() {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (btnConnect.getText().equals("Connect")) {
                        throw new Exception();
                    }
                    info.message = txtMyMSG.getText();
                    info.receiver = txtSendTo.getText();
                    String serializedInfo = SerializeDeserialize.toString(info);
                    out.println(serializedInfo);
                    txtMyMSG.setText("");
                } catch (Exception e) {
                    txtChat.append("<Problem sending message>\n");
                }
            }
        }.start();
    }

    
    private void showOnlineClients() {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (btnConnect.getText().equals("Connect")) {
                        throw new Exception();
                    }
                    info.showOnline = true;
                    String serializedInfo = SerializeDeserialize.toString(info);
                    out.println(serializedInfo);
                    info.showOnline = false;
                } 
                catch (Exception e) {
                    txtChat.append("<The problem with the online sheet>\n");
                }
            }
        }.start();
    }

  
    private void conDisconOfServer() {
        if (btnConnect.getText().equals("Quit")) {
            btnConnect.setText("Connect");
            clostSockInOut();
        } else {
            new Thread() {
                @Override
                public void run() {
                    try {
                        socket = new Socket(txtServerAddr.getText(), SERVER_PORT);
                        out = new PrintWriter(socket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                       
                        info = new ClientInfoScherbina();
                        if ((txtMyName.getText().equals(""))) {
                            info.name = "default name";
                            txtMyName.setText("default name");
                        } else {
                            info.name = txtMyName.getText();
                        }

                       
                        out.println(info.name);

                        btnConnect.setText("Quit");
                        try {
                            String line;
                            while ((line = in.readLine()) != null) {
                                txtChat.append(line + "\n");
                            }
                        } catch (IOException e) {
                            System.out.println(".run()");
                            
                            if (btnConnect.getText().equals("Quit")) {
                                txtChat.append("<Server is down>\n");
                            } else {
                                txtChat.append("<You left the server>\n");
                            }
                        }
                      
                    } catch (IOException e) {
                        txtChat.append("<No connection>\n");

                    } finally {
                        btnConnect.setText("Connect");
                        clostSockInOut();
                    }
                }
            }.start();
        }
    }

   
    private void clostSockInOut() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
        if (out != null) {
            out.close();
        }
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
            java.util.logging.Logger.getLogger(ClientScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientScherbina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientScherbina().setVisible(true);
            }
        });
    }


    
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnWhoOnline;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMyName;
    private javax.swing.JLabel lblServerAddr;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMyMSG;
    private javax.swing.JTextField txtMyName;
    private javax.swing.JTextField txtSendTo;
    private javax.swing.JTextField txtServerAddr;


}
