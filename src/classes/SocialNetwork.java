package classes;

import incl.DoubleOrderedList;
import incl.OrderedList;

public class SocialNetwork {

    DoubleOrderedList<Person> people;
    OrderedList<Relationship> relations;

    public SocialNetwork() {
        people = new DoubleOrderedList<>();
        relations = new OrderedList<>();
    }

    public DoubleOrderedList<Person> getPeople() {
        return people;
    }

    public void setPeople(DoubleOrderedList<Person> people) {
        this.people = people;
    }

    public OrderedList<Relationship> getRelations() {
        return relations;
    }

    public void setRelations(OrderedList<Relationship> relations) {
        this.relations = relations;
    }
}
