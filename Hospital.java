import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital>{
    private String name;
    private List<Resident> preferences;
    private int capacity;

    public Hospital() {
        this.preferences = new ArrayList<>();
    }

    public Hospital(String name, int capacity) {
        this.capacity = capacity;
        this.name = name;
        this.preferences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Resident> getPreferences() {
        return preferences;
    }

    public void addPreferences(Resident resident) {
        preferences.add(resident);
    }


    public void seePreferences() {
        for(Resident resident : preferences) {
            System.out.println(resident);
        }
    }

    public Resident topPreference() {
        return preferences.get(0);
    }
    public int compareTo(Hospital hospital) {
        if (this.capacity == hospital.capacity) {

                if (this.preferences == hospital.preferences) {
                    return 0;
                } else {
                    if (this.preferences.size() > hospital.preferences.size()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        else {
            if(this.capacity > hospital.capacity) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    public String toString() {
        return this.getName();
    }
}
