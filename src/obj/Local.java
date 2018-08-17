
package obj;

import java.io.Serializable;

/**
 * Extend User store member type of Local
 * @author Moss
 */
public class Local extends User implements Serializable  {
        private String state;

    public Local(String mNum, String team, String fname, String lname, int age, String gender, String email, String mobile,String state) {
        super(mNum, team, fname, lname, age, gender, email, mobile);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    public double getmemberFare (int yearOfMember ){
        return yearOfMember * 67;     
    }

    @Override
    public String getCountry() {
        return "England";
    }
    

}
