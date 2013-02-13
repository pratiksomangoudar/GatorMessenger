/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gatormessenger;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author pratiksomanagoudar
 */
public class GatorMessenger extends javax.swing.JApplet {

    private String currentChatter = "";
    private HashMap<String, String> currentChats = new HashMap<String, String>();
    private String currentChatterHistory;
    private static final Logger logger = Logger.getLogger(GatorMessenger.class.getName());
    private String[] practiceStr = {
        "1.   Start a voice chat with a contact.",
        "2.    End a voice chat with a contact.",
        "3.    Change status of user.",
        "4.    Search for a contact.",
        "5.    Type \"What is a virtual human?\" and send to a contact.",
        "6.    Save the chat to chat history.",
        "7.    Add a new contact.",
        "8.    View the chat history with a contact.",
        "9.    Search chat history.",
        "10.    Delete a contact."};
    private String[] testStr = {
        "1.    Add a new contact.",
        "2.    Type \"What is a virtual human?\" and send to a contact.",
        "3.    Save the chat to chat history.",
        "4.    View the chat history with a contact.",
        "5.    Search chat history.",
        "6.    Delete a contact.",
        "7.    Search for a contact.",
        "8.    Start a voice chat with a contact.",
        "9.    End a voice chat with a contact.",
        "10.    Change status of user."};
    VirtualHumanConnection vir = new VirtualHumanConnection();
    private long taskStartTime;
    private int taskList;
    private boolean isPracticeStarted = false;
    private int taskNumber;
    private boolean isTaskComplete;
    private int taskEvents;
    private long taskEndTime;
    private HashMap<Integer, Integer> practiceTaskEvents = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> testTaskEvents = new HashMap<Integer, Integer>();
    private String practiceResultsStr = new String();
    private HashMap<String, String> chatHistoryMap = new HashMap<String, String>();
    private String testResultsStr;
    private boolean isTestOver = false;

