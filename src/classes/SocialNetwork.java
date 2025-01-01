package classes;

import incl.DoubleOrderedList;
import incl.LinkedBinarySearchTree;
import incl.OrderedList;
import incl.SedgewickCommon.Stack;
import incl.graphsundirected.BreadthFirstPaths;
import incl.graphsundirected.DepthFirstPaths;
import incl.graphsundirected.SymbolGraph;

import java.util.*;

public class SocialNetwork {

    DoubleOrderedList<Person> people;
    OrderedList<Relationship> relations;
    SymbolGraph graph;

    public SocialNetwork() {
        people = new DoubleOrderedList<>();
        relations = new OrderedList<>();
    }

    public DoubleOrderedList<Person> getPeople() {
        return people;
    }

    public void setPeople(DoubleOrderedList<Person> people) {
        this.people = people;
        if(graph == null){
            graph = new SymbolGraph(people);
        }else{
            graph = new SymbolGraph(this.people);
            graph.addEdges(relations);
        }
    }

    public OrderedList<Relationship> getRelations() {
        return relations;
    }

    public void setRelations(OrderedList<Relationship> relations) {
        this.relations = relations;
        graph = new SymbolGraph(this.people);
        graph.addEdges(this.relations);
    }

    public void showPeople(){
        System.out.println(this.people.toString());
    }

    public boolean contains(Person p){
        Iterator<Person> it = people.iterator();
        while(it.hasNext()){
            if(it.next().getId().equals(p.getId())){
                return true;
            }
        }
        return false;
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

    public ArrayList<String> getRelationshipsWithSurname(String id){
        Iterator<Relationship> it = relations.iterator();
        ArrayList<String> result = new ArrayList<>();
        while(it.hasNext()){
            Relationship r = it.next();
            Person aux;
            if(r.getFriend1().equals(id)){
                aux = findPerson(r.getFriend2());
                result.add(aux.getId() + " " + aux.getLastname());
            }

            else if(r.getFriend2().equals(id)){
                aux = findPerson(r.getFriend1());
                result.add(aux.getId() + " " + aux.getLastname());
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
            if(actual.getBirthplace().equalsIgnoreCase(city)) {
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

    public ArrayList<String[]> createProfiles(){
        ArrayList<String[]> profiles = new ArrayList<>();
        Iterator<Person> itPerson = this.people.iterator();
        while(itPerson.hasNext()) {
            Person person = itPerson.next();
            boolean exist = false;
            for(int i=0;i<profiles.size() && !exist;i++){
                String [] p = profiles.get(i);
                Arrays.sort(p);
                Arrays.sort(person.getFilms());
                if(Arrays.equals(p,person.getFilms())){
                    exist = true;
                }
            }
            if(!exist) {
                String[] filmsLiked = person.getFilms();
                profiles.add(filmsLiked);
            }
        }
        return profiles;
    }

    public ArrayList<Person> splitProfiles(String[] profile){
        ArrayList<Person> sameProfile = new ArrayList<>();
        Iterator<Person> itPerson = this.people.iterator();
        while(itPerson.hasNext()) {
            Person person = itPerson.next();
            if(Arrays.equals(person.getFilms(), profile)){
                sameProfile.add(person);
            }
        }
        return sameProfile;
    }

    public ArrayList<Person> findBetweenYears(int year1,int year2){
        ArrayList<Person> persons = new ArrayList<>();
        Iterator<Person> itPerson = this.people.iterator();
        while(itPerson.hasNext()) {
            Person person = itPerson.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(person.getBirthdate());
            int yearBirth = calendar.get(Calendar.YEAR);

            if(year1<=yearBirth && year2>=yearBirth){
                persons.add(person);
            }
        }

        persons.sort(Comparator.comparing(Person::getBirthplace).thenComparing(Person::getLastname).thenComparing(Person::getName));

        return persons;
    }

    public ArrayList<Person> BSTfindBetweenYears(int year1,int year2){
        LinkedBinarySearchTree<Person> tree = new LinkedBinarySearchTree<>(PersonComparator.byBirthplaceLastnameName());
        ArrayList<Person> peopleBetween = new ArrayList<>();
        Iterator<Person> itPerson = this.people.iterator();
        while(itPerson.hasNext()) {
            Person person = itPerson.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(person.getBirthdate());
            int yearBirth = calendar.get(Calendar.YEAR);
            if(year1<=yearBirth && year2>=yearBirth) {
                tree.addElement(person);
            }
        }
        Iterator <Person> treeIt= tree.iteratorInOrder();
        while(treeIt.hasNext()){
            Person person = treeIt.next();
            peopleBetween.add(person);
        }

        return peopleBetween;
    }

    public ArrayList<String> BFS (String source, String destination){
        int s = graph.index(source);
        int d = graph.index(destination);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph.G(),s);
        Stack<Integer> stack = bfs.check(graph.G(),s,d);
        if(stack == null){
            return new ArrayList<>();
        }
        Iterator<Integer> it = stack.iterator();
        ArrayList<String> result = new ArrayList<>();
        while(it.hasNext()){
            int v = it.next();
            result.add(graph.name(v));
        }
        return result;
    }

    // Provides an alternative path to the first one found by BFS, or null if it doesn't exist
    public ArrayList<String> altDFS (String source, String destination){
        int s = graph.index(source);
        int d = graph.index(destination);

        // We compute the route that BFS finds first
        ArrayList<String> BFSpath = BFS(source, destination);

        // The result will contain the alternate route
        ArrayList<String> result = new ArrayList<String>();

        // We try the first path found with DFS
        DepthFirstPaths dfs = new DepthFirstPaths(graph.G(),s);
        Iterable<Integer> firstPath = dfs.pathTo(d);

        // If the friends aren't connected, return an empty array
        if (firstPath==null) return result;

        Iterator<Integer> it = firstPath.iterator();

        // We construct the result
        while (it.hasNext()){
            int v = it.next();
            result.add(graph.name(v));
        }

        // If the path found with DFS is different than the one found with BFS, we return it
        if (!BFSpath.equals(result)) {
            return result;
        }
        // Otherwise, we look for the SECOND path found by DFS
        else {
            result = new ArrayList<String>();
            dfs = new DepthFirstPaths(graph.G(),s, d);
            firstPath = dfs.pathTo(d);

            // If there's no alternative path, we return an empty array
            if (firstPath == null) {
                return result;
            }

            it = firstPath.iterator();
            while (it.hasNext()) {
                int v = it.next();
                result.add(graph.name(v));
            }
            return result;
        }
    }


    public ArrayList<String> DFS (String source, String destination) {
        int s = graph.index(source);
        int d = graph.index(destination);
        DepthFirstPaths dfs = new DepthFirstPaths(graph.G(), s);
        Iterator<Integer> it = dfs.pathTo(d).iterator();
        ArrayList<String> result = new ArrayList<>();
        while(it.hasNext()){
            result.add(graph.name(it.next()));
        }
        return result;
    }

}
