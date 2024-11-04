import classes.Person;
import classes.Relationship;
import classes.SocialNetwork;
import incl.DoubleOrderedList;
import incl.OrderedList;
import incl.exceptions.*;

import javax.swing.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Prints the available options for the main menu on screen.
     */
    public static void showMenu(){
    	System.out.println("Choose one option between the next ones:");
        System.out.println("1. Load people into network");
        System.out.println("2. Load Reationships");
        System.out.println("3. Print out people");
        System.out.println("4. Search");
        System.out.println("5. Get fiends from surname");   			//6th point on project description (2nd milestone)
        System.out.println("6. Find people from a bithplace");			//7th point on project description (2nd milestone)
        System.out.println("7. Find people from two birthdates");		//8th point on project description (2nd milestone)
        System.out.println("8. Find people from others birthplaces");	//9th point on project description (2nd milestone)
        System.out.println("9. Split profiles");						//10th point on project description (2nd milestone)
        System.out.println("10. Log out");
    }

    /**
     * Loads a file from the users' filesystem.
     * @return A File object for the given path, or null if no valid file was found.
     */
    public static File loadFile(){
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            return new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }

    /**
     * Given a user file, processes all users on it.
     * @param filePeople The file containing information from all the users
     * @return Returns an [object] containing all the users in the given file.
     */

    public static DoubleOrderedList<Person> loadPeople(File filePeople) {

        DoubleOrderedList<Person> people = new DoubleOrderedList<>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePeople));
            br.readLine();
            line = br.readLine();

            while (line != null){
                String [] personData = line.split(",");
                Person p = loadPerson(personData);
                people.add(p);
                line = br.readLine();
            }
            br.close();
        }catch (IOException e){
            System.out.println("File not found");
        } catch (ParseException e) {
            System.out.println("Date format exception");
        }

        return people;
    }

    /**
     *
     * @param personData An array containing all the data for a person, ordered.
     * @return Returns a Person object with the given data.
     * @throws ParseException Exception raised if the data received is not correctly formatted.
     */
    public static Person loadPerson(String [] personData) throws ParseException {

        String id,name,lastname,gender,home,groupCode, birthplace;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date birthdate;
        String [] studieDat;
        String [] workplaces;
        String [] films;

        id = personData[0];
        name = personData[1];
        lastname = personData[2];
        birthdate = dateFormat.parse(personData[3]);
        gender = personData[4];
        birthplace = personData[5];
        home = personData[6];
        studieDat = personData[7].split(";");
        workplaces = personData[8].split(";");
        films = personData[9].split(";");
        groupCode = personData[10];

        return new Person(id,name,lastname,birthdate,gender,birthplace,home,studieDat,workplaces,films,groupCode);
    }


    private static OrderedList<Relationship> loadRelations(File relationsFile) {

        OrderedList<Relationship> relations = new OrderedList<>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(relationsFile));
            br.readLine();
            line = br.readLine();

            while (line != null){
                String [] relationship = line.split(",");
                Relationship r = new Relationship(relationship[0],relationship[1]);
                relations.add(r);
                line = br.readLine();
            }
            br.close();
            Iterator<Relationship> it = relations.iterator();
            while(it.hasNext()){
                Relationship r = it.next();
                System.out.println(r.toString());
            }
        }catch (IOException e){
            System.out.println("File not found");
        }
        return relations;
    }
    
    private static DoubleOrderedList<Person> getPeopleFromBirthplace (DoubleOrderedList<Person> people, String city){
    	Iterator<Person> itPerson = people.iterator();
    	DoubleOrderedList<Person> result = new DoubleOrderedList<>();
        Person actual;
        while(itPerson.hasNext()) {
        	actual=itPerson.next();
        	if(actual.getBirthplace().equals(city)) {
        		result.add(actual);
        	}
        }
        return result;
    }


    private static ArrayList<String> loadPeopleResidential (SocialNetwork sn, File filePeople){
        ArrayList<String> birthplaces = new ArrayList<>();
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePeople));
            br.readLine();
            line = br.readLine();
            while (line != null){
                Person person = sn.findPerson(line);
                if(person != null && !birthplaces.contains(person.getBirthplace())) {
                    birthplaces.add(person.getBirthplace());
                }
                line = br.readLine();
            }
            br.close();
        }catch (IOException e){
            System.out.println("File not found");
        }
        return birthplaces;
    }
    
    private static ArrayList<Person> getPeopleSameBirthplace(SocialNetwork sn,ArrayList<String> birthplaces) {
    	ArrayList<Person> result = new ArrayList<>();
        for(String birthplace : birthplaces) {
            ArrayList<Person> aux = sn.getPeopleFromBirthplace(birthplace);
            for(Person person : aux) {
                if(!result.contains(person)) {
                    result.add(person);
                }else{
                    System.out.println("hola");
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        short selectedOption;
        SocialNetwork socialNetwork = new SocialNetwork();
        DoubleOrderedList<Person> people = null;
        OrderedList<Relationship> relations = null;
        Scanner sc = new Scanner(System.in);
        Iterator<Person> itPerson;
        Person actual;
        
        do{
            showMenu();
            selectedOption = sc.nextShort();
            switch (selectedOption){
                case 1:
                    File peopleFile = loadFile();
                    people = loadPeople(peopleFile);
                    socialNetwork.setPeople(people);
                    break;
                case 2:
                    if(!socialNetwork.getPeople().isEmpty()){
                        File relationsFile = loadFile();
                        relations = loadRelations(relationsFile);
                        socialNetwork.setRelations(relations);
                    }else{
                        System.out.println("No people loaded");
                    }
                    break;
                case 3:
                    System.out.println("people:");
                    if(!socialNetwork.getPeople().isEmpty()){
                        socialNetwork.showPeople();
                    }else{
                        System.out.println("No people loaded");
                    }
                    break;
                case 4:
                    System.out.print("introduce ID: ");
                    String id = sc.next();
                    System.out.println("Friends of "+id+": ");
                    if(!socialNetwork.getPeople().isEmpty()){
                        socialNetwork.showRelations(id);
                    }else{
                        System.out.println("No people loaded");
                    }
                    break;
                    
                case 5:
                    System.out.print("introduce surname: ");
                    String surname = sc.next();
                    ArrayList<Person> sameSurname = socialNetwork.findFromSurname(surname);
                    for(Person p : sameSurname){
                        System.out.print("relations for "+ p.getId() + "(" +p.getLastname()+"): ");
                        System.out.println(socialNetwork.getRelationships(p.getId()).toString());
                    }
                    break;
                case 6:
                	System.out.print("introduce city: ");
                    String city = sc.next();
                    DoubleOrderedList<Person> peopleSameCity = getPeopleFromBirthplace(people, city);
                    itPerson = peopleSameCity.iterator();
                    int numberOfPerson=1;
                    while(itPerson.hasNext()) {
                    	actual = itPerson.next();
                    	System.out.println("Person " + numberOfPerson + ": " + actual.toStringIDandSurname());
                    	numberOfPerson=numberOfPerson+1;
                    }
                    break;
                case 7:
                	
                case 8:
                	File residentialFile = loadFile();
                    ArrayList<String> birthplaces = loadPeopleResidential(socialNetwork, residentialFile);
                    ArrayList<Person> sameBirthplaces = getPeopleSameBirthplace(socialNetwork, birthplaces);
                    for(Person person : sameBirthplaces){
                        System.out.println(person.toStringNameSurnameBirthplaceStudiedat());
                    }
                    break;
                case 9:
                	
                case 10:
                    System.out.println("Log out...");
                    break;
                default:
                    System.out.println("Option not found");
                    break;
            }
        }while(selectedOption != 10);
        sc.close();
    }
}