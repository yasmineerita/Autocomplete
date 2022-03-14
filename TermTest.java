package autocomplete;

import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {
    @Test
    public void testSimpleCompareTo() {
        Term a = new SimpleTerm("autocomplete", 0);
        SimpleTerm b = new SimpleTerm("me", 0);
        Term test = new SimpleTerm("zzzzzzzzz", 3);
        SimpleTerm test2 = new SimpleTerm("aaaaa", 6);
        SimpleTerm test3 = new SimpleTerm("zzzzzzzzz", 0);

        assertTrue(a.compareTo(b) < 0); // "autocomplete" < "me"

        assertTrue(a.compareToByPrefixOrder(b, 1) < 0);
        assertFalse(test.compareToByPrefixOrder(test2, 3) < 0);
        assertTrue(test.compareToByPrefixOrder(test3, 4) == 0);




    }

    // Write more unit tests below.
}
