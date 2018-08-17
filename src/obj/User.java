package obj;

import java.io.Serializable;
/**
 * User class store Member instance implement Serializable
 * abstract class with 2 child (local and Inter)
 * @author Moss
 */
public abstract class User implements Serializable {
        private String mNum;
        private String team;
        private String fname;
        private String lname;
        private int age;
        private String gender;
        private String email;
        private String mobile;
        
    
    public User(String mNum,String team,String fname, String lname, int age, String gender, String email, String mobile) {
        this.mNum = mNum;
        this.team = team;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
    }    

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public abstract double getmemberFare(int yearOfMember );
    public abstract String getState();
    public abstract String getCountry();
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    

    @Override
    public String toString() {
        return "user{" + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", email=" + email + ", mobile=" + mobile + '}';
    }
        
        
}
