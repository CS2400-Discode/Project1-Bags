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

    /***
     * Tests to see if repeating objects in one of the bags affects the output of the three methods
     */
    @Test
    void repeatingObjectsInOneBag() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        bag1.add("a");
        bag1.add("c");
        bag1.add("p");
        bag1.add("e");

        bag2.add("d");
        bag2.add("e");
        bag2.add("e");
        bag2.add("c");

        BagInterface everything = bag1.union(bag2);
        BagInterface commonItems = bag1.intersection(bag2);
        BagInterface leftOver1 = bag1.difference(bag2);
        BagInterface leftOver2 = bag2.difference(bag1);

        assertEquals("[a, c, p, e, d, e, e, c]", Arrays.toString(everything.toArray()));
        assertEquals("[c, e]", Arrays.toString(commonItems.toArray()));
        assertEquals("[a, p]", Arrays.toString(leftOver1.toArray()));
        assertEquals("[d, e]", Arrays.toString(leftOver2.toArray()));
    }

    /***
     * Tests to see if repeating objects in one of the bags affects the output of the three methods
     */
    @Test
    void repeatingObjectsInBothBags() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        bag1.add("a");
        bag1.add("c");
        bag1.add("c");
        bag1.add("e");

        bag2.add("d");
        bag2.add("e");
        bag2.add("c");
        bag2.add("c");

        BagInterface everything = bag1.union(bag2);
        BagInterface commonItems = bag1.intersection(bag2);
        BagInterface leftOver1 = bag1.difference(bag2);
        BagInterface leftOver2 = bag2.difference(bag1);

        assertEquals("[a, c, c, e, d, e, c, c]", Arrays.toString(everything.toArray()));
        assertEquals("[c, c, e]", Arrays.toString(commonItems.toArray()));
        assertEquals("[a]", Arrays.toString(leftOver1.toArray()));
        assertEquals("[d]", Arrays.toString(leftOver2.toArray()));
    }
    
        /***
     * Tests to see if bags with the exact same objects affects the output of the three methods
     */
    @Test
    void exactSameObjectsInBothBags() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("d");

        bag2.add("a");
        bag2.add("b");
        bag2.add("c");
        bag2.add("d");

        BagInterface everything = bag1.union(bag2);
        BagInterface commonItems = bag1.intersection(bag2);
        BagInterface leftOver1 = bag1.difference(bag2);
        BagInterface leftOver2 = bag2.difference(bag1);

        assertEquals("[a, b, c, d, a, b, c, d]", Arrays.toString(everything.toArray()));
        assertEquals("[a, b, c, d]", Arrays.toString(commonItems.toArray()));
        assertEquals("[]", Arrays.toString(leftOver1.toArray()));
        assertEquals("[]", Arrays.toString(leftOver2.toArray()));
    }
}
