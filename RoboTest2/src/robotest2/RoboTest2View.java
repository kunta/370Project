package robotest2;

import java.awt.CardLayout;
import java.awt.Component;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 * The application's main frame.
 */
public class RoboTest2View extends FrameView {

    RoboController roboController = new RoboController();
    // The List Models for the Transcript area
    DefaultListModel scienceModel = new DefaultListModel();
    DefaultListModel mathStatsModel = new DefaultListModel();
    DefaultListModel compsciModel = new DefaultListModel();
    DefaultListModel humanitiesModel = new DefaultListModel();
    DefaultListModel socialModel = new DefaultListModel();
    DefaultListModel mathModel = new DefaultListModel();
    DefaultListModel generalModel = new DefaultListModel();
    DefaultListModel electiveModel = new DefaultListModel();
    DefaultListModel DeletedCourses = new DefaultListModel();   //List for deleted courses. Had to be cross functions.
    DefaultComboBoxModel timetableSelectModel = new DefaultComboBoxModel();
    int CourseNum = 0;
    int Semester = 1;

    public RoboTest2View(SingleFrameApplication app) {
        super(app);

        // Retrieve courses from the database
        roboController.ImportCourseCatalog();
        initComponents();
        
        Template.currentTemplates = roboController.GetTemplatesFromDB();
        
        // For some reason this is the only place that
        // it will let me load the template values in.
        for (String s : Template.templateNameList) {
            jcomboProgram.addItem(s);
        }
        for (String s : Timetable.timetableNameList) {
            jcomboTimetable.addItem(s);
        }

        // This whole status bar section is a leftover from the initial code made
        // by the sample project in netbeans. I've left it here because I'm lazy and
        // all sorts of things break when I try and delete it...

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                }
            }
        });
        // Khaled
        jlistSchedule.setVisible(false); // Hides the delete course list since timetable has no classes

        DefaultListModel Courses = new DefaultListModel();

        for (Course C : Course.CourseCatalog) { //Thanks to kevin for the help.
            Courses.addElement(C);
        }
        jlistCourseList.setModel(Courses);


        for (int i = 0; i < 13; i++) { //A for loop to clear timetable, so later code can check if a class is already there before adding
            jtblMonday.setValueAt("", i, 0);
            if (i < 9) {
                jtblTuesday.setValueAt("", i, 0);
            }
        }

    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = RoboTest2App.getApplication().getMainFrame();
            aboutBox = new RoboTest2AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        RoboTest2App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jpanelLogin = new javax.swing.JPanel();
        jlblUsername = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jfieldUsername = new javax.swing.JTextField();
        jbtnLogin = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnRegister = new javax.swing.JButton();
        jlblTitle = new javax.swing.JLabel();
        jlblLoginStatus = new javax.swing.JLabel();
        jfieldPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanelRegister = new javax.swing.JPanel();
        jlblRegisterTitle = new javax.swing.JLabel();
        jlblRegisterName = new javax.swing.JLabel();
        jlblUserPass = new javax.swing.JLabel();
        jtxtRegisterName = new javax.swing.JTextField();
        jbtnRegisterUser = new javax.swing.JButton();
        jlblRegisterStatus = new javax.swing.JLabel();
        jbtnBackToLogin = new javax.swing.JButton();
        jtxtRegisterPass2 = new javax.swing.JPasswordField();
        jlblStudentNumber = new javax.swing.JLabel();
        jtxtStudentNumber = new javax.swing.JTextField();
        jtxtName = new javax.swing.JTextField();
        jlblMajor = new javax.swing.JLabel();
        jlblEmail = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jcheckAdmin = new javax.swing.JCheckBox();
        jlblName = new javax.swing.JLabel();
        jlblAdmin = new javax.swing.JLabel();
        jcheckAddTranscript = new javax.swing.JCheckBox();
        DefaultComboBoxModel programSelectModel = new DefaultComboBoxModel();
        jcomboProgram = new JComboBox(programSelectModel);
        jPanelRegister2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        NewTableModel model = new NewTableModel();
        model.addColumn("Taken");
        model.addColumn("Name");
        model.addColumn("Title");
        model.setRowCount(Course.CourseCatalog.size());
        jtblTranscript = new JTable(model);
        jLabel1 = new javax.swing.JLabel();
        jbtnFinishReg = new javax.swing.JButton();
        jPanelMain = new javax.swing.JPanel();
        jOptionsBar = new javax.swing.JToolBar();
        jlblToolBar = new javax.swing.JLabel();
        jbtnAdmin = new javax.swing.JButton();
        jbtnRandom = new javax.swing.JButton();
        jtabMain = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jpanelTimeBar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jscrollMonday = new javax.swing.JScrollPane();
        jtblMonday = new javax.swing.JTable();
        jtblMonday.setDefaultRenderer(jtblMonday.getColumnClass(-1), new MultiLineCellRenderer());
        int lines = 3;
        jtblMonday.setRowHeight(jtblMonday.getRowHeight() * lines);
        jscrollTuesday = new javax.swing.JScrollPane();
        jtblTuesday = new javax.swing.JTable();
        jtblTuesday.setDefaultRenderer(jtblTuesday.getColumnClass(-1), new MultiLineCellRenderer());
        jtblTuesday.setRowHeight(jtblTuesday.getRowHeight() * lines);
        jscrollWednesday = new javax.swing.JScrollPane();
        jtblWednesday = new javax.swing.JTable();
        jtblWednesday.setDefaultRenderer(jtblWednesday.getColumnClass(-1), new MultiLineCellRenderer());
        jtblWednesday.setRowHeight(jtblWednesday.getRowHeight() * lines);
        jscrollThursday = new javax.swing.JScrollPane();
        jtblThursday = new javax.swing.JTable();
        jtblThursday.setDefaultRenderer(jtblThursday.getColumnClass(-1), new MultiLineCellRenderer());
        jtblThursday.setRowHeight(jtblThursday.getRowHeight() * lines);
        jscrollFriday = new javax.swing.JScrollPane();
        jtblFriday = new javax.swing.JTable();
        jtblFriday.setDefaultRenderer(jtblFriday.getColumnClass(-1), new MultiLineCellRenderer());
        jtblFriday.setRowHeight(jtblFriday.getRowHeight() * lines);
        jbtnAddCourse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultListModel courseModel = new DefaultListModel();
        jlistCourseList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        DefaultListModel scheduleModel = new DefaultListModel();
        jlistSchedule = new javax.swing.JList();
        jlistSchedule.setVisible(false);
        jbtnDeleteCourse = new javax.swing.JButton();
        jcomboTimetable = new javax.swing.JComboBox();
        jbtnSwitchTime = new javax.swing.JButton();
        jpanelProfile = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        persnalInfo = new javax.swing.JTextField();
        jtxtProgMajor = new javax.swing.JTextField();
        jtxtProfileEmail = new javax.swing.JTextField();
        jtxtStudentNum = new javax.swing.JTextField();
        jtxtStudentName = new javax.swing.JTextField();
        jbtnSave = new javax.swing.JButton();
        saveLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlistScience = new JList(scienceModel);
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlistMathStats = new JList(mathStatsModel);
        jLabel25 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jlistCompsci = new JList(compsciModel);
        jLabel26 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jlistHumanities = new JList(humanitiesModel);
        jLabel27 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jlistSocial = new JList(socialModel);
        jLabel28 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jlistMath = new JList(mathModel);
        jLabel29 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jlistGeneral = new JList(generalModel);
        jLabel30 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jlistElectives = new JList(electiveModel);
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(((javax.persistence.Query)null).getResultList());
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jdialAddTimetable = new javax.swing.JDialog();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jtxtTimeName = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jradSept = new javax.swing.JRadioButton();
        jradJan = new javax.swing.JRadioButton();
        jbtnSaveTimetable = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setMinimumSize(new java.awt.Dimension(500, 400));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        mainPanel.setLayout(new java.awt.CardLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(robotest2.RoboTest2App.class).getContext().getResourceMap(RoboTest2View.class);
        jpanelLogin.setForeground(resourceMap.getColor("jpanelLogin.foreground")); // NOI18N
        jpanelLogin.setName("jpanelLogin"); // NOI18N

        jlblUsername.setText(resourceMap.getString("jlblUsername.text")); // NOI18N
        jlblUsername.setName("jlblUsername"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jfieldUsername.setText(resourceMap.getString("jfieldUsername.text")); // NOI18N
        jfieldUsername.setName("jfieldUsername"); // NOI18N

        jbtnLogin.setMnemonic(KeyEvent.VK_ENTER);
        jbtnLogin.setText(resourceMap.getString("jbtnLogin.text")); // NOI18N
        jbtnLogin.setName("jbtnLogin"); // NOI18N
        jbtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(robotest2.RoboTest2App.class).getContext().getActionMap(RoboTest2View.class, this);
        jbtnRegister.setAction(actionMap.get("cardSwap")); // NOI18N
        jbtnRegister.setText(resourceMap.getString("jbtnRegister.text")); // NOI18N
        jbtnRegister.setName("jbtnRegister"); // NOI18N
        jbtnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegisterActionPerformed(evt);
            }
        });

        jlblTitle.setFont(resourceMap.getFont("jlblTitle.font")); // NOI18N
        jlblTitle.setText(resourceMap.getString("jlblTitle.text")); // NOI18N

        jlblLoginStatus.setForeground(resourceMap.getColor("jlblLoginStatus.foreground")); // NOI18N
        jlblLoginStatus.setText(resourceMap.getString("jlblLoginStatus.text")); // NOI18N
        jlblLoginStatus.setName("jlblLoginStatus"); // NOI18N

        jfieldPassword.setText(resourceMap.getString("jfieldPassword.text")); // NOI18N
        jfieldPassword.setName("jfieldPassword"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setActionCommand(resourceMap.getString("jButton1.actionCommand")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        javax.swing.GroupLayout jpanelLoginLayout = new javax.swing.GroupLayout(jpanelLogin);
        jpanelLogin.setLayout(jpanelLoginLayout);
        jpanelLoginLayout.setHorizontalGroup(
            jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblUsername)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblLoginStatus)
                    .addComponent(jlblTitle)
                    .addGroup(jpanelLoginLayout.createSequentialGroup()
                        .addComponent(jbtnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnClear))
                    .addGroup(jpanelLoginLayout.createSequentialGroup()
                        .addComponent(jbtnRegister)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jfieldPassword, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jfieldUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jpanelLoginLayout.setVerticalGroup(
            jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblTitle)
                .addGap(23, 23, 23)
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblUsername)
                    .addComponent(jfieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jfieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnLogin)
                    .addComponent(jbtnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblLoginStatus)
                .addGap(14, 14, 14)
                .addGroup(jpanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnRegister)
                    .addComponent(jButton1))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        mainPanel.add(jpanelLogin, "card1");

        jPanelRegister.setName("jPanelRegister"); // NOI18N

        jlblRegisterTitle.setText(resourceMap.getString("jlblRegisterTitle.text")); // NOI18N
        jlblRegisterTitle.setName("jlblRegisterTitle"); // NOI18N

        jlblRegisterName.setText(resourceMap.getString("jlblRegisterName.text")); // NOI18N
        jlblRegisterName.setName("jlblRegisterName"); // NOI18N

        jlblUserPass.setText(resourceMap.getString("jlblUserPass.text")); // NOI18N
        jlblUserPass.setName("jlblUserPass"); // NOI18N

        jtxtRegisterName.setText(resourceMap.getString("jtxtRegisterName.text")); // NOI18N
        jtxtRegisterName.setName("jtxtRegisterName"); // NOI18N

        jbtnRegisterUser.setText(resourceMap.getString("jbtnRegisterUser.text")); // NOI18N
        jbtnRegisterUser.setName("jbtnRegisterUser"); // NOI18N
        jbtnRegisterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegisterUserActionPerformed(evt);
            }
        });

        jlblRegisterStatus.setText(resourceMap.getString("jlblRegisterStatus.text")); // NOI18N
        jlblRegisterStatus.setToolTipText(resourceMap.getString("jlblRegisterStatus.toolTipText")); // NOI18N
        jlblRegisterStatus.setName("jlblRegisterStatus"); // NOI18N

        jbtnBackToLogin.setText(resourceMap.getString("jbtnBackToLogin.text")); // NOI18N
        jbtnBackToLogin.setName("jbtnBackToLogin"); // NOI18N
        jbtnBackToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBackToLoginActionPerformed(evt);
            }
        });

        jtxtRegisterPass2.setText(resourceMap.getString("jtxtRegisterPass2.text")); // NOI18N
        jtxtRegisterPass2.setName("jtxtRegisterPass2"); // NOI18N

        jlblStudentNumber.setText(resourceMap.getString("jlblStudentNumber.text")); // NOI18N
        jlblStudentNumber.setName("jlblStudentNumber"); // NOI18N

        jtxtStudentNumber.setName("jtxtStudentNumber"); // NOI18N

        jtxtName.setName("jtxtName"); // NOI18N

        jlblMajor.setText(resourceMap.getString("jlblMajor.text")); // NOI18N
        jlblMajor.setName("jlblMajor"); // NOI18N

        jlblEmail.setText(resourceMap.getString("jlblEmail.text")); // NOI18N
        jlblEmail.setName("jlblEmail"); // NOI18N

        jtxtEmail.setName("jtxtEmail"); // NOI18N

        jcheckAdmin.setName("jcheckAdmin"); // NOI18N

        jlblName.setText(resourceMap.getString("jlblName.text")); // NOI18N
        jlblName.setName("jlblName"); // NOI18N

        jlblAdmin.setText(resourceMap.getString("jlblAdmin.text")); // NOI18N
        jlblAdmin.setName("jlblAdmin"); // NOI18N

        jcheckAddTranscript.setText(resourceMap.getString("jcheckAddTranscript.text")); // NOI18N
        jcheckAddTranscript.setName("jcheckAddTranscript"); // NOI18N

        jcomboProgram.setMaximumRowCount(100);
        jcomboProgram.setModel(programSelectModel);
        jcomboProgram.setName("jcomboProgram"); // NOI18N

        javax.swing.GroupLayout jPanelRegisterLayout = new javax.swing.GroupLayout(jPanelRegister);
        jPanelRegister.setLayout(jPanelRegisterLayout);
        jPanelRegisterLayout.setHorizontalGroup(
            jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegisterLayout.createSequentialGroup()
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblRegisterStatus)
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlblStudentNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtStudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlblMajor)
                        .addGap(18, 18, 18)
                        .addComponent(jcomboProgram, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlblEmail)
                        .addGap(22, 22, 22)
                        .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlblAdmin)
                        .addGap(59, 59, 59)
                        .addComponent(jcheckAdmin))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblRegisterTitle))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlblRegisterName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtRegisterName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblUserPass)
                            .addComponent(jlblName))
                        .addGap(4, 4, 4)
                        .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtRegisterPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtnRegisterUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBackToLogin))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jcheckAddTranscript)))
                .addContainerGap(408, Short.MAX_VALUE))
        );
        jPanelRegisterLayout.setVerticalGroup(
            jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegisterLayout.createSequentialGroup()
                .addComponent(jlblRegisterStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblRegisterTitle)
                .addGap(18, 18, 18)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblRegisterName)
                    .addComponent(jtxtRegisterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jlblUserPass))
                    .addComponent(jtxtRegisterPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jlblName))
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jtxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblStudentNumber)
                    .addComponent(jtxtStudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblMajor)
                    .addComponent(jcomboProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jlblEmail))
                    .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRegisterLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jlblAdmin))
                    .addComponent(jcheckAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcheckAddTranscript)
                .addGap(13, 13, 13)
                .addGroup(jPanelRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnRegisterUser)
                    .addComponent(jbtnBackToLogin))
                .addContainerGap(306, Short.MAX_VALUE))
        );

        mainPanel.add(jPanelRegister, "card2");

        jPanelRegister2.setName("jPanelRegister2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jtblTranscript.setModel(model);
        jtblTranscript.setName("jtblTranscript"); // NOI18N
        jtblTranscript.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtblTranscript);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jbtnFinishReg.setText(resourceMap.getString("jbtnFinishReg.text")); // NOI18N
        jbtnFinishReg.setName("jbtnFinishReg"); // NOI18N
        jbtnFinishReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFinishRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRegister2Layout = new javax.swing.GroupLayout(jPanelRegister2);
        jPanelRegister2.setLayout(jPanelRegister2Layout);
        jPanelRegister2Layout.setHorizontalGroup(
            jPanelRegister2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegister2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRegister2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFinishReg, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanelRegister2Layout.setVerticalGroup(
            jPanelRegister2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRegister2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jbtnFinishReg)
                .addContainerGap())
        );

        mainPanel.add(jPanelRegister2, "card3");
        int iter = 0;
        for(Course c: Course.CourseCatalog){
            jtblTranscript.setValueAt(c.getCourseName(), iter, 1);
            jtblTranscript.setValueAt(c.getCourseTitle(), iter, 2);

            iter++;
        }

        jPanelMain.setName("jPanelMain"); // NOI18N

        jOptionsBar.setFloatable(false);
        jOptionsBar.setRollover(true);
        jOptionsBar.setToolTipText(resourceMap.getString("jtlbarSchedule.toolTipText")); // NOI18N
        jOptionsBar.setName("jtlbarSchedule"); // NOI18N

        jlblToolBar.setText(resourceMap.getString("jlblToolBar.text")); // NOI18N
        jlblToolBar.setName("jlblToolBar"); // NOI18N
        jOptionsBar.add(jlblToolBar);

        jbtnAdmin.setText(resourceMap.getString("jbtnAdmin.text")); // NOI18N
        jbtnAdmin.setFocusable(false);
        jbtnAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnAdmin.setName("jbtnAdmin"); // NOI18N
        jbtnAdmin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminActionPerformed(evt);
            }
        });
        jOptionsBar.add(jbtnAdmin);
        jbtnAdmin.setVisible(false);

        jbtnRandom.setText(resourceMap.getString("jbtnRandom.text")); // NOI18N
        jbtnRandom.setFocusable(false);
        jbtnRandom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnRandom.setName("jbtnRandom"); // NOI18N
        jbtnRandom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRandomActionPerformed(evt);
            }
        });
        jOptionsBar.add(jbtnRandom);

        jtabMain.setName("jtabMain"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jpanelTimeBar.setName("jpanelTimeBar"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setName("jLabel23"); // NOI18N

        javax.swing.GroupLayout jpanelTimeBarLayout = new javax.swing.GroupLayout(jpanelTimeBar);
        jpanelTimeBar.setLayout(jpanelTimeBarLayout);
        jpanelTimeBarLayout.setHorizontalGroup(
            jpanelTimeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelTimeBarLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jpanelTimeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jpanelTimeBarLayout.setVerticalGroup(
            jpanelTimeBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelTimeBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(779, Short.MAX_VALUE))
        );

        jscrollMonday.setName("jscrollMonday"); // NOI18N

        jtblMonday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Monday(M)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMonday.setName("jtblMonday"); // NOI18N
        jtblMonday.setPreferredSize(new java.awt.Dimension(500, 750));
        jtblMonday.setRowHeight(45);
        jtblMonday.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtblMonday.setShowVerticalLines(false);
        jtblMonday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblMondayFocusLost(evt);
            }
        });
        jscrollMonday.setViewportView(jtblMonday);
        jtblMonday.getAccessibleContext().setAccessibleParent(jPanel1);

        jscrollTuesday.setName("jscrollTuesday"); // NOI18N

        jtblTuesday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tuesday(T)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblTuesday.setName("jtblTuesday"); // NOI18N
        jtblTuesday.setPreferredSize(new java.awt.Dimension(500, 750));
        jtblTuesday.setRowHeight(68);
        jtblTuesday.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtblTuesday.setShowVerticalLines(false);
        jtblTuesday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblTuesdayFocusLost(evt);
            }
        });
        jscrollTuesday.setViewportView(jtblTuesday);
        jtblTuesday.getAccessibleContext().setAccessibleParent(jPanel1);

        jscrollWednesday.setName("jscrollWednesday"); // NOI18N

        jtblWednesday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Wednesday(W)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblWednesday.setName("jtblWednesday"); // NOI18N
        jtblWednesday.setPreferredSize(new java.awt.Dimension(500, 750));
        jtblWednesday.setRowHeight(45);
        jtblWednesday.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtblWednesday.setShowVerticalLines(false);
        jtblWednesday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblWednesdayFocusLost(evt);
            }
        });
        jscrollWednesday.setViewportView(jtblWednesday);
        jtblWednesday.getAccessibleContext().setAccessibleParent(jPanel1);

        jscrollThursday.setName("jscrollThursday"); // NOI18N

        jtblThursday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Thursday(R)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblThursday.setName("jtblThursday"); // NOI18N
        jtblThursday.setPreferredSize(new java.awt.Dimension(500, 750));
        jtblThursday.setRowHeight(68);
        jtblThursday.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtblThursday.setShowVerticalLines(false);
        jtblThursday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblThursdayFocusLost(evt);
            }
        });
        jscrollThursday.setViewportView(jtblThursday);
        jtblThursday.getAccessibleContext().setAccessibleParent(jPanel1);

        jscrollFriday.setName("jscrollFriday"); // NOI18N

        jtblFriday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Friday(F)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblFriday.setName("jtblFriday"); // NOI18N
        jtblFriday.setPreferredSize(new java.awt.Dimension(500, 750));
        jtblFriday.setRowHeight(45);
        jtblFriday.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtblFriday.setShowVerticalLines(false);
        jtblFriday.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblFridayFocusLost(evt);
            }
        });
        jscrollFriday.setViewportView(jtblFriday);
        jtblFriday.getAccessibleContext().setAccessibleParent(jPanel1);

        jbtnAddCourse.setText(resourceMap.getString("jbtnAddCourse.text")); // NOI18N
        jbtnAddCourse.setName("jbtnAddCourse"); // NOI18N
        jbtnAddCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddCourseActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jlistCourseList.setModel(courseModel);
        jlistCourseList.setName("jlistCourseList"); // NOI18N
        jScrollPane1.setViewportView(jlistCourseList);
        for(Course C:Course.CourseCatalog) {        //Thanks to kevin for the help.
            courseModel.addElement(C.getCourseName());
        }

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jlistSchedule.setModel(scheduleModel);
        jlistSchedule.setName("jlistSchedule"); // NOI18N
        jScrollPane3.setViewportView(jlistSchedule);

        jbtnDeleteCourse.setAction(actionMap.get("deleteCourse")); // NOI18N
        jbtnDeleteCourse.setText(resourceMap.getString("jbtnDeleteCourse.text")); // NOI18N
        jbtnDeleteCourse.setName("jbtnDeleteCourse"); // NOI18N
        jbtnDeleteCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteCourseActionPerformed(evt);
            }
        });

        jcomboTimetable.setModel(timetableSelectModel);
        jcomboTimetable.setName("jcomboTimetable"); // NOI18N

        jbtnSwitchTime.setText(resourceMap.getString("jbtnSwitchTime.text")); // NOI18N
        jbtnSwitchTime.setName("jbtnSwitchTime"); // NOI18N
        jbtnSwitchTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSwitchTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpanelTimeBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollMonday, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jscrollThursday, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jscrollFriday, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnSwitchTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnAddCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                    .addComponent(jbtnDeleteCourse)
                    .addComponent(jcomboTimetable, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jscrollFriday, jscrollMonday, jscrollThursday, jscrollTuesday, jscrollWednesday});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcomboTimetable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSwitchTime)
                        .addGap(98, 98, 98)
                        .addComponent(jbtnAddCourse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jbtnDeleteCourse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanelTimeBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jscrollFriday, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jscrollMonday, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jscrollTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jscrollWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jscrollThursday, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jscrollFriday, jscrollMonday, jscrollThursday, jscrollTuesday, jscrollWednesday});

        jtabMain.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jpanelProfile.setName("jpanelProfile"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        persnalInfo.setText(resourceMap.getString("persnalInfo.text")); // NOI18N
        persnalInfo.setName("persnalInfo"); // NOI18N

        jtxtProgMajor.setText(resourceMap.getString("jtxtProgMajor.text")); // NOI18N
        jtxtProgMajor.setName("jtxtProgMajor"); // NOI18N

        jtxtProfileEmail.setText(resourceMap.getString("jtxtProfileEmail.text")); // NOI18N
        jtxtProfileEmail.setName("jtxtProfileEmail"); // NOI18N

        jtxtStudentNum.setText(resourceMap.getString("jtxtStudentNum.text")); // NOI18N
        jtxtStudentNum.setName("jtxtStudentNum"); // NOI18N

        jtxtStudentName.setName("jtxtStudentName"); // NOI18N

        jbtnSave.setText(resourceMap.getString("jbtnSave.text")); // NOI18N
        jbtnSave.setName("jbtnSave"); // NOI18N
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        saveLabel.setText(resourceMap.getString("saveLabel.text")); // NOI18N
        saveLabel.setName("saveLabel"); // NOI18N

        javax.swing.GroupLayout jpanelProfileLayout = new javax.swing.GroupLayout(jpanelProfile);
        jpanelProfile.setLayout(jpanelProfileLayout);
        jpanelProfileLayout.setHorizontalGroup(
            jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelProfileLayout.createSequentialGroup()
                        .addComponent(saveLabel)
                        .addGap(110, 110, 110)
                        .addComponent(jbtnSave))
                    .addComponent(persnalInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGroup(jpanelProfileLayout.createSequentialGroup()
                        .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(8, 8, 8)
                        .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtStudentNum)
                            .addComponent(jtxtProfileEmail)
                            .addComponent(jtxtProgMajor)
                            .addComponent(jtxtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jpanelProfileLayout.setVerticalGroup(
            jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtStudentNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelProfileLayout.createSequentialGroup()
                        .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtProfileEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jtxtProgMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(persnalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSave)
                    .addComponent(saveLabel))
                .addContainerGap(486, Short.MAX_VALUE))
        );

        jtabMain.addTab("Profile", jpanelProfile);

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jlistScience.setModel(scienceModel);
        jlistScience.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistScience.setName("jlistScience"); // NOI18N
        jScrollPane4.setViewportView(jlistScience);

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jlistMathStats.setModel(mathStatsModel);
        jlistMathStats.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistMathStats.setName("jlistMathStats"); // NOI18N
        jScrollPane5.setViewportView(jlistMathStats);

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        jlistCompsci.setModel(compsciModel);
        jlistCompsci.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistCompsci.setName("jlistCompsci"); // NOI18N
        jScrollPane6.setViewportView(jlistCompsci);

        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jlistHumanities.setModel(humanitiesModel);
        jlistHumanities.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistHumanities.setName("jlistHumanities"); // NOI18N
        jScrollPane7.setViewportView(jlistHumanities);

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jlistSocial.setModel(socialModel);
        jlistSocial.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistSocial.setName("jlistSocial"); // NOI18N
        jScrollPane8.setViewportView(jlistSocial);

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        jlistMath.setModel(mathModel);
        jlistMath.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistMath.setName("jlistMath"); // NOI18N
        jScrollPane9.setViewportView(jlistMath);

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        jlistGeneral.setModel(generalModel);
        jlistGeneral.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistGeneral.setName("jlistGeneral"); // NOI18N
        jScrollPane10.setViewportView(jlistGeneral);

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        jlistElectives.setModel(electiveModel);
        jlistElectives.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlistElectives.setName("jlistElectives"); // NOI18N
        jScrollPane11.setViewportView(jlistElectives);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5)
                        .addComponent(jScrollPane4)
                        .addComponent(jLabel6)
                        .addComponent(jLabel24)
                        .addComponent(jLabel25)))
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(298, 298, 298))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING))
                            .addContainerGap(102, Short.MAX_VALUE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(267, 267, 267))
        );

        jtabMain.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jtabMain, 0, 0, Short.MAX_VALUE))
                    .addComponent(jOptionsBar, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jOptionsBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 828, Short.MAX_VALUE))
        );

        jtabMain.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N

        mainPanel.add(jPanelMain, "card4");

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        jSeparator2.setName("jSeparator2"); // NOI18N
        fileMenu.add(jSeparator2);

        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 702, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel))
                .addGap(3, 3, 3))
        );

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        jdialAddTimetable.setName("jdialAddTimetable"); // NOI18N

        jLabel31.setText(resourceMap.getString("jLabel31.text")); // NOI18N
        jLabel31.setName("jLabel31"); // NOI18N

        jLabel32.setText(resourceMap.getString("jLabel32.text")); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N

        jtxtTimeName.setText(resourceMap.getString("jtxtTimeName.text")); // NOI18N
        jtxtTimeName.setName("jtxtTimeName"); // NOI18N

        jLabel33.setText(resourceMap.getString("jLabel33.text")); // NOI18N
        jLabel33.setName("jLabel33"); // NOI18N

        buttonGroup1.add(jradSept);
        jradSept.setText(resourceMap.getString("jradSept.text")); // NOI18N
        jradSept.setName("jradSept"); // NOI18N

        buttonGroup1.add(jradJan);
        jradJan.setText(resourceMap.getString("jradJan.text")); // NOI18N
        jradJan.setName("jradJan"); // NOI18N

        jbtnSaveTimetable.setText(resourceMap.getString("jbtnSaveTimetable.text")); // NOI18N
        jbtnSaveTimetable.setName("jbtnSaveTimetable"); // NOI18N
        jbtnSaveTimetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveTimetableActionPerformed(evt);
            }
        });

        jbtnCancel.setText(resourceMap.getString("jbtnCancel.text")); // NOI18N
        jbtnCancel.setName("jbtnCancel"); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jdialAddTimetableLayout = new javax.swing.GroupLayout(jdialAddTimetable.getContentPane());
        jdialAddTimetable.getContentPane().setLayout(jdialAddTimetableLayout);
        jdialAddTimetableLayout.setHorizontalGroup(
            jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdialAddTimetableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jdialAddTimetableLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTimeName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jdialAddTimetableLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jradJan)
                            .addComponent(jradSept)
                            .addComponent(jbtnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbtnSaveTimetable, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jdialAddTimetableLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnCancel, jbtnSaveTimetable});

        jdialAddTimetableLayout.setVerticalGroup(
            jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdialAddTimetableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addGroup(jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jtxtTimeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jradSept))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradJan)
                .addGap(18, 18, 18)
                .addGroup(jdialAddTimetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSaveTimetable)
                    .addComponent(jbtnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
    /**
     *
     * @author Kevin
     */
    private void jbtnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRandomActionPerformed
        Date date = new Date();
        Random rGen = new Random();
        rGen.setSeed(date.getTime());
        
        Course newCourse;
        boolean canAddCourse = false; // Can we add the course to the timetable?
        int randomInt = rGen.nextInt(Course.CourseCatalog.size());
        newCourse = Course.CourseCatalog.get(randomInt);
        
        // If the course isn't in the same semester find a new one
        // Will be changed when timetable is implemented so it can go off
        // whichever semester the user has selected.
        while(newCourse.getSemester() != 1){
            randomInt = rGen.nextInt(Course.CourseCatalog.size());
            newCourse = Course.CourseCatalog.get(randomInt);
        }
        
        while (!canAddCourse) {
            if (!AddCourse(newCourse)) {
                randomInt = rGen.nextInt(Course.CourseCatalog.size());
                newCourse = Course.CourseCatalog.get(randomInt);
                System.out.println("In addCourse loop if course = " + newCourse.getCourseName());
                System.out.println("RandomInt = " + randomInt);
                break;
            } else {
                System.out.println("In addCourse loop else");
                canAddCourse = true;
                break;
            }
        }
        

    }//GEN-LAST:event_jbtnRandomActionPerformed

    private void jbtnSaveTimetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveTimetableActionPerformed
        timetableSelectModel.addElement((String)jtxtTimeName.getText());
        if (jradSept.isSelected()){
            Semester = 1;
        }
        if (jradJan.isSelected()) {
            Semester = 2;
        }
        jdialAddTimetable.setVisible(false);
        jtxtTimeName.setText("");
    }//GEN-LAST:event_jbtnSaveTimetableActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        jdialAddTimetable.setVisible(false);
    }//GEN-LAST:event_jbtnCancelActionPerformed

    private void jbtnSwitchTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSwitchTimeActionPerformed
        ClearTimetable();
        List<Timetable> results = roboController.GetTimetablesFromDB("id", (String)jcomboTimetable.getSelectedItem());
        
        for(Timetable t: results){
            Course newCourse = new Course(t.getCourseName());
            AddCourse(newCourse);
        }
        
    }//GEN-LAST:event_jbtnSwitchTimeActionPerformed

    private void jbtnAddCourseActionPerformed(java.awt.event.ActionEvent evt) {
       // String course = (String)jlistCourseList.getSelectedValue();
        AddCourse((Course)jlistCourseList.getSelectedValue());
    }

    private boolean AddCourse(Course courseName){
        // Khaled
        Course temp;           // Decleration of local variables to use temporarly for function.
        Timetable newTimetable = new Timetable();
        String timetableName = (String)jcomboTimetable.getSelectedItem();
        int row = 0;
        boolean toReturn = false;
        
        //temp = Course.CourseCatalog.get(Course.CourseCatalog.indexOf(roboController.GetCourseFromDB(courseName.getCourseName())));
        temp = (Course) jlistCourseList.getSelectedValue();

        if (temp.getDays().equals("MWF")) {                               //If then statement to determine the location a course should be put in
            if (temp.getStartTime() == 830) {
                row = 0;
            } else if (temp.getStartTime() == 930) {
                row = 1;
            } else if (temp.getStartTime() == 1030) {
                row = 2;
            } else if (temp.getStartTime() == 1130) {
                row = 3;
            } else if (temp.getStartTime() == 1230) {
                row = 4;
            } else if (temp.getStartTime() == 1330) {
                row = 5;
            } else if (temp.getStartTime() == 1430) {
                row = 6;
            } else if (temp.getStartTime() == 1530) {
                row = 7;
            } else if (temp.getStartTime() == 1630) {
                row = 8;
            } else if (temp.getStartTime() == 1730) {
                row = 9;
            } else if (temp.getStartTime() == 1830) {
                row = 10;
            } else if (temp.getStartTime() == 1930) {
                row = 11;
            } else if (temp.getStartTime() == 2030) {
                row = 12;
            }

            if (jtblMonday.getValueAt(row, 0).toString().isEmpty() && CourseNum < 5) {      //puts the course info on the timetable. And checks the number of courses 
                
                jtblMonday.setValueAt(temp.getCourseName() + "\n" + temp.getCrn() + "\n" + temp.getProfessor(), row, 0);
                jtblWednesday.setValueAt(temp.getCourseName() + "\n" + temp.getCrn() + "\n" + temp.getProfessor(), row, 0);
                jtblFriday.setValueAt(temp.getCourseName() + "\n" + temp.getCrn() + "\n" + temp.getProfessor(), row, 0);
                DeletedCourses.addElement(temp);                                        //Adds the course to the delete course list for future deletion
                CourseNum += 1;
                
                newTimetable.setCourseName(temp.getCourseName());
                newTimetable.setUsername(Student.currentStudent.getUsername());
                newTimetable.setSem(Semester);
                newTimetable.setTimetableID(timetableName);
                roboController.AddTimetableToDB(newTimetable);
                
                toReturn = true;// Keeps track of the number of courses
            } else {
                System.out.println("Days(MWF) else");
                toReturn = false;
            }
        }

        if (temp.getDays().equals("TR")) {                                        //Same as above, but for tuesday and thursday classes
            if (temp.getStartTime() == 830) {
                row = 0;
            } else if (temp.getStartTime() == 1000) {
                row = 1;
            } else if (temp.getStartTime() == 1130) {
                row = 2;
            } else if (temp.getStartTime() == 1300) {
                row = 3;
            } else if (temp.getStartTime() == 1430) {
                row = 4;
            } else if (temp.getStartTime() == 1600) {
                row = 5;
            } else if (temp.getStartTime() == 1730) {
                row = 6;
            } else if (temp.getStartTime() == 1900) {
                row = 7;
            } else if (temp.getStartTime() == 2030) {
                row = 8;
            }



            if (jtblTuesday.getValueAt(row, 0).toString().isEmpty() && CourseNum < 5) {
                jtblTuesday.setValueAt(temp.getCourseName() + "\n" + temp.getCrn() + "\n" + temp.getProfessor(), row, 0);
                jtblThursday.setValueAt(temp.getCourseName() + "\n" + temp.getCrn() + "\n" + temp.getProfessor(), row, 0);
                DeletedCourses.addElement(temp);
                CourseNum += 1;
                
                newTimetable.setCourseName(temp.getCourseName());
                newTimetable.setUsername(Student.currentStudent.getUsername());
                newTimetable.setSem(Semester);
                newTimetable.setTimetableID(timetableName);
                roboController.AddTimetableToDB(newTimetable);

                toReturn = true;
            } else {
                System.out.println("Days(TR) else");
                toReturn = false;
            }
        }

        jlistSchedule.setVisible(true);
        jlistSchedule.setModel(DeletedCourses);
        
        return toReturn;
    }
    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        saveLabel.setVisible(true);
        if (Student.currentStudent.getName().equals(jtxtStudentName.getText()) ||
                Student.currentStudent.getEmail().equals(jtxtEmail.getText()) ||
                Student.currentStudent.getStudentNo().equals(String.valueOf(jtxtStudentNum.getText()))) {
            
            Student.currentStudent.setName(jtxtStudentName.getText());
            Student.currentStudent.setEmail(jtxtEmail.getText());
            Student.currentStudent.setStudentNo(Integer.decode(jtxtStudentNum.getText()));
            
            roboController.MergeStudentToDB(Student.currentStudent);
        }
