// Dan Rojas
// Mod 6.2
// 20-Apr-26

// Instructions mentioned using the "two following generic methods using a bubble sort" but does not go on to name
// them anywhere. Simply to "Click for more options", I believe these instructions amy have been meant for the
// previous iteration of blackboard we were using as there is nothing to click.

import java.util.Comparator;

public class ComparableAndComparator {
    // Nested Person class which implements comparable
    private static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return this.age - other.age;
        }

        // Anonymous implementation of Comparator
        public static Comparator<Person> nameCompare = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    // Creates Array
    private static Person[] makePeopleArray() {
        Person[] people = new Person[5];
        people[0] = new Person("Bill", 40);
        people[1] = new Person("Casey", 21);
        people[2] = new Person("Andy", 16);
        people[3] = new Person("Jake", 24);
        people[4] = new Person("Frank", 30);

        return people;
    }

    // Bubble sort used by Comparable
    static Person[] bubbleSortAge(Person[] people) {
        int n = people.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (people[j].compareTo(people[j + 1]) > 0) {
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
        return people;
    }

    // Bubble sort used by Comparator
    private static Person[] bubbleSortName(Person[] people, Comparator<Person> comparator) {
        int n = people.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(people[j], people[j + 1]) > 0) {
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
        return people;
    }

    // Displays results
    public static void displaySortedPeople() {
        Person[] people1 = bubbleSortAge(makePeopleArray());
        System.out.println("People sorted by age using comparable:");
        for (Person person : people1) {
            System.out.println(person.toString());
        }

        System.out.println("----------------------------");

        Person[] people2 = bubbleSortName(makePeopleArray(), Person.nameCompare);
        System.out.println("People sorted by name using comparator:");
        for (Person person : people2) {
            System.out.println(person.toString());
        }
    }
}
