package classes;

import incl.DoubleOrderedList;
import incl.OrderedList;

import java.util.Iterator;

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

    public void showPeople(){
        System.out.println(this.people.toString());
    }

    public void showRelations(String id){
        Iterator<Relationship> it = relations.iterator();
        while(it.hasNext()){
            Relationship r = it.next();
            if(r.getFriend1().equals(id)){
                System.out.print(r.getFriend2()+", ");
            }
            if(r.getFriend2().equals(id)){
                System.out.print(r.getFriend1()+", ");
            }
        }
        System.out.println();

    }
}
