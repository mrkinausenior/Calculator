import junit.framework.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    @Test
    public void testIfValidatorAcceptsCharSetAndFloats(){
        final ArrayList<Character> charSet = new ArrayList<Character>();
        charSet.add('+');
        charSet.add('-');
        charSet.add('*');
        charSet.add('%');
        charSet.add('(');
        charSet.add(')');
        String[] formula = {"(","(","(","(","2","*","10.0","*","5",")","*","2","+","4",")","*","7",")",")"};

        assertTrue(Validator.consistsOfValidChars(formula, charSet));
    }
    @Test
    public void testIfRecognizesInvalidCharacter(){
        final ArrayList<Character> charSet = new ArrayList<Character>();
        charSet.add('+');
        charSet.add('-');
        charSet.add('*');
        charSet.add('%');
        charSet.add('(');
        charSet.add(')');
        String[] formula = {"(","(","(","(","2","*","10.0","*","5",")","*","2","+","4",")","*","7",")",")","sieben"};

        assertFalse(Validator.consistsOfValidChars(formula, charSet));
    }
    @Test
    public void testIfNumberOfClosedAndOpenBracketsIsEqual(){
        String[] formula = {"(","(","(","(","2","*","10.0","*","5",")","*","2","+","4",")","*","7",")",")"};

        assertTrue(Validator.isNumberOfBracketsEqual(formula));
    }
    @Test
    public void testIfClosedBracketCanAppearBeforeOpenBracket(){
        String[] formula = {")","(","(","(","(","2","*","10.0","*","5",")","*","2","+","4",")","*","7",")",")"};

        assertFalse(Validator.BracketsArePlacedCorrectly(formula));
    }
}
