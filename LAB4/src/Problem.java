import java.util.*;

public class Problem {
    private Set<Hospital> hospitals = new TreeSet<>();
    private List<Resident> residents = new ArrayList<>();
    private Map<Resident, List<Hospital>> residentPreferences = new HashMap<>();
    private Map<Hospital, List<Resident>> hospitalPreferences = new TreeMap<>();

    public Problem() {
    }

    public Problem(Set<Hospital> hospitals, List<Resident> residents) {
        this.hospitals = hospitals;
        this.residents = residents;
    }

    public Problem(Set<Hospital> hospitalSet, List<Resident> residents, Map<Resident, List<Hospital>> residentPreferences, Map<Hospital, List<Resident>> hospitalPreferences) {
        this.hospitals = hospitalSet;
        this.residents = residents;
        this.residentPreferences = residentPreferences;
        this.hospitalPreferences = hospitalPreferences;
    }

    public Map<Hospital, List<Resident>> getHospitalPreferences() {
        return hospitalPreferences;
    }

    public void setHospitalPreferences(Map<Hospital, List<Resident>> hospitalPreferences) {
        this.hospitalPreferences = hospitalPreferences;
    }

    public Map<Resident, List<Hospital>> getResidentPreferences() {
        return residentPreferences;
    }

    public void setResidentPreferences(Map<Resident, List<Hospital>> residentPreferences) {
        this.residentPreferences = residentPreferences;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public Set<Hospital> getHospital() {
        return hospitals;
    }

    public void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitals = hospitalSet;
    }
}
