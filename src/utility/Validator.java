package utility;

import javax.swing.*;

/**
 * class for validation of input
 * @author Moss
 */
public class Validator {
    
    
/**
 * 
 * @param num parameter for String day 
 * @return boolean
 * method for validate number
 */
    public static boolean validateNumber(String num) {

        if (num.length() == 0) {
            return false;       //enter nothing  
        }

        try {
            Integer.parseInt(num);// check for int attribute
        } catch (NumberFormatException nfE) {
            return false;
        }
        //END CATCH

        int i = Integer.parseInt(num);
        if (i >= 1) {
            return true;
        } else {
   
            return false;
        }
    }
   
    /**
     * 
     * @param input parameter for text input of name
     * @return boolean true/false
     * method for validate user enter String name
     */
    public static boolean validateInputName(String input) {
        if (input.length() == 0) {
            JOptionPane.showMessageDialog(null,
                    "please Enter name");
            return false;       //enter nothing  
        }

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                JOptionPane.showMessageDialog(null, "The name must not contain " //no digit
                        + "any digit");
                return false;
            } else if (Character.isWhitespace(input.charAt(i))) {
                JOptionPane.showMessageDialog(null, "The name must not contain "
                        + "spaces");
                return false;
            }
        }
        return true;
    }
    /**
     * check if input is Empty
     * @param input String
     * @return true/false
     */
     public static boolean validateEmptyInput(String input) {
         if (input.length() == 0) {
            return false;       //enter nothing  
        }
        for (int i = 0; i < input.length(); i++) {
            if(Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * @param input check input is empty and no number or space
     * @return true/false
     */
    public static boolean validateEmptyAndNumber(String input) {
        if (input.length() == 0) {
            return false;       //enter nothing  
        }
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                return false;
            } else if (Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
