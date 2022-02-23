package Bags;

import java.util.Arrays;

/***
 * Tests the methods in the Bags.ResizableArrayBag
 */
public class ArrayBagTest
{
    public static void main(String[] args){
//      Creating two new array bags
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        BagInterface<String> bag2 = new ResizableArrayBag<>();

//      Adding objects to bag 1
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");

//      Adding objects to bag 2
        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");

//        Creating new bags that store values from methods
        BagInterface everything = bag1.union(bag2);
        BagInterface commonItems = bag1.intersection(bag2);
        BagInterface leftOver1 = bag1.difference(bag2);
        BagInterface leftOver2 = bag2.difference(bag1);

//        Printing out the objects in the various bags
        System.out.println("Union of bag1 and bag2: " + Arrays.toString(everything.toArray()));
        System.out.println("Intersection of bag1 and bag2: " + Arrays.toString(commonItems.toArray()));
        System.out.println("Difference of bag1 and bag2: " + Arrays.toString(leftOver1.toArray()));
        System.out.println("Difference of bag2 and bag1: " + Arrays.toString(leftOver2.toArray()));
    }
}
