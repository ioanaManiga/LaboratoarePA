import java.util.ArrayList;
import java.util.List;

public class Resident {
    private String name;
    private List<Hospital> preferences;

    public Resident() {
        this.preferences = new ArrayList<>();
    }

    public Resident(String name) {
        this.name = name;
        this.preferences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hospital> getPreferences() {
        return preferences;
    }

    public void addPreferences(Hospital hospital) {
        preferences.add(hospital);
    }

    public String toString() {
        return this.getName();
    }
}
