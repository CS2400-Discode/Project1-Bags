package Bags;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
/***
 * Uses JUnit to test different scenarios for the union, intersection, and difference methods in ResizableArrayBag.java
 */
class ResizableArrayBagTest {
    /***
     * Tests to see if one of the bags being empty affects the output of the three methods
     */
    @Test
    void emptyBag() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        bag1.add("a");
        bag1.add("c");
        bag1.add("c");

        BagInterface everything = bag1.union(bag2);
        BagInterface commonItems = bag1.intersection(bag2);
        BagInterface leftOver1 = bag1.difference(bag2);
        BagInterface leftOver2 = bag2.difference(bag1);

        assertEquals("[a, c, c]", Arrays.toString(everything.toArray()));
        assertEquals("[]", Arrays.toString(commonItems.toArray()));
        assertEquals("[a, c, c]", Arrays.toString(leftOver1.toArray()));
        assertEquals("[]", Arrays.toString(leftOver2.toArray()));
    }


}