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

    public static void showMenu(){

        System.out.println("1. Load people into network");
        System.out.println("2. Load Reationships");
        System.out.println("3. Print out people");
        System.out.println("4. Search");
        System.out.println("5. Log out");


    }

    public static File loadFile(){
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            return new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }

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

        short selectedOpiton;
        ArrayList<Person> people = null;
        Scanner sc = new Scanner(System.in);
        
        do{
            showMenu();
            selectedOpiton = sc.nextShort();
            switch (selectedOpiton){
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
        }while(selectedOpiton != 5);

    }
}