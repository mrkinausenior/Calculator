import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static boolean isValid(String[] formula){
        return false;
    }
    public static boolean consistsOfValidChars(String[] formula, List<Character> charSet){
        for (String s : formula) {
            if (!(charSet.contains(s.charAt(0)) && s.length() == 1  || isNumeric(s))){
                return false;
            }
        }
        return true;
    }
    public static boolean isNumberOfBracketsEqual(String[] formula){
        int counterOpeningBrackets = 0;
        int counterClosingBrackets = 0;
        for (String s : formula) {
            if(s.equals("(")){
                counterOpeningBrackets++;
            }
            else if (s.equals(")")){
                counterClosingBrackets++;
            }
        }
        if(counterClosingBrackets == counterOpeningBrackets){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean BracketsArePlacedCorrectly(String[] formula){
        int counterOpeningBrackets = 0;
        int counterClosingBrackets = 0;
        for (String s : formula) {
            if(s.equals("(")){
                counterOpeningBrackets++;
            }
            else if (s.equals(")")){
                counterClosingBrackets++;
            }
            if(counterClosingBrackets > counterOpeningBrackets){
                return false;
            }
        }
        return counterOpeningBrackets == counterClosingBrackets;
    }















    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            float f = Float.parseFloat(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
