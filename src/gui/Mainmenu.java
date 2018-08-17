package gui;

import static db.ClassSQL.SelectAllstmt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import obj.User;
import utility.ReadWrite;

/**
 * MainMenu first gui that come when user run
 *
 * @author Moss
 */
public class Mainmenu extends JFrame implements ActionListener {

    JButton btnMember = new JButton();
    JButton btnSearch = new JButton();
    JButton btnHelp = new JButton();
    JButton btnBackup = new JButton();
    JButton btnRestore = new JButton();

    JTextField txfBank = new JTextField(10);
    JTextField txfBSB = new JTextField(10);
    JTextField txfAccount = new JTextField(10);
    JTextField txfFirst = new JTextField(10);
    JTextField txfLast = new JTextField(10);
    JTextField txfAddress = new JTextField(10);

    JPanel pnlMenu = new JPanel();
    JPanel pnlHeader = new JPanel();
    JPanel pnlEmployee = new JPanel();
    Container con = getContentPane();

    /**
     * Mainmenu Constructor
     */
    public Mainmenu() {

        ///initiate
        this.setTitle("Soccer Member Registration");
        this.setVisible(true);
        this.setBounds(700, 300, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Panel
        con.add(pnlHeader, BorderLayout.NORTH);
        con.add(pnlMenu, BorderLayout.CENTER);

        //header
        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(Mainmenu.class.getResource("/icon/header.png")));
        pnlHeader.add(imgLabel);
        pnlHeader.setBackground(Color.white);

        //mainmenu
        pnlMenu.setBackground(Color.white);
        pnlMenu.setLayout(new GridBagLayout());
        pnlMenu.setBorder(BorderFactory.createLineBorder(Color.black));
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title;
        title = BorderFactory.createTitledBorder(
                border, "Soccer Member Registration", 0, 0, new Font("times new roman", Font.PLAIN, 32), Color.red);
        title.setTitleJustification(TitledBorder.CENTER);
        pnlMenu.setBorder(title);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 20;
        c.ipady = 20;
        c.insets = new Insets(15, 0, 0, 0);  //top padding

        //constraint icon gridbag lay out(positioning  btn)
        c.gridy = 0;
        c.gridx = 0;
        pnlMenu.add(btnMember, c);

        c.gridy = 1;
        c.gridx = 0;
        pnlMenu.add(btnSearch, c);

        c.gridy = 2;
        c.gridx = 0;
        pnlMenu.add(btnHelp, c);

        c.gridy = 3;
        c.gridx = 0;
        pnlMenu.add(btnBackup, c);
        //end constraint

        //create btn  , add icon
        ImageIcon ball = new ImageIcon(Mainmenu.class.getResource("/icon/ball.png"));
        //btn add
        JLabel icon = new JLabel();
        btnMember.setLayout(new GridLayout(1, 2));
        btnMember.add(icon);
        btnMember.add(new JLabel("Add member"));
        btnMember.add(new JLabel(""));
        icon.setIcon(ball);
        //btn 2
        icon = new JLabel();
        btnSearch.setLayout(new GridLayout(1, 2));
        btnSearch.add(icon);
        btnSearch.add(new JLabel("Search member"));
        btnSearch.add(new JLabel(""));
        icon.setIcon(ball);
        //btn 3
        icon = new JLabel();
        btnHelp.setLayout(new GridLayout(1, 2));
        btnHelp.add(icon);
        btnHelp.add(new JLabel("Help Document"));
        btnHelp.add(new JLabel(""));
        icon.setIcon(ball);
        //btn 4
        icon = new JLabel();
        btnBackup.setLayout(new GridLayout(1, 2));
        btnBackup.add(icon);
        btnBackup.add(new JLabel("Back up"));
        btnBackup.add(new JLabel(""));
        icon.setIcon(ball);

        //add listener to btn
        btnMember.addActionListener(this);
        btnSearch.addActionListener(this);
        btnBackup.addActionListener(this);
        btnHelp.addActionListener(this);
    }

    /**
     * in Action event
     *
     * @param ev
     */
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == btnMember) { //btn member
            this.setVisible(false);
            AddMember app = new AddMember(this);
        } else if (ev.getSource() == btnSearch) { //btnSearch
            this.setVisible(false);
            SearchMember app = new SearchMember(this);
        } else if (ev.getSource() == btnBackup) { //btn Backup
            MemberModel m = new MemberModel();
            m.getDataFromDatabase(SelectAllstmt());
            ArrayList<User> list = m.getlist();
            try {
                ReadWrite.saveData(list);
                JOptionPane.showMessageDialog(null, "Data Backup successful\n " + list.size() + " of record has beed save to data.bin");
            } catch (IOException ex) {
                Logger.getLogger(Mainmenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ev.getSource() == btnHelp) //help doc
        {
            String document = "dist/javadoc/index.html";  //could also have a file path here to another directory
            try {
                File f = new File(document);
                Desktop.getDesktop().open(f);
            } catch (IOException ioE) {
                System.err.print(ioE.getMessage());
            }
        }
    }
}
