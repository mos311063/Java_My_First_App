package gui;

import db.ClassSQL;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import obj.User;
import static utility.Validator.validateEmptyAndNumber;
import static utility.Validator.validateEmptyInput;
import static utility.Validator.validateInputName;
import static utility.Validator.validateNumber;

/**
 * Class to create edit gui when add member btn is click in parent(SearchMember)
 *
 * @author Moss
 */
public class EditMember extends JFrame implements ActionListener, ItemListener {

    //btn
    private final JButton btnRegister = new JButton("Edit");
    private final JButton btnExit = new JButton("Cancel");

    private final String[] team = {"ManUnited", "Liverpool", "Arsenal", "Chelsea", "ManCity", "Totenham"};
    private final JComboBox cboTeam = new JComboBox(team);

    //txf
    private final JTextField txf_fname = new JTextField(10);
    private final JTextField txf_lname = new JTextField(10);
    private final JTextField txf_age = new JTextField(10);
    private final JTextField txf_email = new JTextField(10);
    private final JTextField txf_mobile = new JTextField(10);
    private final JTextField txf_country = new JTextField(10);
    private final JTextField txf_state = new JTextField(10);

    // dynamic Label
    private final JLabel lbl_country = new JLabel("Country");
    private final JLabel lbl_state = new JLabel("State");

    //cbo country
    private final static String LOCAL = "Local";
    private final static String INTERNATIONAL = "International";
    private final String[] country = {LOCAL, INTERNATIONAL};
    private final JComboBox cboCountry = new JComboBox(country);

    //rad gender
    //radio btn
    private final ButtonGroup bgpGender = new ButtonGroup();
    private final JRadioButton rbtMale = new JRadioButton("Male", true);
    private final JRadioButton rbtFemale = new JRadioButton("Female");

    // panel/layout
    private final JPanel pnlCountry = new JPanel();
    private final JPanel cards;
    private final ImageIcon add = new ImageIcon(Mainmenu.class.getResource("/icon/add.png"));
    private final ImageIcon wrong = new ImageIcon(Mainmenu.class.getResource("/icon/error.png"));
    private SearchMember app;
    private User edituser;