    /**
     * Initializes the applet GatorMessenger
     */
    @Override
    public void init() {
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
            java.util.logging.Logger.getLogger(GatorMessenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GatorMessenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GatorMessenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GatorMessenger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initComponents();
                    updateFriendList();
                    updateSavedConversations();
                    initialiseEventCounts();
                    initialiseChatHistory();


                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateFriendList() {
        int listsize = friendList.getModel().getSize();
        for (int i = 0; i < listsize; i++) {
            friendListModel.addElement(friendList.getModel().getElementAt(i));
            friendListArray.add(friendList.getModel().getElementAt(i));

        }
    }

    private void updateSavedConversations() {
        int listsize = savedChatsList.getModel().getSize();
        for (int i = 0; i < listsize; i++) {
            savedChatListModel.addElement(savedChatsList.getModel().getSize());
            savedChatListArray.add(savedChatsList.getModel().getSize());
            savedChatsList.setModel(savedChatListModel);

        }
    }

    private void initialiseEventCounts() {
        practiceTaskEvents.put(0, 5);
        practiceTaskEvents.put(1, 1);
        practiceTaskEvents.put(2, 1);
        practiceTaskEvents.put(3, 2);
        practiceTaskEvents.put(4, 6);
        practiceTaskEvents.put(5, 1);
        practiceTaskEvents.put(6, 2);
        practiceTaskEvents.put(7, 2);
        practiceTaskEvents.put(8, 2);
        practiceTaskEvents.put(9, 7);
        testTaskEvents.put(0, 2);
        testTaskEvents.put(1, 6);
        testTaskEvents.put(2, 1);
        testTaskEvents.put(3, 2);
        testTaskEvents.put(4, 2);
        testTaskEvents.put(5, 6);
        testTaskEvents.put(6, 2);
        testTaskEvents.put(7, 5);
        testTaskEvents.put(8, 1);
        testTaskEvents.put(9, 1);
    }

    private void initialiseChatHistory() {
        int size = friendList.getModel().getSize();

        for (int i = 0; i < size; i++) {
            String person = (String) friendListModel.getElementAt(i);
            String history = "\nMe: \n" + "This is working.\n\n" + person + " :\n" + "Thats great. Good work." + "\n\nAuto-saved chat history with " + person + "\n";
            chatHistoryMap.put(person, history);
            savedChatListModel.addElement(person + "-Autosaved");
            savedChatsMap.put(person + "-Autosaved", history);
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFriendWindow = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        friendNameLabel = new javax.swing.JLabel();
        friendEmailLabel = new javax.swing.JLabel();
        friendNameText = new javax.swing.JTextField();
        friendEmailText = new javax.swing.JTextField();
        addFriendButton = new javax.swing.JButton();
        removeFriendWindow = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        removeYes = new javax.swing.JButton();
        removeCancel = new javax.swing.JButton();
        chatWindow = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatDialogue = new javax.swing.JTextPane();
        saveChat = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        userInput = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        voiceChatButton = new javax.swing.JToggleButton();
        volumeSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        resultWindow = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        resultText = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        friendsTab = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList();
        friendlistStatus = new javax.swing.JLabel();
        addFriendsButton = new javax.swing.JButton();
        removeFriendsButton = new javax.swing.JButton();
        searchFriends1 = new org.jdesktop.swingx.JXSearchField();
        jPanel4 = new javax.swing.JPanel();
        chatList = new javax.swing.JScrollPane();
        savedChatsList = new javax.swing.JList();
        searchChats1 = new org.jdesktop.swingx.JXSearchField();
        jPanel6 = new javax.swing.JPanel();
        statusIcon = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        userStatus = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        practiceButton = new javax.swing.JButton();
        testStartButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        instructionText = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        statusMenu = new javax.swing.JMenu();
        availableStatus = new javax.swing.JMenuItem();
        busyStatus = new javax.swing.JMenuItem();
        awayStatus = new javax.swing.JMenuItem();
        invisibleStatus = new javax.swing.JMenuItem();
        otherStatus = new javax.swing.JMenu();
        homeStatus = new javax.swing.JMenuItem();
        officeStatus = new javax.swing.JMenuItem();
        schoolStatus = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        copyMenu = new javax.swing.JMenuItem();
        pasteMenu = new javax.swing.JMenuItem();
        cutMenu = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        Troubleshooter = new javax.swing.JMenuItem();
        topicHelpMenu = new javax.swing.JMenuItem();
        aboutUsMenu = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("gatormessenger/Bundle"); // NOI18N
        addFriendWindow.setTitle(bundle.getString("GatorMessenger.addFriendWindow.title")); // NOI18N
        addFriendWindow.setAlwaysOnTop(true);
        addFriendWindow.setBounds(new java.awt.Rectangle(0, 200, 265, 140));
        addFriendWindow.setResizable(false);
        addFriendWindow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addFriendWindowKeyPressed(evt);
            }
        });

        friendNameLabel.setText(bundle.getString("GatorMessenger.friendNameLabel.text")); // NOI18N

        friendEmailLabel.setText(bundle.getString("GatorMessenger.friendEmailLabel.text")); // NOI18N

        friendNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendNameTextActionPerformed(evt);
            }
        });

        friendEmailText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendEmailTextActionPerformed(evt);
            }
        });

        addFriendButton.setText(bundle.getString("GatorMessenger.addFriendButton.text")); // NOI18N
        addFriendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addFriendButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(friendEmailLabel)
                            .add(friendNameLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, friendNameText)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, friendEmailText)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                        .add(0, 94, Short.MAX_VALUE)
                        .add(addFriendButton)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(friendNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(friendNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(friendEmailLabel)
                    .add(friendEmailText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(addFriendButton)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout addFriendWindowLayout = new org.jdesktop.layout.GroupLayout(addFriendWindow.getContentPane());
        addFriendWindow.getContentPane().setLayout(addFriendWindowLayout);
        addFriendWindowLayout.setHorizontalGroup(
            addFriendWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addFriendWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        addFriendWindowLayout.setVerticalGroup(
            addFriendWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addFriendWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        addFriendWindow.getAccessibleContext().setAccessibleDescription(bundle.getString("GatorMessenger.addFriendWindow.AccessibleContext.accessibleDescription")); // NOI18N

        removeFriendWindow.setTitle(bundle.getString("GatorMessenger.removeFriendWindow.title")); // NOI18N
        removeFriendWindow.setAlwaysOnTop(true);
        removeFriendWindow.setLocation(new java.awt.Point(0, 100));
        removeFriendWindow.setMaximumSize(new java.awt.Dimension(340, 110));
        removeFriendWindow.setMinimumSize(new java.awt.Dimension(340, 110));
        removeFriendWindow.setPreferredSize(new java.awt.Dimension(340, 110));
        removeFriendWindow.setResizable(false);

        jLabel1.setText(bundle.getString("GatorMessenger.jLabel1.text")); // NOI18N

        removeYes.setText(bundle.getString("GatorMessenger.removeYes.text")); // NOI18N
        removeYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeYesMouseClicked(evt);
            }
        });

        removeCancel.setText(bundle.getString("GatorMessenger.removeCancel.text")); // NOI18N
        removeCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(removeCancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(removeYes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, removeCancel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, removeYes))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout removeFriendWindowLayout = new org.jdesktop.layout.GroupLayout(removeFriendWindow.getContentPane());
        removeFriendWindow.getContentPane().setLayout(removeFriendWindowLayout);
        removeFriendWindowLayout.setHorizontalGroup(
            removeFriendWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, removeFriendWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        removeFriendWindowLayout.setVerticalGroup(
            removeFriendWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, removeFriendWindowLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        removeFriendWindow.getAccessibleContext().setAccessibleDescription(bundle.getString("GatorMessenger.removeFriendWindow.AccessibleContext.accessibleDescription")); // NOI18N

        chatWindow.setBounds(new java.awt.Rectangle(300, 300, 624, 450));
        chatWindow.setMinimumSize(new java.awt.Dimension(624, 450));
        chatWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                chatWindowWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                chatWindowWindowClosed(evt);
            }
        });

        chatDialogue.setEditable(false);
        jScrollPane2.setViewportView(chatDialogue);

        saveChat.setText(bundle.getString("GatorMessenger.saveChat.text")); // NOI18N
        saveChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveChatMouseClicked(evt);
            }
        });
        saveChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveChatActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, saveChat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 618, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel8Layout.createSequentialGroup()
                .add(saveChat)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 270, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        userInput.setColumns(20);
        userInput.setLineWrap(true);
        userInput.setRows(5);
        userInput.setWrapStyleWord(true);
        userInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userInputMouseClicked(evt);
            }
        });
        userInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userInputKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userInputKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(userInput);

        sendButton.setText(bundle.getString("GatorMessenger.sendButton.text")); // NOI18N
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 327, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sendButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sendButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GatorMessenger.jPanel1.border.title"))); // NOI18N

        voiceChatButton.setText(bundle.getString("GatorMessenger.voiceChatButton.text")); // NOI18N
        voiceChatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voiceChatButtonMouseClicked(evt);
            }
        });
        voiceChatButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                voiceChatButtonStateChanged(evt);
            }
        });
        voiceChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voiceChatButtonActionPerformed(evt);
            }
        });

        jLabel2.setText(bundle.getString("GatorMessenger.jLabel2.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(volumeSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(voiceChatButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(volumeSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(voiceChatButton)
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        org.jdesktop.layout.GroupLayout chatWindowLayout = new org.jdesktop.layout.GroupLayout(chatWindow.getContentPane());
        chatWindow.getContentPane().setLayout(chatWindowLayout);
        chatWindowLayout.setHorizontalGroup(
            chatWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(chatWindowLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(chatWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(chatWindowLayout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(2, 2, 2)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        chatWindowLayout.setVerticalGroup(
            chatWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(chatWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chatWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        resultWindow.setTitle(bundle.getString("GatorMessenger.resultWindow.title")); // NOI18N
        resultWindow.setBounds(new java.awt.Rectangle(200, 300, 710, 410));
        resultWindow.setMinimumSize(new java.awt.Dimension(710, 410));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GatorMessenger.jScrollPane4.border.title"))); // NOI18N

        resultText.setEditable(false);
        jScrollPane4.setViewportView(resultText);

        org.jdesktop.layout.GroupLayout resultWindowLayout = new org.jdesktop.layout.GroupLayout(resultWindow.getContentPane());
        resultWindow.getContentPane().setLayout(resultWindowLayout);
        resultWindowLayout.setHorizontalGroup(
            resultWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 696, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        resultWindowLayout.setVerticalGroup(
            resultWindowLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultWindowLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 395, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 490));

        friendsTab.setToolTipText(bundle.getString("GatorMessenger.friendsTab.toolTipText")); // NOI18N
        friendsTab.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        friendsTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                friendsTabStateChanged(evt);
            }
        });

        friendList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        friendList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Dr Lok", "Ronaldo", "Messi", "Rooney", "Henry", "Mata", "Gerrard", "Xavi", "Jessica", "Catherine" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        friendList.setToolTipText(bundle.getString("GatorMessenger.friendList.toolTipText")); // NOI18N
        friendList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friendListMouseClicked(evt);
            }
        });
        friendList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                friendListValueChanged(evt);
            }
        });
        friendList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                friendListFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(friendList);

        friendlistStatus.setText(bundle.getString("GatorMessenger.friendlistStatus.text")); // NOI18N

        addFriendsButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        addFriendsButton.setText(bundle.getString("GatorMessenger.addFriendsButton.text")); // NOI18N
        addFriendsButton.setToolTipText(bundle.getString("GatorMessenger.addFriendsButton.toolTipText")); // NOI18N
        addFriendsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addFriendsButtonMouseClicked(evt);
            }
        });
        addFriendsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFriendsButtonActionPerformed(evt);
            }
        });

        removeFriendsButton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        removeFriendsButton.setText(bundle.getString("GatorMessenger.removeFriendsButton.text")); // NOI18N
        removeFriendsButton.setToolTipText(bundle.getString("GatorMessenger.removeFriendsButton.toolTipText")); // NOI18N
        removeFriendsButton.setDefaultCapable(false);
        removeFriendsButton.setEnabled(false);
        removeFriendsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeFriendsButtonMouseClicked(evt);
            }
        });
        removeFriendsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFriendsButtonActionPerformed(evt);
            }
        });
        removeFriendsButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                removeFriendsButtonFocusLost(evt);
            }
        });

        searchFriends1.setPrompt(bundle.getString("GatorMessenger.searchFriends1.prompt")); // NOI18N
        searchFriends1.setText(bundle.getString("GatorMessenger.searchFriends1.text")); // NOI18N
        searchFriends1.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        searchFriends1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFriends1MouseClicked(evt);
            }
        });
        searchFriends1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFriends1ActionPerformed(evt);
            }
        });
        searchFriends1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFriends1FocusLost(evt);
            }
        });
        searchFriends1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFriends1KeyTyped(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(searchFriends1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(friendlistStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(addFriendsButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(removeFriendsButton)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(searchFriends1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(friendlistStatus)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addFriendsButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(removeFriendsButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        friendsTab.addTab(bundle.getString("GatorMessenger.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        savedChatsList.setToolTipText(bundle.getString("GatorMessenger.savedChatsList.toolTipText")); // NOI18N
        savedChatsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savedChatsListMouseClicked(evt);
            }
        });
        chatList.setViewportView(savedChatsList);

        searchChats1.setPrompt(bundle.getString("GatorMessenger.searchChats1.prompt")); // NOI18N
        searchChats1.setText(bundle.getString("GatorMessenger.searchChats1.text")); // NOI18N
        searchChats1.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        searchChats1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchChats1MouseClicked(evt);
            }
        });
        searchChats1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchChats1ActionPerformed(evt);
            }
        });
        searchChats1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchChats1FocusLost(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(searchChats1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(chatList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(searchChats1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chatList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        friendsTab.addTab(bundle.getString("GatorMessenger.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(friendsTab)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(friendsTab)
                .addContainerGap())
        );

        jPanel6.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        statusIcon.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        statusIcon.setForeground(new java.awt.Color(0, 255, 0));
        statusIcon.setText(bundle.getString("GatorMessenger.statusIcon.text")); // NOI18N

        userName.setFont(new java.awt.Font("Cambria", 1, 13)); // NOI18N
        userName.setText(bundle.getString("GatorMessenger.userName.text")); // NOI18N
        userName.setToolTipText(bundle.getString("GatorMessenger.userName.toolTipText")); // NOI18N

        userStatus.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        userStatus.setText(bundle.getString("GatorMessenger.userStatus.text")); // NOI18N
        userStatus.setToolTipText(bundle.getString("GatorMessenger.userStatus.toolTipText")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(statusIcon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(userName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(userStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(userStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(userName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(statusIcon, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GatorMessenger.jPanel11.border.title"))); // NOI18N

        practiceButton.setText(bundle.getString("GatorMessenger.practiceButton.text")); // NOI18N
        practiceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                practiceButtonMouseClicked(evt);
            }
        });

        testStartButton.setText(bundle.getString("GatorMessenger.testStartButton.text")); // NOI18N
        testStartButton.setEnabled(false);
        testStartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testStartButtonMouseClicked(evt);
            }
        });
        testStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testStartButtonActionPerformed(evt);
            }
        });

        instructionText.setEditable(false);
        instructionText.setBackground(new java.awt.Color(204, 204, 204));
        instructionText.setText(bundle.getString("GatorMessenger.instructionText.text")); // NOI18N
        jScrollPane5.setViewportView(instructionText);

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 300, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(practiceButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(75, 75, 75)
                        .add(testStartButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(practiceButton)
                    .add(testStartButton)))
        );

        jMenuBar1.setMaximumSize(new java.awt.Dimension(217, 30));
        jMenuBar1.setMinimumSize(new java.awt.Dimension(260, 25));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(260, 22));
        jMenuBar1.setRequestFocusEnabled(false);
        jMenuBar1.setSize(new java.awt.Dimension(260, 22));

        statusMenu.setText(bundle.getString("GatorMessenger.statusMenu.text")); // NOI18N
        statusMenu.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                statusMenuMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        availableStatus.setText(bundle.getString("GatorMessenger.availableStatus.text")); // NOI18N
        availableStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                availableStatusMouseClicked(evt);
            }
        });
        availableStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availableStatusActionPerformed(evt);
            }
        });
        statusMenu.add(availableStatus);

        busyStatus.setText(bundle.getString("GatorMessenger.busyStatus.text")); // NOI18N
        busyStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busyStatusActionPerformed(evt);
            }
        });
        statusMenu.add(busyStatus);

        awayStatus.setText(bundle.getString("GatorMessenger.awayStatus.text")); // NOI18N
        awayStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                awayStatusActionPerformed(evt);
            }
        });
        statusMenu.add(awayStatus);

        invisibleStatus.setText(bundle.getString("GatorMessenger.invisibleStatus.text")); // NOI18N
        invisibleStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invisibleStatusActionPerformed(evt);
            }
        });
        statusMenu.add(invisibleStatus);

        otherStatus.setText(bundle.getString("GatorMessenger.otherStatus.text")); // NOI18N

        homeStatus.setText(bundle.getString("GatorMessenger.homeStatus.text")); // NOI18N
        homeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeStatusActionPerformed(evt);
            }
        });
        otherStatus.add(homeStatus);

        officeStatus.setText(bundle.getString("GatorMessenger.officeStatus.text")); // NOI18N
        officeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                officeStatusActionPerformed(evt);
            }
        });
        otherStatus.add(officeStatus);

        schoolStatus.setText(bundle.getString("GatorMessenger.schoolStatus.text")); // NOI18N
        schoolStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolStatusActionPerformed(evt);
            }
        });
        otherStatus.add(schoolStatus);

        statusMenu.add(otherStatus);

        jMenuBar1.add(statusMenu);

        toolsMenu.setText(bundle.getString("GatorMessenger.toolsMenu.text")); // NOI18N

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.META_MASK));
        copyMenu.setText(bundle.getString("GatorMessenger.copyMenu.text")); // NOI18N
        copyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuActionPerformed(evt);
            }
        });
        toolsMenu.add(copyMenu);

        pasteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.META_MASK));
        pasteMenu.setText(bundle.getString("GatorMessenger.pasteMenu.text")); // NOI18N
        toolsMenu.add(pasteMenu);

        cutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.META_MASK));
        cutMenu.setText(bundle.getString("GatorMessenger.cutMenu.text")); // NOI18N
        toolsMenu.add(cutMenu);

        jMenuBar1.add(toolsMenu);

        helpMenu.setText(bundle.getString("GatorMessenger.helpMenu.text")); // NOI18N

        Troubleshooter.setText(bundle.getString("GatorMessenger.Troubleshooter.text")); // NOI18N
        helpMenu.add(Troubleshooter);

        topicHelpMenu.setText(bundle.getString("GatorMessenger.topicHelpMenu.text")); // NOI18N
        helpMenu.add(topicHelpMenu);

        aboutUsMenu.setText(bundle.getString("GatorMessenger.aboutUsMenu.text")); // NOI18N
        helpMenu.add(aboutUsMenu);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 429, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName(bundle.getString("GatorMessenger.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(bundle.getString("GatorMessenger.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void awayStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_awayStatusActionPerformed
        userStatus.setText("(" + awayStatus.getText() + ")");
        statusIcon.setForeground(Color.yellow);
        logger.info("Status set to Away");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }


    }//GEN-LAST:event_awayStatusActionPerformed

    private void busyStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busyStatusActionPerformed
        userStatus.setText("(" + busyStatus.getText() + ")");
        statusIcon.setForeground(Color.red);

        logger.info("Status set to busy");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_busyStatusActionPerformed

    private void schoolStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolStatusActionPerformed
        userStatus.setText("(" + schoolStatus.getText() + ")");
        statusIcon.setForeground(Color.blue);
        logger.info("Status set to at School");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_schoolStatusActionPerformed

    private void copyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuActionPerformed
    }//GEN-LAST:event_copyMenuActionPerformed

    private void removeFriendsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFriendsButtonActionPerformed
    }//GEN-LAST:event_removeFriendsButtonActionPerformed

    private void addFriendsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addFriendsButtonMouseClicked
        friendNameText.setText("");
        friendEmailText.setText("");
        addFriendWindow.setVisible(true);
        logger.info("Add friends button clicked");
        taskEvents++;

    }//GEN-LAST:event_addFriendsButtonMouseClicked

    private void friendNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendNameTextActionPerformed
    }//GEN-LAST:event_friendNameTextActionPerformed

    private void friendEmailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendEmailTextActionPerformed
    }//GEN-LAST:event_friendEmailTextActionPerformed

    private void addFriendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addFriendButtonMouseClicked
        String friendName = friendNameText.getText();
        friendListArray.add(friendName);
        //presentFriendList = (String[])friendListArray.toArray();
        friendListModel.addElement(friendName);
        friendList.setModel(friendListModel);
        addFriendWindow.setVisible(false);
        int size = friendListArray.size();
        if (size > 1) {
            friendlistStatus.setText(size + " Friends");
        } else {
            friendlistStatus.setText(size + " Friend");
        }
        logger.info("friend added");
        taskEvents++;
        if (taskList == 1 && taskNumber == 6) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 0) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_addFriendButtonMouseClicked

    private void friendListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_friendListValueChanged
        if (!friendList.isSelectionEmpty()) {
            removeFriendsButton.setEnabled(true);
            taskEvents++;
        }


        if ((String) friendList.getSelectedValue() != null) {
            selectedFriend = (String) friendList.getSelectedValue();
            logger.info("friend selected = " + selectedFriend);
        }
        logger.info("friend selected");
    }//GEN-LAST:event_friendListValueChanged

    private void friendListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_friendListFocusLost
        friendList.clearSelection();
        friendList.setModel(friendListModel);
        if (evt.getOppositeComponent() == removeFriendsButton) {
        } else {
            removeFriendsButton.setEnabled(false);
        }

    }//GEN-LAST:event_friendListFocusLost

    private void friendListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friendListMouseClicked
        if (evt.getClickCount() == 2) {

            initialiseChatWindow();
            taskEvents++;
        }
    }//GEN-LAST:event_friendListMouseClicked

    private void addFriendsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFriendsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFriendsButtonActionPerformed

    private void removeCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCancelActionPerformed
        removeFriendWindow.setVisible(false);
        removeFriendsButton.setEnabled(false);
        taskEvents++;

    }//GEN-LAST:event_removeCancelActionPerformed

    private void removeFriendsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeFriendsButtonMouseClicked


        if (removeFriendsButton.isEnabled() || !friendList.isSelectionEmpty()) {
            removeFriendWindow.setVisible(true);
            logger.info("Remove Friend window opened");
            taskEvents++;
        }
    }//GEN-LAST:event_removeFriendsButtonMouseClicked

    private void removeYesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeYesMouseClicked
        //friendListModel.remove(friendList.getSelectedIndex());

        if (selectedFriend.equalsIgnoreCase(currentChatter)) {

            chatWindow.setVisible(false);

        }

        friendListModel.removeElement(selectedFriend);
        friendListArray.remove(selectedFriend);
        int size = friendListArray.size();
        if (size > 1) {
            friendlistStatus.setText(friendListArray.size() + " Friends");
        } else {
            friendlistStatus.setText(friendListArray.size() + " Friend");
        }
        removeFriendWindow.setVisible(false);
        removeFriendsButton.setEnabled(false);
        
        taskEvents++;
        if (taskList == 1 && taskNumber == 9) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 5) {
            setTaskCompleted();
        }
       logger.info("friend removed"+taskEvents);
    }//GEN-LAST:event_removeYesMouseClicked

    private void availableStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_availableStatusMouseClicked
    }//GEN-LAST:event_availableStatusMouseClicked

    private void statusMenuMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_statusMenuMenuKeyPressed
        taskEvents++;
    }//GEN-LAST:event_statusMenuMenuKeyPressed

    private void availableStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availableStatusActionPerformed
        userStatus.setText("(" + availableStatus.getText() + ")");

        statusIcon.setForeground(Color.green);
        logger.info("Status set to Available");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_availableStatusActionPerformed

    private void invisibleStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invisibleStatusActionPerformed
        userStatus.setText("(" + invisibleStatus.getText() + ")");
        statusIcon.setForeground(Color.GRAY);
        logger.info("Status set to invisible");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_invisibleStatusActionPerformed

    private void homeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeStatusActionPerformed
        userStatus.setText("(" + homeStatus.getText() + ")");
        statusIcon.setForeground(Color.blue);
        logger.info("Status set to home");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }

    }//GEN-LAST:event_homeStatusActionPerformed

    private void officeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_officeStatusActionPerformed
        userStatus.setText("(" + officeStatus.getText() + ")");
        statusIcon.setForeground(Color.blue);
        logger.info("Status set to office");
        taskEvents++;
        if (taskList == 1 && taskNumber == 2) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 9) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_officeStatusActionPerformed

    private void saveChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveChatActionPerformed
    }//GEN-LAST:event_saveChatActionPerformed

    private void saveChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveChatMouseClicked

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a z");
        Date date = new Date();

        String entry = currentChatter + " (" + dateFormat.format(date) + ")";
        savedChatListArray.add(entry);
        savedChatListModel.addElement(entry);
        savedChatsList.setModel(savedChatListModel);
        savedChatsMap.put(entry, currentChats.get(currentChatter));
        String temp = chatDialogue.getText();

        chatDialogue.setText(temp + "\n" + "\n Saved conversation with " + currentChatter + " to Chat History.\n");
        currentChats.put(currentChatter, chatDialogue.getText());
        logger.info("Save Chat Button Clicked");
        taskEvents++;
        if (taskList == 1 && taskNumber == 5) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 2) {
            setTaskCompleted();
        }
    }//GEN-LAST:event_saveChatMouseClicked

    private void sendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendButtonMouseClicked
        addToChat();
        logger.info("send button clicked");
        taskEvents++;
        if (taskList == 1 && taskNumber == 4) {
            setTaskCompleted();
        }
        if (taskList == 2 && taskNumber == 1) {
            setTaskCompleted();
        }

    }//GEN-LAST:event_sendButtonMouseClicked

    private void userInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userInputKeyPressed
        int keyPressed = evt.getKeyCode();

        if (keyPressed == 10) {
            addToChat();
            userInput.setText(new String("\b"));
            userInput.setCaretPosition(0);

            logger.info("send button clicked");
            taskEvents++;
            if (taskList == 1 && taskNumber == 4) {
                setTaskCompleted();
            }
            if (taskList == 2 && taskNumber == 1) {
                setTaskCompleted();
            }
        }
    }//GEN-LAST:event_userInputKeyPressed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

    private void voiceChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voiceChatButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_voiceChatButtonActionPerformed

    private void searchFriends1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFriends1MouseClicked
        searchFriends1.setText("");
        searchFriends1.setForeground(Color.BLACK);
        logger.info("search friends selected");
        taskEvents++;

    }//GEN-LAST:event_searchFriends1MouseClicked

    private void searchFriends1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFriends1FocusLost

       
