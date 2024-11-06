package classes;

public class Relationship implements Comparable<Relationship>{

    private String friend1, friend2;

    public Relationship() {

    }

    public Relationship(String friend1, String friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public String getFriend1() {
        return friend1;
    }

    public void setFriend1(String friend1) {
        this.friend1 = friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "friend1='" + friend1 + '\'' +
                ", friend2='" + friend2 + '\'' +
                '}';
    }

    @Override
    public int compareTo(Relationship o) {
        if(this.friend1.toLowerCase().compareTo(o.friend1.toLowerCase()) == 0) {
            return this.friend2.toLowerCase().compareTo(o.friend2.toLowerCase());
        }
        return this.friend1.toLowerCase().compareTo(o.friend1.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        Relationship r = (Relationship) o;
        if(this.friend1.equalsIgnoreCase(r.friend1) && this.friend2.equalsIgnoreCase(r.friend2)) {
            return true;
        }
        return this.friend1.equalsIgnoreCase(r.friend2) && this.friend2.equalsIgnoreCase(r.friend1);
    }


}
