package Bags;

/***
 * Creates a linked bag and implements Bags.BagInterface methods
 * @param <T> A generic type
 */
public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;

    /** Creates an empty bag */
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    /** Adds new entry to bag.
     * @param newEntry  The object to be added as a new entry.
     * @return True if addition was successful, false if not */
    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; //New node references to chain
        firstNode = newNode; //New node at beginning of chain
        numberOfEntries++;
        return true;
    }

    /** Removes an unspecific entry from a bag.
     * @return Removed entry if removal was successful, or null. */
    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        }
        return result;
    }

    /** Locates a given entry in a bag.
     * @param anEntry The given entry
     * @return The location of the given entry, or null. */
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if(anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    /** Removes a specific entry from a bag.
     * @param anEntry  The entry to be removed.
     * @return True if removal was successful, false if not. */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null)
        {
            nodeN.setData(firstNode.getData()); //Replace located entry with first node
            firstNode = firstNode.getNextNode(); //Remove first Node
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    /** Test to see if bag is empty.
     * @return True if bag is empty, false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    /** Gets the number of entries of the bag.
     * @return The number of entries in the bag. */
    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    /** Removes all entries in a bag. */
    public void clear()
    {
        while (!isEmpty())
            remove();
    }

    /** Counts how many times an entry appears in a bag.
     * @param anEntry  The entry to be counted.
     * @return The number of times anEntry appears in a bag. */
    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while ((counter < numberOfEntries) && (currentNode != null))
        {
            if(anEntry.equals(currentNode.getData()))
                frequency++;
            counter++;
            currentNode = currentNode.getNextNode();
        }
        return frequency;
    }

    /** Tests to see if the bag contains a specific entry.
     * @param anEntry  The entry to find.
     * @return True if bag contains the entry, false if not. */
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return found;
    }

    /** Turns all entries in a bag into an array.
     * @return A new array of all entries in the bag. */
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
                T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }
        return result;
    }
    /** Combines the contents of two bags and returns a new bag.
     @param bag2 The second bag to be combined with
     @return  A new bag consisting of every entry in both bags.  */
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> everything = new LinkedBag<>();
        T[] bag1 = toArray();
        T[] bag2Array = bag2.toArray();
        int bag2Size = bag2.getCurrentSize();
        for (int i =0; i < getCurrentSize(); i++)
            everything.add(bag1[i]);
        for (int i = 0; i < bag2Size; i++)
            everything.add(bag2Array[i]);
        return everything;
    }

    /** Compares the contents of two bags for same entries
     @param bag2 The second bag to be compared with
     @return  A new bag consisting of entries both bags have. */
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        BagInterface<T> commonItems = new LinkedBag<>();
        T[] bag1 = toArray();
        for (int i =0; i < getCurrentSize(); i++)
        {
            if(bag2.contains(bag1[i]))
            {
                commonItems.add(bag1[i]);
            }
        }
        return commonItems;
    }

    /** Compares the contents of two bags for unique entries
     @param bag2 The second bag to be compared with
     @return  A new bag consisting of entries only either bag has.  */
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        BagInterface<T> leftOver = new LinkedBag<>();
        T[] bag1 = toArray();
        for (int i = 0; i < getCurrentSize(); i++)
        {
            if (!leftOver.contains(bag1[i]))
            {
                int difference = getFrequencyOf(bag1[i]) - bag2.getFrequencyOf(bag1[i]);
                for (int j = 0; j < difference; j++)
                {
                    leftOver.add(bag1[i]);
                }
            }
        }
        return leftOver;
    }

    /***
     * Creates nodes that can be used in other classes
     */
    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private T getData()
        {
            return data;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }
}
