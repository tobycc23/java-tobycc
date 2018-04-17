package com.tobycc.java.learning.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by toby.christey-clover on 25/01/2018.
 */
public class CollectionsLearning {

    //Legacy Vector and Enumeration and Collection Classes
    /** Example 1:1
     *
     */
    class EnumerationTester {
        public void main(String args[]) {
            Enumeration days;
            Vector dayNames = new Vector();

            dayNames.add("Sunday");
            dayNames.add("Monday");
            dayNames.add("Tuesday");
            dayNames.add("Wednesday");
            dayNames.add("Thursday");
            dayNames.add("Friday");
            dayNames.add("Saturday");
            days = dayNames.elements();

            while (days.hasMoreElements()) {
                System.out.println(days.nextElement());
            }
        }
    }


    /** Example 2:1
     *
     */
    class HashMapDemo {
        public void main(String args[]) {
            // Create a hash map
            Map hm = new HashMap();

            // Put elements to the map
            hm.put("Zara", new Double(3434.34));
            hm.put("Mahnaz", new Double(123.22));
            hm.put("Ayan", new Double(1378.00));
            hm.put("Daisy", new Double(99.22));
            hm.put("Qadir", new Double(-19.08));

            // Get a set of the entries
            Set set = hm.entrySet();

            // Get an iterator
            Iterator i = set.iterator();

            // Display elements
            // Use TreeMap to display in name order ascending
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
            }
            System.out.println();

            // Deposit 1000 into Zara's account
            double balance = ((Double)hm.get("Zara")).doubleValue();
            hm.put("Zara", new Double(balance + 1000));
            System.out.println("Zara's new balance: " + hm.get("Zara"));

            ArrayList al = new ArrayList();

            // add elements to the array list
            al.add("C");
            al.add("A");
            al.add("E");
            al.add("B");
            al.add("D");
            al.add("F");

            // Modify objects being iterated
            ListIterator litr = al.listIterator();

            while(litr.hasNext()) {
                Object element = litr.next();
                litr.set(element + "+");
            }
            System.out.print("Modified contents of al: ");

            Iterator itr = al.iterator();
            while(itr.hasNext()) {
                Object element = itr.next();
                System.out.print(element + " ");
            }
            System.out.println();

            // Now, display the list backwards
            System.out.print("Modified list backwards: ");

            while(litr.hasPrevious()) {
                Object element = litr.previous();
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }




    //Converting between collections is simple
    public static void convertBetweenCollections() {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("two");
        list.add("three");
        Set set = new HashSet(list);
        list = new ArrayList(set);
        //List now has duplicates removed but is ordered unlike set
        System.out.println(list);

        //or can use aggregate operations
        Set set2 = Arrays.asList("one","two","two","three").stream().collect(Collectors.toSet());
        System.out.println(set2);

        //or can simply use for loop or someCollection.iterator() and then the add() method (duplicates not added obviously)
    }

    //someCollection.toArray() converts a Collection to an Array
    public static void usingToArray() {
        List list = new ArrayList();
        list.add("h");
        list.add("g");
        String[] array = (String[]) list.toArray();
    }

    //Arrays.asList(someArray) converts Arrays to a Java Collection
    public static void usingAsList() throws UnsupportedOperationException {
        String[] array = new String[]{"h","g"};
        List list = Arrays.asList(array);

        //The following will throw an UnsupportedOperationException, the list formed from asList(...) is immutable
        //in that it cannot have elements added or removed; the size must remain the same.
        try {
            List a = Arrays.asList("hello", "goodbye");
            a.remove(1);
        } catch (UnsupportedOperationException e) {
            System.out.println(e);
        }

        //The following will work as it is wrapped in an ArrayList. Use this to initialise a list with values
        //as this list will be mutable.
        List b = new ArrayList(Arrays.asList("hello again", "goodbye again"));
        b.remove(1);
        System.out.println(b.size());
    }



    public static void bulkOperations() {
        Set s1 = new HashSet<String>(Arrays.asList("one", "two", "three", "four", "seven"));
        Set s2 = new HashSet<String>(Arrays.asList("four", "five", "six", "seven", "eight", "nine"));

        //returns true if s2 a subset of s1
        s1.containsAll(s2);
        //transforms s1 into the union of s1 and s2
        s1.addAll(s2);
        //transforms s1 into the intersection of s1 and s2
        s1.retainAll(s2);
        //transforms s1 into the set difference of s1 and s2
        s1.removeAll(s2);

        //To get the set of elements contained in either of the sets but not in both do one of the following
        Set sA = new HashSet<String>(Arrays.asList("one", "two", "three", "four", "seven"));
        Set sB = new HashSet<String>(Arrays.asList("four", "five", "six", "seven", "eight", "nine"));
        Set symmetricDiff = new HashSet(sA);
        Set tmpA = new HashSet(sA);
        symmetricDiff.addAll(sB);
        tmpA.retainAll(sB);
        symmetricDiff.removeAll(tmpA);
        System.out.println(symmetricDiff);
        //or
        Set symmetricDiff2 = new HashSet(sA);
        Set tmpA2 = new HashSet(sA);
        Set tmpB = new HashSet(sB);
        symmetricDiff2.removeAll(sB);
        tmpB.removeAll(tmpA2);
        symmetricDiff2.addAll(tmpB);
        System.out.println(symmetricDiff2);

        //TreeSet will order the set (alphabetically)
        Set ts = new TreeSet(symmetricDiff2);
        System.out.println(ts);
    }

    //Create Map with defaults then can put specifics in afterwards that override default values with same keys
    public static <K,V> Map<K,V> createMapWithDefaults(Map<K,V>defaults, Map<K,V> overrides) {
        Map<K,V> result = new HashMap<>(defaults);
        result.putAll(overrides);
        return result;
    }


    //Synchronization wrappers allow any collection to be transformed into a synchronized one, list example here
    public static <T> void synchronizeAList(List<T> list) {
        List<T> newSyncList = Collections.synchronizedList(list);
        synchronized (newSyncList) {
            for (T e : newSyncList) System.out.println(e);
        }
        //java.util.concurrent provides concurrent implementations of BlockingQueue and ConcurrentMap interfaces
        //with much higher concurrency than mere synchronized implementations
    }

    //Immutable lists operations
    public static void immutableLists() {
        //Immutable list with n copies of the same element
        List list = new ArrayList(Collections.nCopies(20, "hello"));
        System.out.println(list);
        list.addAll(10, Collections.nCopies(10, "echo"));
        System.out.println(list);

        //If the number of copies you want is one, use the singleton method e.g. to remove all occurrences
        //of a specified element from a Collection
        list.removeAll(Collections.singletonList("hello"));
        System.out.println(list);
        //Immutable empty list
        System.out.println(Collections.emptyList());
    }



    public static void testMapEntryUnavailable() {
        Map<String,String> map = new HashMap<>();
        map.put("1","hello");
        map.put("2","goodbye");

        Map<String,String> newMap = new HashMap<>();
        //This following line does not through an error, just associates 3 to null
        newMap.put("3", map.get("3"));
        newMap.put("2", map.get("2"));
        System.out.println(newMap);
    }



    public static void main(String[] args) {
        usingAsList();
        convertBetweenCollections();
        bulkOperations();
        immutableLists();
        testMapEntryUnavailable();
    }

}