searchFriends1.setPrompt(" Search friends");
        if (friendList.isSelectionEmpty()) {
            friendList.setModel(friendListModel);
            logger.info("focuslost");
        }
        logger.info("Search friends focus lost");
    }//GEN-LAST:event_searchFriends1FocusLost

    private void searchFriends1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFriends1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFriends1KeyTyped

    private void searchFriends1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFriends1ActionPerformed
        String clue = searchFriends1.getText();
        if (clue != null && !clue.equals("") && searchFriends1.isFocusOwner()) {


            DefaultListModel searchModel = new DefaultListModel();
            for (int i = 0; i < friendListModel.getSize(); i++) {
                String str = ((String) friendListModel.getElementAt(i));



                if (clue.length() <= str.length()) {
                    if (str.substring(0, clue.length()).equalsIgnoreCase(clue)) {

                        searchModel.addElement(friendListModel.getElementAt(i));

                    }
                }
            }
            friendList.setModel(searchModel);
            logger.info("Friends Searched");
            if (searchModel.size() == 1) {
                taskEvents++;
                if (taskList == 1 && taskNumber == 3) {
                    setTaskCompleted();
                }
                if (taskList == 2 && taskNumber == 6) {
                    setTaskCompleted();
                }
            }
        } else {
            if (friendList.isSelectionEmpty()) {
                friendList.setModel(friendListModel);
                logger.info("Search reset");
            }
        }
    }//GEN-LAST:event_searchFriends1ActionPerformed

    private void searchChats1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchChats1ActionPerformed

        String clue = searchChats1.getText();
        if (clue != null && !clue.equals("") && searchChats1.isFocusOwner()) {

            DefaultListModel searchChatsModel = new DefaultListModel();
            int size = savedChatListModel.getSize();
            for (int i = 0; i < size; i++) {
                String str = ((String) savedChatListModel.getElementAt(i));
                String temp = savedChatsMap.get(str).toLowerCase();
                if (temp.contains(clue.toLowerCase())) {


                    searchChatsModel.addElement(savedChatListModel.getElementAt(i));

                }
            }

            savedChatsList.setModel(searchChatsModel);
            logger.info("search chats done");
            taskEvents++;
            if (taskList == 1 && taskNumber == 8) {
                setTaskCompleted();
            }
            if (taskList == 2 && taskNumber == 4) {
                setTaskCompleted();
            }
        } else {
            if (savedChatsList.isSelectionEmpty()) {
                savedChatsList.setModel(savedChatListModel);
                logger.info("search chats reset");
            }
        }
    }//GEN-LAST:event_searchChats1ActionPerformed

    private void searchChats1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchChats1MouseClicked
        searchChats1.setText("");
        searchChats1.setForeground(Color.BLACK);
        logger.info("Search friends button clicked");
        taskEvents++;
    }//GEN-LAST:event_searchChats1MouseClicked

    private void searchChats1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchChats1FocusLost
        
