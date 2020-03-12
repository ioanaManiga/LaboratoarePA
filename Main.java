import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        Resident R0 = new Resident("R0");
        Resident R1 = new Resident("R1");
        Resident R2 = new Resident("R2");
        Resident R3 = new Resident("R3");

        Hospital H0 = new Hospital("H0", 1);
        Hospital H1 = new Hospital("H1", 2);
        Hospital H2 = new Hospital("H2", 2);



        R0.addPreferences(H0);
        R0.addPreferences(H1);
        R0.addPreferences(H2);

        R1.addPreferences(H0);
        R1.addPreferences(H1);
        R1.addPreferences(H2);

        R2.addPreferences(H0);
        R2.addPreferences(H1);

        R3.addPreferences(H0);
        R3.addPreferences(H2);

        H0.addPreferences(R3);
        H0.addPreferences(R0);
        H0.addPreferences(R1);
        H0.addPreferences(R2);

        H1.addPreferences(R0);
        H1.addPreferences(R2);
        H1.addPreferences(R1);

        H2.addPreferences(R0);
        H2.addPreferences(R1);
        H2.addPreferences(R3);

        //adaugam rezidenti la lista
        List<Resident> residentList = new ArrayList<>();
        residentList.add(R1);
        residentList.add(R0);
        residentList.add(R3);
        residentList.add(R2);

        //sortam rezidentii folosind comparatorul
        residentList.sort(Comparator.comparing(Resident::getName));

        //set
        Set<Hospital> hospitalSet = new TreeSet<>();
        hospitalSet.add(H0);
        hospitalSet.add(H1);
        hospitalSet.add(H2);

        //hashmap
        Map<Resident, List<Hospital>> residentPreferences = new HashMap<>();
        residentPreferences.put(R0, Arrays.asList(H0, H1, H2));
        residentPreferences.put(R1, Arrays.asList(H0, H1, H2));
        residentPreferences.put(R2, Arrays.asList(H0, H1));
        residentPreferences.put(R3, Arrays.asList(H0, H2));

        //treemap
        Map<Hospital, List<Resident>> hospitalPreferences = new TreeMap<>();
        hospitalPreferences.put(H0, H0.getPreferences());
        hospitalPreferences.put(H1, H1.getPreferences());
        hospitalPreferences.put(H2, H2.getPreferences());
        System.out.println(hospitalPreferences);

        List<Hospital> hospitalsToMatch = Arrays.asList(H0, H2);
        List<Resident> resultedResidents = residentList
                .stream()
                .filter(x -> residentPreferences.get(x).containsAll(hospitalsToMatch))
                .collect(Collectors.toList());

        System.out.println("Residents that prefer hospitals H0 and H2");
        resultedResidents
                .forEach(System.out::println);

        List<Hospital> resultedHospital = hospitalPreferences.keySet()
                .stream()
                .filter(x -> x.topPreference().equals(R0))
                .collect(Collectors.toList());

        System.out.println();

        System.out.println("Hospitals that have resident RO as their top preference");

        resultedHospital
                .forEach(System.out::println);
    }

}
