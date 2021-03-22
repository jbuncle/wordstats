package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;

/**
 *
 * @author jbuncle
 */
public class WordLengthCountTest {

    /**
     * Test of increment method, of class WordLengthCount.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        final WordLengthCount instance = new WordLengthCount(1);
        instance.increment();
        instance.increment();

        final int result = instance.getOccurences();
        assertEquals(3, result);
    }

    /**
     * Test of getLength method, of class WordLengthCount.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        final WordLengthCount instance = new WordLengthCount(98);
        final int expResult = 98;
        final int result = instance.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOccurences method, of class WordLengthCount.
     */
    @Test
    public void testGetOccurences() {
        System.out.println("getOccurences");
        WordLengthCount instance = new WordLengthCount(3, 40);
        int expResult = 40;
        int result = instance.getOccurences();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals with same object.
     */
    @Test
    public void testEqualsSameObject() {
        System.out.println("equals");

        final WordLengthCount instance = new WordLengthCount(17, 12);
        assertTrue(instance.equals(instance));
    }

    /**
     * Test of equals method with a different object with same values.
     */
    @Test
    public void testEqualsDifferentObject() {
        System.out.println("equals");
        final Object obj = new WordLengthCount(17, 12);
        final WordLengthCount instance = new WordLengthCount(17, 12);
        assertTrue(instance.equals(obj));
    }

    /**
     * Test of equals method with a different object type.
     */
    @Test
    public void testEqualsDifferentObjectType() {
        System.out.println("equals");
        final Object obj = new WordLengthCountI() {
            @Override
            public int getLength() {
                return 17;
            }

            @Override
            public int getOccurences() {
                return 12;
            }
        };

        final WordLengthCount instance = new WordLengthCount(17, 12);
        assertTrue(instance.equals(obj));
    }

    /**
     * Test of equals method, of class WordLengthCount.
     */
    @Test
    public void testEqualsBadObject() {
        System.out.println("equals");
        final WordLengthCount instance = new WordLengthCount(17, 12);
        assertFalse(instance.equals(new LinkedList<Object>()));
    }

    /**
     * Test of hashCode method, of class WordLengthCount.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        final WordLengthCount instance = new WordLengthCount(29, 43);
        final int expResult = 1903;
        final int result = instance.hashCode();
        assertEquals(expResult, result);
    }

}