searchChats1.setPrompt("Search chats");
        if (savedChatsList.isSelectionEmpty()) {
            savedChatsList.setModel(savedChatListModel);
            logger.info("focus lost. chat history reset");
        }
    }//GEN-LAST:event_searchChats1FocusLost

    private void savedChatsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savedChatsListMouseClicked
        if (evt.getClickCount() == 2) {
            taskEvents++;
            initialiseChatWindowForHistory();
            logger.info("saved chats opened");
             if (taskList == 1 && taskNumber == 7) {

                setTaskCompleted();
            }
            if (taskList == 2 && taskNumber == 3) {
                setTaskCompleted();
            }
        }
    }//GEN-LAST:event_savedChatsListMouseClicked

    private void voiceChatButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_voiceChatButtonStateChanged
    }//GEN-LAST:event_voiceChatButtonStateChanged

    private void voiceChatButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voiceChatButtonMouseClicked

        if (voiceChatButton.isSelected()) {
            String temp = chatDialogue.getText();
            voiceChatButton.setText("Stop Voice");
            chatDialogue.setText(temp + "\n" + "\n VOICE CHAT STARTED: Voice chat with " + currentChatter + " has started.\n");
            currentChats.put(currentChatter, chatDialogue.getText());
            logger.info("Voice Started");
            taskEvents++;
            if (taskList == 1 && taskNumber == 0) {
                setTaskCompleted();
            }
            if (taskList == 2 && taskNumber == 7) {
                setTaskCompleted();
            }

        } else if (!voiceChatButton.isSelected()) {
            String temp = chatDialogue.getText();
            voiceChatButton.setText("Start Voice");
            chatDialogue.setText(temp + "\n" + "\n VOICE CHAT ENDED: Voice chat with " + currentChatter + " has ended.\n");
            currentChats.put(currentChatter, chatDialogue.getText());
            logger.info("Voice Ended");
            taskEvents++;
            if (taskList == 1 && taskNumber == 1) {
                setTaskCompleted();
            }
            if (taskList == 2 && taskNumber == 8) {
                setTaskCompleted();
            }
        }
    }//GEN-LAST:event_voiceChatButtonMouseClicked

    private void userInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userInputKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_userInputKeyTyped

    private void addFriendWindowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addFriendWindowKeyPressed
        if (evt.getKeyCode() == 10) {
            String friendName = friendNameText.getText();
            friendListArray.add(friendName);
            //presentFriendList = (String[])friendListArray.toArray();
            friendListModel.addElement(friendName);
            friendList.setModel(friendListModel);
            addFriendWindow.setVisible(false);
            int size = friendListArray.size();
            if (size > 1) {
                friendlistStatus.setText(size + " Friends");
            } else {
                friendlistStatus.setText(size + " Friend");
            }

        }
    }//GEN-LAST:event_addFriendWindowKeyPressed

    private void testStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testStartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_testStartButtonActionPerformed

    private void practiceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_practiceButtonMouseClicked
        if (!isPracticeStarted) {
            startPractice();
        } else {
        }
    }//GEN-LAST:event_practiceButtonMouseClicked

    private void userInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userInputMouseClicked
        taskEvents++;
    }//GEN-LAST:event_userInputMouseClicked

    private void friendsTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_friendsTabStateChanged
        taskEvents++;
        if (friendsTab.getSelectedIndex() == 1) {
            searchChats1.setText("");
           
        }
        if (friendsTab.getSelectedIndex() == 0) {
            searchFriends1.setText("");
            
        }
    }//GEN-LAST:event_friendsTabStateChanged

    private void removeFriendsButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_removeFriendsButtonFocusLost
        removeFriendsButton.setEnabled(false);
    }//GEN-LAST:event_removeFriendsButtonFocusLost

    private void testStartButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testStartButtonMouseClicked
        if (!isTestOver) {
            startTest();
        } else {
            resultText.setText(practiceResultsStr + testResultsStr);
            resultWindow.setVisible(true);
        }

    }//GEN-LAST:event_testStartButtonMouseClicked

    private void chatWindowWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chatWindowWindowClosed
    }//GEN-LAST:event_chatWindowWindowClosed

    private void chatWindowWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_chatWindowWindowClosing
        String currentConversation = currentChats.get(currentChatter) + "\n Auto saved chat history with " + currentChatter + "\n";
        String previous = chatHistoryMap.get(currentChatter);
        if (savedChatsMap.containsKey(currentChatter + "-Autosaved")) {
            savedChatsMap.put(currentChatter + "-Autosaved", previous + currentConversation);
        } else {
            savedChatListModel.addElement(currentChatter + "-Autosaved");
            savedChatsMap.put(currentChatter + "-Autosaved", previous + currentConversation);
        }
        logger.info("Chat is Auto saved.");
    }//GEN-LAST:event_chatWindowWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Troubleshooter;
    private javax.swing.JMenuItem aboutUsMenu;
    private javax.swing.JButton addFriendButton;
    private javax.swing.JFrame addFriendWindow;
    private javax.swing.JButton addFriendsButton;
    private javax.swing.JMenuItem availableStatus;
    private javax.swing.JMenuItem awayStatus;
    private javax.swing.JMenuItem busyStatus;
    private javax.swing.JTextPane chatDialogue;
    private javax.swing.JScrollPane chatList;
    private javax.swing.JFrame chatWindow;
    private javax.swing.JMenuItem copyMenu;
    private javax.swing.JMenuItem cutMenu;
    private javax.swing.JLabel friendEmailLabel;
    private javax.swing.JTextField friendEmailText;
    private javax.swing.JList friendList;
    private javax.swing.JLabel friendNameLabel;
    private javax.swing.JTextField friendNameText;
    private javax.swing.JLabel friendlistStatus;
    private javax.swing.JTabbedPane friendsTab;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem homeStatus;
    private javax.swing.JTextPane instructionText;
    private javax.swing.JMenuItem invisibleStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem officeStatus;
    private javax.swing.JMenu otherStatus;
    private javax.swing.JMenuItem pasteMenu;
    private javax.swing.JButton practiceButton;
    private javax.swing.JButton removeCancel;
    private javax.swing.JFrame removeFriendWindow;
    private javax.swing.JButton removeFriendsButton;
    private javax.swing.JButton removeYes;
    private javax.swing.JTextPane resultText;
    private javax.swing.JFrame resultWindow;
    private javax.swing.JButton saveChat;
    private javax.swing.JList savedChatsList;
    private javax.swing.JMenuItem schoolStatus;
    private org.jdesktop.swingx.JXSearchField searchChats1;
    private org.jdesktop.swingx.JXSearchField searchFriends1;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel statusIcon;
    private javax.swing.JMenu statusMenu;
    private javax.swing.JButton testStartButton;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenuItem topicHelpMenu;
    private javax.swing.JTextArea userInput;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userStatus;
    private javax.swing.JToggleButton voiceChatButton;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel friendListModel = new DefaultListModel();
    private ArrayList friendListArray = new ArrayList();
    private DefaultListModel savedChatListModel = new DefaultListModel();
    private HashMap<String, String> savedChatsMap = new HashMap<String, String>();
    private ArrayList savedChatListArray = new ArrayList();
    private String[] presentFriendList;
    private String selectedFriend;

    private void addToChat() {

        String typed = userInput.getText();
        if (!typed.trim().equals("")) {
            userInput.setText("".trim());

            String displayText = "Me:\n" + typed.trim();
            String temp = currentChats.get(currentChatter);
            if (temp == null) {
                temp = "";
            }

            chatDialogue.setText(temp + "\n" + displayText + "\n");
            currentChats.put(currentChatter, chatDialogue.getText());

            String resp = (String) vir.getResponse(typed);

            resp = currentChatter + ":\n" + resp.trim();
            temp = chatDialogue.getText();


            chatDialogue.setText(temp + "\n" + resp + "\n");
            currentChats.put(currentChatter, chatDialogue.getText());
        }




    }

    private void initialiseChatWindow() {


        currentChatter = (String) friendList.getSelectedValue();
        if (currentChatter != null) {
            chatWindow.setVisible(true);
            chatWindow.setTitle(("Chat with " + currentChatter));
            chatDialogue.setText(currentChats.get(currentChatter));
            taskEvents++;
            enableChatComponents();
        }


    }

    private void initialiseChatWindowForHistory() {

        currentChatterHistory = (String) savedChatsList.getSelectedValue();
        if (currentChatterHistory != null) {
            chatWindow.setVisible(true);
            chatWindow.setTitle(("Chat with " + currentChatterHistory));
            chatDialogue.setText(savedChatsMap.get(currentChatterHistory));
            disableChatComponents();
        }
    }

    private void disableChatComponents() {
        saveChat.setEnabled(false);
        sendButton.setEnabled(false);
        userInput.setEnabled(false);
        voiceChatButton.setEnabled(false);
        volumeSlider.setEnabled(false);

    }

    private void enableChatComponents() {
        saveChat.setEnabled(true);
        sendButton.setEnabled(true);
        userInput.setEnabled(true);
        voiceChatButton.setEnabled(true);
        volumeSlider.setEnabled(true);
    }

    private void startPractice() {

        taskEvents=0;
        isPracticeStarted = true;
        taskList = 1;
        taskNumber = 0;
        isTaskComplete = false;
        setNextTask();
        logger.info("Practice started");

    }

    private void setNextTask() {

        if (taskList == 1) {
            for (int i = 0; i < practiceStr.length; i++) {
                if (taskNumber == i) {

                    instructionText.setText(practiceStr[i]);
                    taskStartTime = System.currentTimeMillis();
                    break;
                }

            }

        }
        if (taskList == 2) {
            for (int i = 0; i < testStr.length; i++) {
                if (taskNumber == i) {

                    instructionText.setText(testStr[i]);
                    taskStartTime = System.currentTimeMillis();
                    break;
                }

            }
        }
    }

    private void setTaskCompleted() {


        taskEndTime = System.currentTimeMillis();
        double taskTime = calculateTaskTime();
        logger.info("Task Type = " + taskList + " TaskNo = " + taskNumber + " Task Evennts = " + taskEvents + " " + "Task time = " + taskTime);
        int taskErrors = calculateTaskError();

        if (taskList == 1) {
            String temp = "\nTask Type = PRACTICE\n" + "Task = " + practiceStr[taskNumber] + "\nErrors = " + taskErrors + "\nTask time = " + taskTime + "\n\n";
            practiceResultsStr = practiceResultsStr + temp;

            initialiseNextTask();

        }
        if (taskList == 2) {
            String temp = "\nTask Type = TEST\n" + "Task = " + testStr[taskNumber] + "\nErrors = " + taskErrors + "\nTask time = " + taskTime + "\n\n";
            testResultsStr = testResultsStr + temp;

            initialiseNextTask();
        }
    }

    private double calculateTaskTime() {
        return (double) ((taskEndTime - taskStartTime) / 1000.000);
    }

    private int calculateTaskError() {
        int errorCount = 0;
        if (taskList == 1) {
            int correctCount = practiceTaskEvents.get(taskNumber);
            errorCount = Math.abs(taskEvents - correctCount);

        }
        if (taskList == 2) {
            int correctCount = testTaskEvents.get(taskNumber);
            errorCount = Math.abs(taskEvents - correctCount);

        }
        return errorCount;
    }

    private void initialiseNextTask() {
        taskEvents = 0;
        taskNumber++;
        if (taskNumber <= 9) {
            setNextTask();
        } else {
            if (taskList == 1) {
                String temp = "Congratuations! You have successfully completed the practice run." + "\nYou may begin the test now.";
                instructionText.setText(temp);

                practiceButton.setEnabled(false);
                testStartButton.setEnabled(true);
                logger.info("Practice ended");

            }
            if (taskList == 2) {
                String temp = "Congratuations! You have successfully completed the Test run." + "\nThank you. You can send me the results.";
                instructionText.setText(temp);
                System.out.println(practiceResultsStr);
                System.out.println(testResultsStr);
                isTestOver = true;
                testStartButton.setText("Show Results");
                logger.info("Test ended");
            }
        }
    }

    private void startTest() {

        taskEvents=0;
        taskList = 2;
        taskNumber = 0;
        isTaskComplete = false;
        setNextTask();
        logger.info("Test Started");
    }
}
