package classes;

import java.util.Comparator;

public class PersonComparator {

    public static Comparator<Person> byBirthplace() {
        return (Person p1, Person p2) -> p1.birthplace.compareToIgnoreCase(p2.birthplace);
    }

    public static Comparator<Person> byLastname() {
        return (Person p1, Person p2) -> p1.lastname.compareToIgnoreCase(p2.lastname);
    }

    public static Comparator<Person> byName() {
        return (Person p1, Person p2) -> p1.name.compareToIgnoreCase(p2.name);
    }

    public static Comparator<Person> byBirthplaceLastnameName() {
        return byBirthplace()
                .thenComparing(byLastname())
                .thenComparing(byName());
    }
}

