package gui;

import static db.ClassSQL.getConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import obj.Inter;
import obj.Local;
import obj.User;

/**
 * MemberModel is class that extend table model the class consist of method
 * involve model in JTable
 *
 * @author Moss
 */
public class MemberModel extends AbstractTableModel {

    //data extracted from the database will be created into 
    //Student objects and added to the ArrayList
    private ArrayList<User> list = new ArrayList<>();
    private final String[] columnNames = {"Team", "First name", "Last name", "Age", "Gender", "Email", "Mobile", "State", "Country"};

    //constructor will call the method to extract data from database
    public MemberModel() {
    }

    public ArrayList<User> getlist() {
        return list;
    }

    /*
    At a minimum this class must implement the 3 abstract methods inherited from
    the abstract class: AbstractTableModel.
     - public int getRowCount();
     - public in getColumnCount();
     - public Object getValueAt(int row, int column);
    These methods are fairly self-explanatory.
     */
    public int getRowCount() {
        return list.size();   //how many rows (records) in the table
    }

    public int getColumnCount() {
        return columnNames.length; //how many columns to display in the table
    }

    /**
     * 
     * @param row
     * @param col
     * @return get data from database and return the User object components
     */
    public Object getValueAt(int row, int col) {
        User user = list.get(row);
        switch (col) {
            case 0:
                return user.getTeam();
            case 1:
                return user.getFname();
            case 2:
                return user.getLname();
            case 3:
                return user.getAge();
            case 4:
                return user.getGender();
            case 5:
                return user.getEmail();
            case 6:
                return user.getMobile();
            case 7:
                return user.getState();
            case 8:
                return user.getCountry();
        }
        return null;
    }
    // End of methods that MUST be implemented

    //Non-abstract method inherited from AbstractTableModle - 
    //this method already has a method body but can still be 
    //overidden by creating a new version of this method
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    //This method will extra one object(record) from the ArrayList at a time
    public User getRow(int row) {
        User user = list.get(row);
        return user;
    }

    /**
     * method to retrive data from db and store data in arraylist in
     *
     * @param sql passing SQL statement
     */
    public void getDataFromDatabase(String sql) { //Method to load data from the database and save to the ArrayList
        Connection con;
        Statement stmt;
        ResultSet r;

        try {
            con = getConnect();
            stmt = con.createStatement();

            r = stmt.executeQuery(sql);
            // Retrieve records and place them in arrayList
            list.clear(); //remove any data from the ArrayList

            //loop through the extracted records and add to ArrayList
            while (r.next()) {

                if (r.getString("Country").equalsIgnoreCase("England")) {
                    list.add(new Local(r.getString("MemberID"), r.getString("Teamname"), r.getString("FirstName"), r.getString("LastName"), r.getInt("Age"),
                            r.getString("Gender"), r.getString("Email"), r.getString("Mobile"), r.getString("State")));
                } else {
                    list.add(new Inter(r.getString("MemberID"), r.getString("Teamname"), r.getString("FirstName"), r.getString("LastName"), r.getInt("Age"),
                            r.getString("Gender"), r.getString("Email"), r.getString("Mobile"), r.getString("Country")));
                }
            }
            con.close();
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            // Place code in here that will always be run.
        }
    }
}
