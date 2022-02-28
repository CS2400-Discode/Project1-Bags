package Bags;
import java.util.*;

public class BagDriver {

    public static void main(String[] args){

        int bagType; //selection of bag type
        Scanner k = new Scanner(System.in);
        System.out.println("Which bag type? Enter 1 for ResizableArrayBag or 2 for LinkedBag:");

        bagType = k.nextInt();

        if (bagType == 1){

            int entries; 
            BagInterface<String> bag1 = new ResizableArrayBag<>();
            BagInterface<String> bag2 = new ResizableArrayBag<>();

            System.out.println("How many entries would you like to enter for Bag 1?");
            entries = k.nextInt();

            for (int i = 0; i < entries; i++){
                System.out.println("Please enter an entry for Bag 1:");
                bag1.add(k.next());
            }

            System.out.println("How many entries would you like to enter for Bag 2?");
            entries = k.nextInt();

            for (int i = 0; i < entries; i++){
                System.out.println("Please enter an entry for Bag 2:");
                bag2.add(k.next());
            }

            BagInterface everything = bag1.union(bag2);
            BagInterface commonItems = bag1.intersection(bag2);
            BagInterface leftOver1 = bag1.difference(bag2);
            BagInterface leftOver2 = bag2.difference(bag1);

            System.out.println("Union of bag1 and bag2: " + Arrays.toString(everything.toArray()));
            System.out.println("Intersection of bag1 and bag2: " + Arrays.toString(commonItems.toArray()));
            System.out.println("Difference of bag1 and bag2: " + Arrays.toString(leftOver1.toArray()));
            System.out.println("Difference of bag2 and bag1: " + Arrays.toString(leftOver2.toArray()));
        }
        else if (bagType == 2){
            
            int entries2; 
            BagInterface<String> bag1b = new LinkedBag<>();
            BagInterface<String> bag2b = new LinkedBag<>();

            System.out.println("How many entries would you like to enter for Bag 1?");
            entries2 = k.nextInt();

            for (int i = 0; i < entries2; i++){
                System.out.println("Please enter an entry for Bag 1:");
                bag1b.add(k.next());
            }

            System.out.println("How many entries would you like to enter for Bag 2?");
            entries2 = k.nextInt();

            for (int i = 0; i < entries2; i++){
                System.out.println("Please enter an entry for Bag 2:");
                bag2b.add(k.next());
            }

            BagInterface everythingB = bag1b.union(bag2b);
            BagInterface commonItemsB = bag1b.intersection(bag2b);
            BagInterface leftOver1B = bag1b.difference(bag2b);
            BagInterface leftOver2B = bag2b.difference(bag1b);

            System.out.println("Union of bag1 and bag2: " + Arrays.toString(everythingB.toArray()));
            System.out.println("Intersection of bag1 and bag2: " + Arrays.toString(commonItemsB.toArray()));
            System.out.println("Difference of bag1 and bag2: " + Arrays.toString(leftOver1B.toArray()));
            System.out.println("Difference of bag2 and bag1: " + Arrays.toString(leftOver2B.toArray()));
        }
        else {
            System.out.println("Please only enter 1 or 2.");
        }
    }
    
}
