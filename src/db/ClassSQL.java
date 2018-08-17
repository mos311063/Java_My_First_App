
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * SQl connection class
 * @author Moss
 */
public class ClassSQL {
    
    
    /**
     * initiate Csql connection and return it as object(Connection)
     * @return SQL Connection 
     */
    public static Connection getConnect()
    {
        Connection c = null;
        try {
            Class.forName(SQLConnection.getDRIVER());
             c = DriverManager.getConnection(SQLConnection.getURL(), SQLConnection.getUSERNAME(),
                      SQLConnection.getPASSWORD());
        } catch (ClassNotFoundException ex) {
            System.err.print("Class error connection");
        } catch (SQLException ex) {
           System.err.print("SQL error connection");
        }
    
        return c;
    }
    /**
     * 
     * @return select all SQL statement
     */
    public static String SelectAllstmt()
    {
        String sql = "Select * from tblmember";
        return sql;
    }
    /**
     * 
     * @param Searchby
     * @param Team
     * @param keyword
     * @return SQL statement select * from where Searchby = keyword and TeamName column = Team 
     */
     public static String Searchstmt(String Searchby, String Team,String keyword)
    {
        String sql = "Select * from tblmember where " + Searchby + " = '" + keyword + "' AND TeamName = '"+ Team + "'";
        return sql;
    }
    /**
     * 
     * @param memberID
     * @return delete SQL statement
     */
     public static String Deletestmt(String memberID)
    {

        String sql = "DELETE from tblmember where MemberID = '" + memberID +"';";
        return sql;
    }
    
}
