
package obj;

import java.io.Serializable;

/**
 * Extend User store member type of International
 * @author Moss
 */
public class Inter extends User implements Serializable  {
        private String country;

   public Inter(String mNum, String team, String fname, String lname, int age, String gender, String email, String mobile,String country) {
        super(mNum, team, fname, lname, age, gender, email, mobile);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public double getmemberFare (int yearOfMember ){
        if(super.getAge() < 18)
        {return yearOfMember * 84;}
        else
        {return yearOfMember * 102;}           
    }    

    @Override
    public String getState() {
        return "-";
    }
        
}