//        jlistSchedule.setVisible(true);                                        //Updates the delete course list and makes sure its visible/enabled
//        jlistSchedule.setModel(DeletedCourses);
    }

    private void jbtnDeleteCourseActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void ClearTimetable(){
        for(int i = 0;i < jtblMonday.getRowCount();i++){
            jtblMonday.setValueAt("", i, 0);
            jtblWednesday.setValueAt("", i, 0);
            jtblFriday.setValueAt("", i, 0);
        }
        for(int i = 0;i < jtblTuesday.getRowCount();i++){
            jtblTuesday.setValueAt("", i, 0);
            jtblThursday.setValueAt("", i, 0);
        }
    }
    /**
     *
     * @author Kevin
     */
    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {
        jfieldUsername.setText("");
        jfieldPassword.setText("");
        jlblLoginStatus.setText("");
    }

    /**
     *
     * @author Kevin
     */
    private void jbtnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        char[] password = jfieldPassword.getPassword();

        // Sets up the entity manager which deals with our database.
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        try {
            eManager.getTransaction().begin();

            // Creates a new query and uses the named query 'Student.findByUserName'
            // which is found in the Student.java file. You can write queries beforehand
            // and reference them by the shortname instead of writing them out everytime.
            // Parameters that are inserted in (here the username) get filled in via
            // the setParameter function.
            Query studentQ = eManager.createNamedQuery("Student.findByUsername").setParameter("username", jfieldUsername.getText());

            // Puts the result (username and password) into the Student object
            // which has userName and password fields which are accessed by
            // user.getPassword() and user.getUsername()
            Student loginStudent = (Student) studentQ.getSingleResult();
            eManager.getTransaction().commit();

            // Checking if the username/password is correct
            if (!isPasswordCorrect(password, loginStudent.getPassword())) {
                jlblLoginStatus.setText("Username/Password are invalid. Please try again!");
                jfieldPassword.selectAll();
                return;
            } else {
                // Loads all of the Student data into the 
                // currentStudent object which is used for further
                // references instead of querying the database for it.
                Student.currentStudent.setName(loginStudent.getName());
                Student.currentStudent.setUsername(loginStudent.getUsername());
                Student.currentStudent.setPassword(loginStudent.getPassword());
                Student.currentStudent.setEmail(loginStudent.getEmail());
                Student.currentStudent.setStudentNo(loginStudent.getStudentNo());
                Student.currentStudent.setAdminUser(loginStudent.getAdminUser());
                Student.currentStudent.setProgramMajor(loginStudent.getProgramMajor());

                if (Student.currentStudent.getAdminUser() == true) {
                    jbtnAdmin.setVisible(true);
                }

                // Switches to the Registration screen
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "card4");

                // Put values into Profile tab
                jtxtStudentName.setText(Student.currentStudent.getName());
                jtxtStudentNum.setText(Student.currentStudent.getStudentNo().toString());
                jtxtProgMajor.setText(Student.currentStudent.getProgramMajor());
                jtxtProfileEmail.setText(Student.currentStudent.getEmail());
                saveLabel.setVisible(false);

                // Get Courses from the users transcript and then load them
                // into the Transcript table.
                roboController.GetUserTranscriptFromDB(Student.currentStudent);
                roboController.GetTimetablesFromDB("byUsername",Student.currentStudent.getUsername());
                LoadTranscript();
            }

        } catch (NoResultException e) {
            jlblLoginStatus.setText("Username/Password are invalid. Please try again!");
            eManager.getTransaction().rollback();
        }
        eManager.close();

    }

    /**
     *
     * @author Kevin
     */
    private void jbtnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "card1");
    }

    /**
     *
     * @author Kevin
     */
    private void jbtnRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {

        // Creating the Entity Manager which interfaces with the database
        EntityManager eManager = Persistence.createEntityManagerFactory("cmpt370group06PU").createEntityManager();

        String str = new String(jtxtRegisterPass2.getPassword());
        int studentNo = Integer.parseInt(jtxtStudentNumber.getText());

        // Set up a try block to catch any failures. ie. User already
        // exists in the system.
        try {
            eManager.getTransaction().begin();
            // Create the global Student object which contains the values from the
            // text fields.
            Student.currentStudent.setName(jtxtName.getText());
            Student.currentStudent.setUsername(jtxtRegisterName.getText());
            Student.currentStudent.setPassword(str);
            Student.currentStudent.setEmail(jtxtEmail.getText());
            Student.currentStudent.setStudentNo(studentNo);
            Student.currentStudent.setAdminUser(jcheckAdmin.isSelected());
            Student.currentStudent.setProgramMajor((String)jcomboProgram.getSelectedItem());

            // The entity manager commands work like this
            // persist = INSERT
            // find = SELECT
            // merge = UPDATE
            // remove = DELETE

            // This command inserts the 'user' object into the database(which
            // contains the username and password fields)
            eManager.persist(Student.currentStudent);

            // Commit the transaction
            eManager.getTransaction().commit();
            jlblRegisterStatus.setText("Registration Success! You may now login.");

        } catch (Exception e) {
            // If something goes wrong roll back any changes
            eManager.getTransaction().rollback();
            jlblRegisterStatus.setText("Username already exists! Please try a different one.");
        }
        // Close the connection to the database.
        eManager.close();

        if (jcheckAddTranscript.isSelected()) {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "card3");
        } else {
            if (Student.currentStudent.getAdminUser() == true) {
                jbtnAdmin.setVisible(true);
            }
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "card1");
        }

    }
    /**
     *
     * @author Kevin
     */
    private void jbtnBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "card1");
    }

    /**
     *
     * @author Kevin
     */
    private void jbtnAdminActionPerformed(java.awt.event.ActionEvent evt) {
        RoboAdvisorAdmin admin = new RoboAdvisorAdmin();
        admin.setVisible(true);
    }

    /**
     *
     * @author Kevin
     */
    private void jtblFridayFocusLost(java.awt.event.FocusEvent evt) {
        jtblFriday.clearSelection();
    }

    /**
     *
     * @author Kevin
     */
    private void jtblThursdayFocusLost(java.awt.event.FocusEvent evt) {
        jtblThursday.clearSelection();
    }

    /**
     *
     * @author Kevin
     */
    private void jtblWednesdayFocusLost(java.awt.event.FocusEvent evt) {
        jtblWednesday.clearSelection();
    }

    /**
     *
     * @author Kevin
     */
    private void jtblTuesdayFocusLost(java.awt.event.FocusEvent evt) {
        jtblTuesday.clearSelection();
    }

    /**
     *
     * @author Kevin
     */
    private void jtblMondayFocusLost(java.awt.event.FocusEvent evt) {
        jtblMonday.clearSelection();
    }

    /**
     *
     * @author Kevin
     */
    private void jbtnFinishRegActionPerformed(java.awt.event.ActionEvent evt) {
        // This code grabs the values from the table and inserts the corresponding
        // entries into the database. 

        String colName = "";
        Boolean colChecked = false;

        for (int i = 0; i < Course.CourseCatalog.size(); i++) {
            Transcript newTranscript = new Transcript();
            colChecked = (Boolean) jtblTranscript.getValueAt(i, 0);
            colName = (String) jtblTranscript.getValueAt(i, 1);
            if (colChecked == null) {
                colChecked = false;
            }

            if (colChecked) {
                newTranscript.setCourseName(colName.toString());
                newTranscript.setUserName(Student.currentStudent.getUsername());
                roboController.AddTranscriptToDB(newTranscript);
            }
        }

        if (Student.currentStudent.getAdminUser() == true) {
            jbtnAdmin.setVisible(true);
        }

        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "card1");
    }

    /**
     *
     * @author Kevin
     */
    private static boolean isPasswordCorrect(char[] input, String correctPass) {
        // This whole method is for extracting the *** from the password field
        // and comparing it with the string which was retrieved from the 
        // database (corectPass)
        boolean isCorrect = true;
        char[] correctPassword = correctPass.toCharArray();


        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, correctPassword);
        }

        // Sets the passed in variable to all zeroes
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    /**
     *
     * @author Kevin
     */
    void LoadTranscript() {
        List<Transcript> newTranList = new LinkedList<Transcript>();
        newTranList = roboController.GetUserTranscriptFromDB(Student.currentStudent);
        List<Template> newTempList = new LinkedList<Template>();
        newTempList = roboController.GetTemplatesFromDB("new");

        // Horrible way of doing it, will change later(maybe :P )
        for (Transcript t : newTranList) {
            for (Template te : newTempList) {
                if (te.getCategory().equals("Natural Science") && !scienceModel.contains((String) te.getCourseName())) {
                    scienceModel.addElement((String) te.getCourseName());
                }
                if (te.getCategory().equals(("Math and Stats")) && !mathStatsModel.contains((String) te.getCourseName())) {
                    mathStatsModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("Computer Science")) && !compsciModel.contains((String) te.getCourseName())) {
                    compsciModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("Humanities")) && !humanitiesModel.contains((String) te.getCourseName())) {
                    humanitiesModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("Social Science")) && !socialModel.contains((String) te.getCourseName())) {
                    socialModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("Cognate Math")) && !mathModel.contains((String) te.getCourseName())) {
                    mathModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("General")) && !generalModel.contains((String) te.getCourseName())) {
                    generalModel.addElement((String) te.getCourseName());
                } 
                if (te.getCategory().equals(("Elective")) && !electiveModel.contains((String) te.getCourseName())) {
                    electiveModel.addElement((String) te.getCourseName());
                } 
            }
        }


    }

    // Function for switching the cards (may or may not get used)
    // It switches to the next card in the order, but I've found the ordering
    // to be a bit strange so the next one may not be the correct one. *shrugs*
    @Action
    public void cardSwap() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.next(mainPanel);
    }

    @Action
    public void deleteCourse() {
        // Khaled

        Course temp2 = new Course();
        int row = 0;


        temp2 = (Course) jlistSchedule.getSelectedValue();

        if (temp2.getDays().equals("MWF")) {
            if (temp2.getStartTime() == 830) {
                row = 0;
            } else if (temp2.getStartTime() == 930) {
                row = 1;
            } else if (temp2.getStartTime() == 1030) {
                row = 2;
            } else if (temp2.getStartTime() == 1130) {
                row = 3;
            } else if (temp2.getStartTime() == 1230) {
                row = 4;
            } else if (temp2.getStartTime() == 1330) {
                row = 5;
            } else if (temp2.getStartTime() == 1430) {
                row = 6;
            } else if (temp2.getStartTime() == 1530) {
                row = 7;
            } else if (temp2.getStartTime() == 1630) {
                row = 8;
            } else if (temp2.getStartTime() == 1730) {
                row = 9;
            } else if (temp2.getStartTime() == 1830) {
                row = 10;
            } else if (temp2.getStartTime() == 1930) {
                row = 11;
            } else if (temp2.getStartTime() == 2030) {
                row = 12;
            }

            DeletedCourses.removeElement(temp2);
            jtblMonday.setValueAt("", row, 0);
            jtblWednesday.setValueAt("", row, 0);
            jtblFriday.setValueAt("", row, 0);
            CourseNum -= 1;
        }

        if (temp2.getDays().equals("TR")) {
            if (temp2.getStartTime() == 830) {
                row = 0;
            } else if (temp2.getStartTime() == 1000) {
                row = 1;
            } else if (temp2.getStartTime() == 1130) {
                row = 2;
            } else if (temp2.getStartTime() == 1300) {
                row = 3;
            } else if (temp2.getStartTime() == 1430) {
                row = 4;
            } else if (temp2.getStartTime() == 1600) {
                row = 5;
            } else if (temp2.getStartTime() == 1730) {
                row = 6;
            } else if (temp2.getStartTime() == 1900) {
                row = 7;
            } else if (temp2.getStartTime() == 2030) {
                row = 8;
            }

            DeletedCourses.removeElement(temp2);
            jtblTuesday.setValueAt("", row, 0);
            jtblThursday.setValueAt("", row, 0);
            CourseNum -= 1;

        }


        jlistSchedule.setModel(DeletedCourses);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jOptionsBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelRegister;
    private javax.swing.JPanel jPanelRegister2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnAddCourse;
    private javax.swing.JButton jbtnAdmin;
    private javax.swing.JButton jbtnBackToLogin;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnDeleteCourse;
    private javax.swing.JButton jbtnFinishReg;
    private javax.swing.JButton jbtnLogin;
    private javax.swing.JButton jbtnRandom;
    private javax.swing.JButton jbtnRegister;
    private javax.swing.JButton jbtnRegisterUser;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveTimetable;
    private javax.swing.JButton jbtnSwitchTime;
    private javax.swing.JCheckBox jcheckAddTranscript;
    private javax.swing.JCheckBox jcheckAdmin;
    private javax.swing.JComboBox jcomboProgram;
    private javax.swing.JComboBox jcomboTimetable;
    private javax.swing.JDialog jdialAddTimetable;
    private javax.swing.JPasswordField jfieldPassword;
    private javax.swing.JTextField jfieldUsername;
    private javax.swing.JLabel jlblAdmin;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblLoginStatus;
    private javax.swing.JLabel jlblMajor;
    private javax.swing.JLabel jlblName;
    private javax.swing.JLabel jlblRegisterName;
    private javax.swing.JLabel jlblRegisterStatus;
    private javax.swing.JLabel jlblRegisterTitle;
    private javax.swing.JLabel jlblStudentNumber;
    private javax.swing.JLabel jlblTitle;
    private javax.swing.JLabel jlblToolBar;
    private javax.swing.JLabel jlblUserPass;
    private javax.swing.JLabel jlblUsername;
    private javax.swing.JList jlistCompsci;
    private javax.swing.JList jlistCourseList;
    private javax.swing.JList jlistElectives;
    private javax.swing.JList jlistGeneral;
    private javax.swing.JList jlistHumanities;
    private javax.swing.JList jlistMath;
    private javax.swing.JList jlistMathStats;
    private javax.swing.JList jlistSchedule;
    private javax.swing.JList jlistScience;
    private javax.swing.JList jlistSocial;
    private javax.swing.JPanel jpanelLogin;
    private javax.swing.JPanel jpanelProfile;
    private javax.swing.JPanel jpanelTimeBar;
    private javax.swing.JRadioButton jradJan;
    private javax.swing.JRadioButton jradSept;
    private javax.swing.JScrollPane jscrollFriday;
    private javax.swing.JScrollPane jscrollMonday;
    private javax.swing.JScrollPane jscrollThursday;
    private javax.swing.JScrollPane jscrollTuesday;
    private javax.swing.JScrollPane jscrollWednesday;
    private javax.swing.JTabbedPane jtabMain;
    private javax.swing.JTable jtblFriday;
    private javax.swing.JTable jtblMonday;
    private javax.swing.JTable jtblThursday;
    private javax.swing.JTable jtblTranscript;
    private javax.swing.JTable jtblTuesday;
    private javax.swing.JTable jtblWednesday;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtName;
    private javax.swing.JTextField jtxtProfileEmail;
    private javax.swing.JTextField jtxtProgMajor;
    private javax.swing.JTextField jtxtRegisterName;
    private javax.swing.JPasswordField jtxtRegisterPass2;
    private javax.swing.JTextField jtxtStudentName;
    private javax.swing.JTextField jtxtStudentNum;
    private javax.swing.JTextField jtxtStudentNumber;
    private javax.swing.JTextField jtxtTimeName;
    private java.util.List<robotest2.Test> list;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField persnalInfo;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private boolean saveNeeded;
}

// This class was borrowed from: http://www.java2s.com/Code/Java/Swing-Components/MultiLineCellExample.htm
// so we can have multiple lines in each JTable cell.
class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setFont(table.getFont());
        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            if (table.isCellEditable(row, column)) {
                setForeground(UIManager.getColor("Table.focusCellForeground"));
                setBackground(UIManager.getColor("Table.focusCellBackground"));
            }
        } else {
            setBorder(new EmptyBorder(1, 2, 1, 2));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
