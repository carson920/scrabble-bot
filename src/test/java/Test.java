import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class Test {
    @org.junit.Test
    public void testSubstituteBlank() {
        assertNotNull("a");
        Set<String> s = new HashSet<>();
        s.add("WEEWEE");
        Set<String> e = new HashSet<>();
        e.add("wEEWEE");
        e.add("WEEwEE");

        assertEquals( e, Util.substituteBlanks("EEEeWw", s));
    }

    @org.junit.Test
    public void testSubstituteBlank2() {
        assertNotNull("a");
        Set<String> s = new HashSet<>();
        s.add("WEEWEE");
        Set<String> e = new HashSet<>();
        e.add("weEWEE");
        assertEquals(s, Util.substituteBlanks("EEEEWW", s));

    }

}
