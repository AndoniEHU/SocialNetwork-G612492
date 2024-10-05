import classes.Person;

import javax.swing.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Prints the available options for the main menu on screen.
     */
    public static void showMenu(){
        System.out.println("1. Load people into network");
        System.out.println("2. Load Reationships");
        System.out.println("3. Print out people");
        System.out.println("4. Search");
        System.out.println("5. Log out");

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
     * @param peopleFile The file containing information from all the users
     * @return Returns an [object] containing all the users in the given file.
     */
	 public static ArrayList<Person> loadPeople(File peopleFile){

        String line;
        ArrayList<Person> people = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(peopleFile));
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

        String id,name,lastname,gender,home,groupCode;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYY");
        Date birthdate;
        String [] studieDat;
        String [] workplaces;
        String [] films;

        id = personData[0];
        name = personData[1];
        lastname = personData[2];
        birthdate = dateFormat.parse(personData[3]);
        gender = personData[4];
        home = personData[5];
        studieDat = personData[6].split(";");
        workplaces = personData[7].split(";");
        films = personData[8].split(";");
        groupCode = personData[9];

        return new Person(id,name,lastname,birthdate,gender,home,studieDat,workplaces,films,groupCode);
    }

    /**
     * Prints information of all people in a given array.
     * @param people An array containing a list of people.
     */
	 public static void showPeople(ArrayList<Person> people){
        if(people.isEmpty()){
            System.out.println("People not loaded");
        }else{
            for(Person p : people){
                System.out.println(p.toString());
            }
        }
    }

    public static void main(String[] args) {

        short selectedOption;
        ArrayList<Person> people = null;
        Scanner sc = new Scanner(System.in);
        
        do{
            showMenu();
            selectedOption = sc.nextShort();
            switch (selectedOption){
                case 1:
                    File peopleFile = loadFile();
                    people = loadPeople(peopleFile);
                    break;
                case 2:
                    File relationsFile = loadFile();
                    break;
                case 3:
                    System.out.println("people");
                    if(people != null){
                        showPeople(people);
                    }else{
                        System.out.println("People not loaded");
                    }
                    break;
                case 4:
                    System.out.print("introduce ID:");
                    break;
                case 5:
                    System.out.println("Login out...");
                    break;
                default:
                    System.out.println("Option not found");
                    break;
            }
        }while(selectedOption != 5);
    	
    }
    
}