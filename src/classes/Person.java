package classes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Person implements Comparable<Person> {

    String id;
    String name;
    String lastname;
    Date birthdate;
    String gender;
    String birthplace;
    String home;
    String [] studiedat;
    String [] workplaces;
    String [] films;
    String groupCode;

    public Person() {

    }

    public Person(String id, String name, String lastname,Date birthdate,String gender,String birthplace,String home,String[] studiedat,String[] workplaces,String[] films,String groupCode) {
        this.films = films;
        this.groupCode = groupCode;
        this.workplaces = workplaces;
        this.studiedat = studiedat;
        this.home = home;
        this.birthdate = birthdate;
        this.gender = gender;
        this.birthplace = birthplace;
        this.lastname = lastname;
        this.name = name;
        this.id = id;
    }

    public Person(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String[] getStudiedat() {
        return studiedat;
    }

    public void setStudiedat(String[] studiedat) {
        this.studiedat = studiedat;
    }

    public String[] getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(String[] workplaces) {
        this.workplaces = workplaces;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getBirthplace() {
        return birthplace;
    }
    
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public String toString() {
    	String resul;
    	try {
    		resul="Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", birthdate=" + new SimpleDateFormat("dd-MM-yyyy").format(birthdate) +
                    ", gender='" + gender + '\'' +
                    ", birthplace='" + birthplace + '\'' +
                    ", home='" + home + '\'' +
                    ", studiedat=" + Arrays.toString(studiedat) +
                    ", workplaces=" + Arrays.toString(workplaces) +
                    ", films=" + Arrays.toString(films) +
                    ", groupCode='" + groupCode + '\'' +
                    '}';
    	}catch(NullPointerException e) {
    		resul="Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", birthdate= 'null'" +
                    ", gender='" + gender + '\'' +
                    ", birthplace='" + birthplace + '\'' +
                    ", home='" + home + '\'' +
                    ", studiedat=" + Arrays.toString(studiedat) +
                    ", workplaces=" + Arrays.toString(workplaces) +
                    ", films=" + Arrays.toString(films) +
                    ", groupCode='" + groupCode + '\'' +
                    '}';
    	}
        return resul;
    }

    public String toStringIDandSurname() {
    	return "id= " + id + ", lastname= " + lastname;
    }
    public String toStringNameSurnameBirthplaceStudiedat() {
    	return "name= " + name + ", lastname= " + lastname + ", birthplace= " + birthplace + ", studiedat= " + Arrays.toString(studiedat);
    }
    public String toStringBirthplaceSurnameName() {
        return "birthplace= " + birthplace + ", lastname= " + lastname + ", name= " + name;
    }
    @Override
    public int compareTo(Person o) {
        return this.id.toLowerCase().compareTo(o.id.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        Person p = (Person) o;
    	if (this.id.equalsIgnoreCase(p.id)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
