import java.util.*;
import java.util.stream.Collectors;

public class Matching {
    private Problem problem = new Problem();
    private Map<Hospital, List<Resident>> solution = new HashMap<>();

    public Matching() {
    }

    public Matching(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Map<Hospital, List<Resident>> getProblemSolution() {
        return solution;
    }

    public void printSolution(){
        solution.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }

    public void solution(){
        List<Resident> assignedResidents = new ArrayList<>();
        for ( Hospital hospital : problem.getHospital()) {
            int residentsNumber = 0;
            List<Resident> residentsOfHospital = new ArrayList<>();
            for(Resident resident : hospital.getPreferences()){
                if(residentsNumber < hospital.getCapacity()){
                    residentsNumber++;
                    if(!assignedResidents.contains(resident))
                    {
                        assignedResidents.add(resident);
                        residentsOfHospital.add(resident);
                    }
                }
            }
            solution.put(hospital, residentsOfHospital);
        }
    }

    public boolean stable(){
        for(Resident resident : problem.getResidents()){
                for(Hospital hospital : resident.getPreferences()){
                    if(solution.get(hospital).contains(resident)){
                        break;
                    } else {
                        if(solution.get(hospital).size() < hospital.getCapacity())
                            return false;
                    }
                }
        }
        return true;
    }
}


