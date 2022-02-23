package Bags;

import java.util.Arrays;

/***
 * Creates a resizable array bag and implements Bags.BagInterface methods
 * @param <T> A generic type
 */
public class ResizableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag with a default capacity. */
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

    /** Creates an empty bag with a desired capacity.
     @param desiredCapacity The desired capacity. */
    public ResizableArrayBag(int desiredCapacity)
    {
        if (desiredCapacity <= MAX_CAPACITY) //Capacity under max?
        {
            @SuppressWarnings("unchecked")
                    T[]tempBag = (T[])new Object[desiredCapacity];
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        }
        else //Capacity over max
            throw new IllegalStateException("Attempt to create a bag whose"
            + "capacity exceeds allowed maximum.");
    }

    /** Adds new entry to bag
    @param newEntry Object to be added to bag.
     @return True if addition was successful, false is not. */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        boolean result = true;
        if (isFull()) //Is the bag full?
            doubleCapacity();
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return result;
    }

    /** Doubles the size of the bag. */
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }

    /** Checks to see if capacity is too large.
     * @param capacity Capacity to be checked. */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag over the capacity of " + MAX_CAPACITY);
    }

    /** Converts bag into an array.
     @return An array of the bag's contents.*/
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
                T[] result = (T[])new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = bag[index];
        }
        return result;
    }

    /** Tests to see if bag is full.
     @return True if bag is full, false if not. */
    public boolean isFull()
    {
        return numberOfEntries == bag.length;
    }

    /** Tests to see if object is initialized.
     * Throws an exception is object is not initialized. */
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    }
    /** Tests to see if bag is empty.
     @return True if bag is empty, false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    /** Gets the size of the bag.
     @return The number of entries in the bag. */
    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    /** Finds how many times an entry is in a bag.
     @param anEntry The entry to be counted
     @return The number of times anEntry is in the bag. */
    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++)
        {
            if (anEntry.equals(bag[index]))
                counter++;
        }
        return counter;
    }

    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    /** Locates a desired entry in a bag.
     * @param anEntry The entry to find in the bag.
     * @return Gives the location of the desired entry (if where > -1). */
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;
        int index = 0;
        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }

    /** Removes an entry from the bag at a given location.
     * @param givenIndex The entry to be removed.
     * @return Returns the entry if removed, null if not. */
    private T removeEntry(int givenIndex)
    {
        T result = null;
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    /** Removes the last entry of a bag.
     * @return Returns the entry if removed, null if not. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }
    /** Removes all entries in a bag. */
    public void clear()
    {
        while (!isEmpty())
            remove();
    }

    /** Tests where the bag has a desired entry.
     * @param anEntry  The entry to find.
     * @return True if the entry is found, false if not. */
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /** Combines the contents of two bags and returns a new bag.
     @return  A new bag consisting of every entry in both bags.
      * @param bag2 The second bag to be combined with*/
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> everything = new ResizableArrayBag<>();
        T[] bag2Array = bag2.toArray();
        int bag2Size = bag2.getCurrentSize();
        for (int i = 0; i < getCurrentSize(); i++)
            everything.add(bag[i]);
        for (int i = 0; i < bag2Size; i++)
            everything.add(bag2Array[i]);
        return everything;
    }

    /** Compares the contents of two bags for same entries
     @param bag2 The second bag to be compared with
     @return  A new bag consisting of entries both bags have. */
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        BagInterface<T> commonItems = new ResizableArrayBag<>();
        for (int i = 0; i < getCurrentSize(); i++)
        {
            if (bag2.contains(bag[i]))
            {
                commonItems.add(bag[i]);
            }
        }
        return commonItems;
    }

    /** Compares the contents of two bags for unique entries
     @param bag2 The second bag to be compared with
     @return  A new bag consisting of entries only either bag has.  */
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
       BagInterface<T> leftOver = new ResizableArrayBag<>();
        for (int i = 0; i < getCurrentSize(); i++)
        {
            if (!leftOver.contains(bag[i]))
            {
                int difference = getFrequencyOf(bag[i]) - bag2.getFrequencyOf(bag[i]);
                for (int j = 0; j < difference; j++)
                {
                    leftOver.add(bag[i]);
                }
            }
        }
        return leftOver;
    }
}
