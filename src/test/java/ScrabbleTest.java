import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScrabbleTest {

    // test complex situations when the rack has two blanks
    @Test
    public void testSubstituteBlank() {
        Set<String> s = new HashSet<>();
        s.add("DOODOO");

        Set<String> expected = new HashSet<>();
        expected.add("DOoDoO");
        expected.add("DOoDOo");
        expected.add("DOODoo");
        expected.add("DooDOO");
        expected.add("DoODoO");
        expected.add("DoODOo");
        assertEquals( expected, Util.substituteBlanks("DDooOO", s));
    }

    @Test
    public void testSubstituteBlank2() {
        Set<String> s = new HashSet<>();
        s.add("DOODOO");

        Set<String> expected = new HashSet<>();
        expected.add("dOODoO");
        expected.add("dOoDOO");
        expected.add("dOODOo");
        expected.add("doODOO");
        expected.add("DOodOO");
        expected.add("DOOdoO");
        expected.add("DOOdOo");
        expected.add("DoOdOO");
        assertEquals( expected, Util.substituteBlanks("dDoOOO", s));

    }

}