    /**
     * EditMember constructor will create gui and retrive information from
     * edituser
     *
     * @param app parent(Search member)
     * @param edituser user that select in parent
     */
    public EditMember(SearchMember app, User edituser) {
        this.app = app;
        this.edituser = edituser;
        //initi
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registration System");
        this.setSize(700, 750);
        this.setLocation(600, 200);
        this.setVisible(true);

        //img header
        JLabel lblHeading = new JLabel();
        lblHeading.setIcon(new ImageIcon(Mainmenu.class.getResource("/icon/logo.png")));
        //header
        JPanel pnlHeading = new JPanel();
        pnlHeading.add(lblHeading);

        //gender
        bgpGender.add(rbtMale);
        bgpGender.add(rbtFemale);
        JPanel gender = new JPanel();
        rbtMale.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rbtFemale.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rbtMale.setBackground(Color.white);
        rbtFemale.setBackground(Color.white);

        //main
        JPanel pnlTeam = new JPanel();
        pnlTeam.setLayout(new BorderLayout());
        pnlTeam.add(new JLabel("Team : "), BorderLayout.WEST);
        pnlTeam.add(cboTeam, BorderLayout.CENTER);
        cboTeam.setBackground(Color.white);
        JPanel pnlMemberDetail = new JPanel();
        pnlMemberDetail.setLayout(new GridLayout(6, 3, 1, 1));
        pnlMemberDetail.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(
                20, 2, 2, 2, Color.gray),
                "Member Detail", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Sans Serif", Font.BOLD, 20), Color.black));

        //start to set and create edit
        cboTeam.setSelectedItem(edituser.getTeam());
        pnlMemberDetail.add(new JLabel("First name: "));
        pnlMemberDetail.add(txf_fname);
        txf_fname.setText(edituser.getFname());
        pnlMemberDetail.add(new JLabel("Last name: "));
        pnlMemberDetail.add(txf_lname);
        txf_lname.setText(edituser.getLname());
        pnlMemberDetail.add(new JLabel("Age: "));
        pnlMemberDetail.add(txf_age);
        txf_age.setText(Integer.toString(edituser.getAge()));
        pnlMemberDetail.add(new JLabel("Gender: "));
        pnlMemberDetail.add(gender);
        gender.setLayout(new BorderLayout());
        gender.add(rbtMale, BorderLayout.WEST);
        gender.add(rbtFemale, BorderLayout.CENTER);
        if (edituser.getGender().equalsIgnoreCase("Female")) {
            rbtMale.setSelected(false);
            rbtFemale.setSelected(true);
        }
        pnlMemberDetail.add(new JLabel("Email: "));
        pnlMemberDetail.add(txf_email);
        txf_email.setText(edituser.getEmail());
        txf_email.setEnabled(false);
        pnlMemberDetail.add(new JLabel("Mobile: "));
        pnlMemberDetail.add(txf_mobile);
        txf_mobile.setText(edituser.getMobile());
        //edit set value

        //country
        //add cbo
        cboCountry.setEditable(false);
        cboCountry.setBackground(Color.white);  //cb field bg
        pnlCountry.setLayout(new GridLayout(2, 2)); //grid 2x2
        pnlCountry.add(cboCountry);
        pnlCountry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(
                20, 2, 2, 2, Color.gray),
                "Country Detail", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("Sans Serif", Font.BOLD, 20), Color.black));

        //Create the "cards".
        JPanel card1 = new JPanel(new GridLayout(1, 2));
        card1.setBackground(Color.white);
        card1.add(lbl_state);
        card1.add(txf_state);

        JPanel card2 = new JPanel(new GridLayout(1, 2));
        card2.setBackground(Color.white);
        card2.add(lbl_country);
        card2.add(txf_country);

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        pnlCountry.add(cboCountry);
        if (!edituser.getState().equalsIgnoreCase("-")) {
            cards.add(card1, LOCAL);
            cards.add(card2, INTERNATIONAL);
            cboCountry.setSelectedItem(LOCAL);
            txf_state.setText(edituser.getState());
            txf_country.setText("");
        }else {
            cards.add(card2, INTERNATIONAL);
            cards.add(card1, LOCAL);
            txf_country.setText(edituser.getCountry());
            cboCountry.setSelectedItem(INTERNATIONAL); 
            txf_state.setText("");
        }
        pnlCountry.add(cards);
        ////////////////////////////////////////end country

        //panel main panel position
        JPanel pnlAllDetails = new JPanel();
        pnlAllDetails.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.PAGE_START;
        c.gridy = 0;
        c.gridx = 0;
        c.weighty = 1;
        c.ipadx = 300;
        pnlAllDetails.add(pnlTeam, c);
        c.insets = new Insets(5, 0, 5, 0);  //top padding
        c.ipady = 30;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.gridx = 0;
        pnlAllDetails.add(pnlMemberDetail, c);
        c.gridy = 2;
        c.gridx = 0;
        c.ipady = 20;
        pnlAllDetails.add(pnlCountry, c);

        //btnPanel postion
        JPanel pnlButtons = new JPanel(new GridBagLayout());
        GridBagConstraints gbtn = new GridBagConstraints();
        gbtn.fill = GridBagConstraints.NORTH;
        gbtn.fill = GridBagConstraints.BOTH;
        gbtn.ipady = 20;
        gbtn.ipadx = 20;
        gbtn.insets = new Insets(0, 0, 30, 20);
        pnlButtons.add(btnRegister, gbtn);
        gbtn.insets = new Insets(0, 20, 30, 0);
        gbtn.ipadx = 45;
        pnlButtons.add(btnExit, gbtn);

        //btn icon
        btnRegister.setIcon(add);
        btnExit.setIcon(wrong);
        //position panel Frame
        Container cn = this.getContentPane();
        cn.add(pnlHeading, BorderLayout.NORTH);
        cn.add(pnlAllDetails, BorderLayout.CENTER);
        cn.add(pnlButtons, BorderLayout.SOUTH);

        //set panel bg
        pnlHeading.setBackground(Color.white);
        pnlAllDetails.setBackground(Color.white);
        pnlButtons.setBackground(Color.white);
        pnlMemberDetail.setBackground(Color.white);
        pnlCountry.setBackground(Color.white);
        pnlTeam.setBackground(Color.white);

        //add event
        btnRegister.addActionListener(this);
        btnExit.addActionListener(this);
        cboCountry.addItemListener(this);

        this.validate();
    }
    /**
     * action event
     * @param e event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            //edit method
            Connection con = ClassSQL.getConnect();  //call SQL conection method
            Statement stmt; //declare statement obj

            //get value in field
            String cbo_country = (String) cboCountry.getSelectedItem();
            String temp_state = getState(cbo_country);
            String temp_country = getCountry(cbo_country);
            String temp_gender = getGender();
            boolean check = true;
            //validation
            while (check) {
                if (!validateInputName(txf_fname.getText())) {
                    txf_fname.requestFocus();
                    check = false;
                    break;
                } else if (!validateInputName(txf_lname.getText())) {
                    txf_lname.requestFocus();
                    check = false;
                    break;
                } else if (!validateNumber(txf_age.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a (positive) number for age", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    txf_age.requestFocus();
                    check = false;
                    break;
                } else if (!validateEmptyInput(txf_email.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter Email", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    txf_email.requestFocus();
                    check = false;
                    break;

                } else if (!validateNumber(txf_mobile.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a (positive) number for mobile", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    txf_mobile.requestFocus();
                    check = false;
                    break;
                } else {
                    if (cbo_country.equalsIgnoreCase(LOCAL)) {
                        if (!validateEmptyAndNumber(txf_state.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter State", "Invalid input", JOptionPane.ERROR_MESSAGE);
                            txf_state.requestFocus();
                            check = false;
                            break;
                        }
                    } else if (cbo_country.equalsIgnoreCase(INTERNATIONAL)) {
                        if (!validateEmptyAndNumber(txf_country.getText())) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter Country", "Invalid input", JOptionPane.ERROR_MESSAGE);
                            txf_country.requestFocus();
                            check = false;
                            break;
                        }
                    }
                    break; //end else break loop with check = true
                }
            }//end loop

            if (check) {
                try {
                    stmt = con.createStatement();
                    String update = "UPDATE tblmember SET TeamName= '" + (String) cboTeam.getSelectedItem()
                            + "', FirstName= '" + txf_fname.getText() + "', LastName= '" + txf_lname.getText()
                            + "', Age= " + Integer.parseInt(txf_age.getText()) + ", Gender= '" + temp_gender + "', Email= '" + txf_email.getText() + "', Mobile= '" + txf_mobile.getText()
                            + "', State= '" + temp_state + "', Country= '" + temp_country
                            + "' WHERE MemberID = '" + edituser.getmNum() + "'";
                    stmt.executeUpdate(update);
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL statement error");
                    System.out.print((String) bgpGender.getSelection().toString());
                } catch (java.lang.NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Check connection with DB (SQL connection error might not initilize)");
                }
                JOptionPane.showMessageDialog(null, "Update Success");
                Exit();
            }
        }//end add
        /////Exit btn/////
        if (e.getSource() == btnExit) {
            Exit();
        }
    }

    /**
     *  get gender from radio btn
     * @return gender
     **/
    public String getGender() {
        if (rbtMale.isSelected()) {
            return rbtMale.getText();
        } else if (rbtFemale.isSelected()) {
            return rbtFemale.getText();
        }
        return "Unknownz";
    }

    /**
     * check if Local/International is select and retreive information from
     * country if select
     *
     * @param cbo_country
     * @return country
     */
    public String getCountry(String cbo_country) {

        if (cbo_country.equalsIgnoreCase(LOCAL)) //local have default country as England
        {
            return "England";
        } else if (cbo_country.equalsIgnoreCase(INTERNATIONAL)) //inter have country
        {
            return txf_country.getText();  //return country
        }
        return "Unknown";

    }

    /**
     * check if Local/International is select and retreive information from
     * state if select
     *
     * @param cbo_country
     * @return state
     */
    public String getState(String cbo_country) {

        if (cbo_country.equalsIgnoreCase(LOCAL)) //local have state
        {
            return txf_state.getText(); // return state
        } else if (cbo_country.equalsIgnoreCase(INTERNATIONAL))// inter dont
        {
            return "-";
        }
        return "Unknown";

    }

        
    /**
    *   displose and set parent visible
    */
    private void Exit() {
        app.setVisible(true);
        app.refeshAll();
        this.dispose();

    }
    /**
     * listener to change from cbo Local/Inter
     * @param ie event
     */
    @Override
    public void itemStateChanged(ItemEvent ie) {

        if (ie.getSource() == cboCountry) //between local an international CBO Cardlayout
        {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, (String) ie.getItem());
        }

    }
}
