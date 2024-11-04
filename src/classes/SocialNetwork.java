package classes;

import incl.DoubleOrderedList;
import incl.OrderedList;

import java.util.ArrayList;
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

            else if(r.getFriend2().equals(id)){
                System.out.print(r.getFriend1()+", ");
            }
        }
        System.out.println();

    }

    public ArrayList<String> getRelationships(String id){
        Iterator<Relationship> it = relations.iterator();
        ArrayList<String> result = new ArrayList<>();
        while(it.hasNext()){
            Relationship r = it.next();
            if(r.getFriend1().equals(id)){
                result.add(r.getFriend2());
            }

            else if(r.getFriend2().equals(id)){
                result.add(r.getFriend1());
            }
        }
        return result;
    }

    public ArrayList<Person> findFromSurname(String surname){
        ArrayList<Person> persons = new ArrayList<>();
        Iterator<Person> it = people.iterator();
        while(it.hasNext()){
            Person p = it.next();
            if(p.getLastname().equalsIgnoreCase(surname)){
                persons.add(p);
            }
        }
        return persons;
    }


    public ArrayList<Person> getPeopleFromBirthplace (String city){
        Iterator<Person> itPerson = this.people.iterator();
        ArrayList<Person> result = new ArrayList<>();
        Person actual;
        while(itPerson.hasNext()) {
            actual=itPerson.next();
            if(actual.getBirthplace().equals(city)) {
                result.add(actual);
            }
        }
        return result;
    }


    public Person findPerson(String id){
        Iterator<Person> itPerson = this.people.iterator();
        while(itPerson.hasNext()) {
            Person person = itPerson.next();
            if(person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

}
