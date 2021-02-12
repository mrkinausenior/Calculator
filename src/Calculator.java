import java.util.ArrayList;
public class Calculator {
    public static void main(String[] args){
        final ArrayList<Character> charSet = new ArrayList<Character>();
        charSet.add('+');
        charSet.add('-');
        charSet.add('*');
        charSet.add('%');
        charSet.add('(');
        charSet.add(')');
        String[] formula = new String[args.length];
        initialize(args, charSet, formula);
        solve(formula);
    }

    private static String[] solveBrackets(String[] formula) {
        while(hasBrackets(formula)){
            System.out.println();
            formula = solveLastBracket(formula);
            for (String s : formula) {
                System.out.print(s);
            }
        }
        return formula;
    }

    private static void initialize(String[] args, ArrayList<Character> charSet, String[] formula) {
        for (int i = 0; i < args.length ; i++) {
            if(charSet.contains(args[i].charAt(0)) && args[i].length() == 1 ){
                formula[i] = args[i];
            }
            else if(isNumeric(args[i])){
                formula[i] = args[i];
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            float f = Float.parseFloat(strNum);
        } catch (NumberFormatException e) {
            System.exit(1);
            return false;
        }
        return true;
    }
    public static void solve (String[] formula){
        formula = solveBrackets(formula);
        int counter = 0;
        float erg = 0;
        for (int i =0; i < formula.length ; i++) {
            if(formula[i].equals("*") ||formula[i].equals("%") ){
                counter++;
                break;
            }
        }
        if(counter != 0){
            for (int i = 0; i < formula.length ; i++) {
                if(counter == 0){
                    break;
                }
                switch(formula[i]){
                    case "%":
                        erg=divide(Float.parseFloat(formula[i-1]), Float.parseFloat(formula[i+1]));
                        counter--;
                        break;
                    case "*":
                        erg=multiply(Float.parseFloat(formula[i-1]), Float.parseFloat(formula[i+1]));
                        counter--;
                        break;
                }
                if(formula[i].equals("*") || formula[i].equals("%")){
                    formula[i-1] =String.valueOf(erg);
                    formula = shiftFormulaLeftByIndex(formula, i);
                    i+=2;
                }
            }
        }
        for (int i = 0; i < formula.length ; i++) {
            if(formula[i]==null){
                break;
            }
            switch (formula[i]) {
                case "+":
                    erg = add(Float.parseFloat(formula[i - 1]), Float.parseFloat(formula[i + 1]));
                    break;
                case "-":
                    erg = subtract(Float.parseFloat(formula[i - 1]), Float.parseFloat(formula[i + 1]));
                    break;
            }
            if( formula[i].equals("+") || formula[i].equals("-")){
                formula[i-1] =String.valueOf(erg);
                formula = shiftFormulaLeftByIndex(formula, i);
                i+=2;
            }
        }
        System.out.println();
        for (String s : formula) {
            System.out.println(s);
        }
    }
    public static String[] solveLastBracket(String[] formula){
        float erg = 0;
        int counter = 0;
        int start = getLastOpeningBracket(formula);
        int end = getFirstClosingBracket(formula);
        if(end == start+1){
            formula = removeBrackets(start,end,formula);
            return formula;
        }
        for (int i =start+1; i <end ; i++) {
            if(formula[i].equals("*") ||formula[i].equals("%") ){
                counter++;
                break;
            }
        }
        if(counter != 0){
            for (int i = start+1; i <end ; i++) {
                if(counter == 0){
                    break;
                }
                switch(formula[i]){
                    case "%":
                        erg=divide(Float.parseFloat(formula[i-1]), Float.parseFloat(formula[i+1]));
                        counter--;
                        break;
                    case "*":
                        erg=multiply(Float.parseFloat(formula[i-1]), Float.parseFloat(formula[i+1]));
                        counter--;
                        break;
                }
                if(formula[i].equals("*") || formula[i].equals("%")){
                    formula[i-1] =String.valueOf(erg);
                    formula = shiftFormulaLeftByIndex(formula, i);
                    i+=2;
                }
            }
        }
        start = getLastOpeningBracket(formula);
        end = getFirstClosingBracket(formula);
        for (int i = start+1; i < end ; i++) {
            if(formula[i]==null){
                break;
            }
            switch (formula[i]) {
                case "+":
                    erg = add(Float.parseFloat(formula[i - 1]), Float.parseFloat(formula[i + 1]));
                    break;
                case "-":
                    erg = subtract(Float.parseFloat(formula[i - 1]), Float.parseFloat(formula[i + 1]));
                    break;
            }
            if( formula[i].equals("+") || formula[i].equals("-")){
                formula[i-1] =String.valueOf(erg);
                formula = shiftFormulaLeftByIndex(formula, i);
                i+=2;
            }
        }
        formula = removeBrackets(getLastOpeningBracket(formula),getFirstClosingBracket(formula),formula);
        return formula;
    }
    private static String[] shiftFormulaLeftByIndex(String[] formula, int start ){
        String[] newFormula = new String[formula.length-2];
        if (start >= 0) System.arraycopy(formula, 0, newFormula, 0, start);
        if (newFormula.length - start >= 0)
            System.arraycopy(formula, start + 2, newFormula, start, newFormula.length - start);
        return newFormula;
    }

    private static int getLastOpeningBracket(String[] operands){
        int result = 0;
        for (int i = 0; i <operands.length ; i++) {
            if(operands[i].equals("(")){
                result=i;
            }
        }
        return result;
    }
    private static int getFirstClosingBracket(String[]operands){
        int result= 0;
        for (int i = 0; i <operands.length ; i++) {
            if(operands[i].equals(")")){
                result = i;
                return result;
            }
        }
        return result;
    }
    private static String[] removeBrackets(int positionOpeningBracket, int positionClosingBracket, String[] formula){
        String[] bracketsRemoved = new String[formula.length-2];
        for (int i = 0; i <positionOpeningBracket; i++) {
            bracketsRemoved[i] = formula[i];
        }
        for (int i = positionOpeningBracket+1 ; i <positionClosingBracket ; i++) {
            bracketsRemoved[i-1]=formula[i];
        }
        for (int i = positionClosingBracket+1; i < formula.length ; i++) {
            bracketsRemoved[i-2]=formula[i];
        }
        return bracketsRemoved;
    }
    private static boolean hasBrackets(String[] formula){
        for (String s : formula) {
            if (s.equals("(")) {
                return true;
            }
        }
        return false;
    }


    private static float add(float addendOne, float addendTwo){
        return addendOne+addendTwo;
    }
    private static float divide(float dividend, float divisor){
        if(divisor == 0){
            System.out.println("Division by 0 is not defined");
            System.exit(1);
        }
        return dividend / divisor;
    }
    private static float subtract(float minuend, float subtrahend){
        return minuend-subtrahend;
    }
    private static float multiply(float multiplierOne, float multiplierTwo){
        return multiplierOne*multiplierTwo;
    }
}
