package gui;

import db.ClassSQL;
import static db.ClassSQL.Deletestmt;
import static db.ClassSQL.Searchstmt;
import static db.ClassSQL.SelectAllstmt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import obj.User;

/**
 * SearchMember Frame
 * parent(Main menu);
 * @author Moss
 */
public class SearchMember extends JFrame implements ActionListener {

    ///initiate Table element
    private JTable tblMember = new JTable();
    //add table to the Scrollpane constructor
    private JScrollPane scroll = new JScrollPane(tblMember,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    //initiate Membermodel
    private MemberModel memberModel;
    private int selectedRow = -1;

    //other element
    private JButton btnAll = new JButton("Search All records");
    private JButton btnSearch = new JButton("Search");
    private JTextField txfSearch = new JTextField(18);

    //private final String[] team = {"ManUnited", "Liverpool"}; might add team to search
    //private final JComboBox cboTeam = new JComboBox(team);
    private final String[] search_item = {"FirstName", "LastName","Age","Gender","Email","Mobile","State","Country"};
    private final JComboBox cboSearchby = new JComboBox(search_item);
    private final String[] sort_item = {"ManUnited", "Liverpool","Arsenal","Chelsea","ManCity","Totenham"};
    private final JComboBox cboSortby = new JComboBox(sort_item);
    private JTextField txfFirstName = new JTextField();
    private JTextField txfLastName = new JTextField();

    private final JButton btnEdit = new JButton();
    private final JButton btnDelete = new JButton();
    private final JButton btnExit = new JButton("Exit");

    private JLabel icon = new JLabel();
    private ImageIcon edit = new ImageIcon(Mainmenu.class.getResource("/icon/edit.png"));
    private ImageIcon delete = new ImageIcon(Mainmenu.class.getResource("/icon/delete.gif"));
    private Mainmenu app;
    
    /**
     * Serach Member Constructer
     * @param app (Parent)
     */
    public SearchMember(Mainmenu app) {
        this.app = app;
        this.setTitle("Soccer Club Member");
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel lblHeading = new JLabel();
        lblHeading.setIcon(new ImageIcon(Mainmenu.class.getResource("/icon/logo2.png")));
        //header
        JPanel pnlHeading = new JPanel();
        pnlHeading.add(lblHeading);
        pnlHeading.setBackground(Color.white);

        JPanel pnlMain = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 800;
        c.ipady = 500;
        c.insets = new Insets(-200, 0, 0, 0);
        c.gridy = 0;
        c.gridx = 0;
        pnlMain.add(scroll, c);
        pnlMain.setBackground(Color.white);
        scroll.setBackground(Color.yellow);

        //position edit btn
        c.gridy = 1;
        c.gridx = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 0, 0, 100);
        pnlMain.add(btnEdit, c);

        ///edit btn
        btnEdit.setLayout(new FlowLayout());
        icon = new JLabel();
        btnEdit.add(icon);
        btnEdit.add(new JLabel("Edit"));
        icon.setIcon(edit);

        //position delete btn
        btnDelete.setLayout(new FlowLayout());
        c.insets = new Insets(5, 100, 0, 0);
        pnlMain.add(btnDelete, c);
        //delete btn
        icon = new JLabel();
        btnDelete.add(icon);
        btnDelete.add(new JLabel("Delete"));
        icon.setIcon(delete);

        //search position
        c.ipadx = 0;
        c.ipady = 0;
        c.gridy = 2;
        c.gridx = 0;
        c.insets = new Insets(20, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(new JLabel("Search by"), c);
        c.insets = new Insets(20, 100, 0, 0);
        pnlMain.add(cboSearchby, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20, 0, 0, 0);
        pnlMain.add(new JLabel("Select Team"), c);
        c.insets = new Insets(20, 100, 0, 0);
        pnlMain.add(cboSortby, c);

        ///search panel
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);
        JPanel pnlSearch = new JPanel(new FlowLayout());
        pnlSearch.setBackground(Color.white);
        pnlSearch.add(new JLabel("Search : "));
        txfSearch.setPreferredSize(new Dimension(25, 25));
        pnlSearch.add(txfSearch);
        pnlSearch.add(btnSearch);
        pnlSearch.add(btnAll);
        pnlMain.add(pnlSearch, c);

        JPanel pnlButton = new JPanel(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.ipadx = 80;
        b.ipady = 10;
        b.insets = new Insets(-10, 0, 20, 0);
        pnlButton.add(btnExit, b);
        pnlButton.setBackground(Color.white);
        Container cn = this.getContentPane();
        memberModel = new MemberModel();
        memberModel.getDataFromDatabase(SelectAllstmt());
        tblMember.setModel(memberModel);
        tblMember.getParent().setBackground(Color.white);
        tblMember.setBackground(Color.LIGHT_GRAY);
        tblMember.setForeground(Color.BLACK);
        tblMember.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        cn.add(pnlHeading, BorderLayout.NORTH);
        cn.add(pnlMain, BorderLayout.CENTER);
        cn.add(pnlButton, BorderLayout.SOUTH);
        this.setBounds(500, 150, 900, 800);

        //only one record can be deleted at a time
        tblMember.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //get Selection that user select from table
        ListSelectionModel rowSM = tblMember.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel temp = (ListSelectionModel) e.getSource();
                selectedRow = temp.getMinSelectionIndex();
            }
        });

        //add listener
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);
        btnAll.addActionListener(this);
        btnExit.addActionListener(this);
    }
    //action event
    public void actionPerformed(ActionEvent ev) {
        ///delete btn
         if (ev.getSource() == btnEdit) {
            if(selectedRow == -1)
            {//disable edit when non-selected    
            }
            else
            {
             this.setVisible(false);
             EditMember e = new EditMember(this,memberModel.getRow(selectedRow));
            }
         }
         //Delete btn
         else if (ev.getSource() == btnDelete) {
            //confirm dialog box
            if(selectedRow == -1)
            {//disable delete when non-selected
            }
            else
            {
            int decision = JOptionPane.showConfirmDialog(null,
                    "WARNING. You are about to delete a record. PROCEED?",
                    "CONFIRM DELETE",
                    JOptionPane.YES_NO_OPTION);

            if (decision == JOptionPane.YES_OPTION) {
                deleteSelectRecord(memberModel.getRow(selectedRow));
                refeshAll();
            }
            }
        }
       
        ///Search btn
        else if (ev.getSource() == btnSearch) {
                String searchby = (String)cboSearchby.getSelectedItem();
                String sortby = (String)cboSortby.getSelectedItem();
                String  keyword = txfSearch.getText();
                memberModel.getDataFromDatabase(Searchstmt(searchby,sortby,keyword));
                memberModel.fireTableRowsDeleted(selectedRow, selectedRow);
        }
        //Search All
        else if (ev.getSource() == btnAll) {
                refeshAll();
        }
        //Exit
        else if(ev.getSource() == btnExit){
                Exit();
         }
    }
    /**
     * Delete method get column pointer from getRow() and delete the value of
     * that column
     *
     * @param selectUser == store pointer to user that select in member model
     */
    public void deleteSelectRecord(User selectUser) {
        Connection con;
        try {
            con = ClassSQL.getConnect();
            Statement stmt = con.createStatement();
            String sql = Deletestmt(selectUser.getmNum());
            // System.out.println(sql);  //for testing purposes
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Place code in here that will always be run.
        }
    }
    /**
     *  Search data will use data in Search field to Select for data according from db
     */
    private void Searchdata() {
        Connection con;
        String searchby;
        String sortby;
        String keyword;
        
        searchby = (String)cboSearchby.getSelectedItem();
        sortby = (String)cboSortby.getSelectedItem();
        keyword = txfSearch.getText();
        try {
            con = ClassSQL.getConnect();
            Statement stmt = con.createStatement();
            String sql = Searchstmt(searchby,sortby,keyword);
            // System.out.println(sql);  //for testing purposes
            stmt.executeQuery(sql);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Place code in here that will always be run.
        }
    }
    
    /**
     * refresh table
     */
    public void refeshAll()
    {
        memberModel.getDataFromDatabase(SelectAllstmt());
        memberModel.fireTableRowsDeleted(selectedRow, selectedRow);
    }
    /**
     * close this and open parent
     */
    private void Exit() {
        this.dispose();
        app.setVisible(true);
    }
}
