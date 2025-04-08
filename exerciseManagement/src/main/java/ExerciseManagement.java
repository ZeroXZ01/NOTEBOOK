import java.util.ArrayList;

public class ExerciseManagement {
    private ArrayList<Exercise> exercises;

    public ExerciseManagement(){
        this.exercises=new ArrayList<>();
    }

    public ArrayList<String> exerciseList(){
        ArrayList<String> list=new ArrayList<>();
        for(Exercise exercise:exercises){
            list.add(exercise.getName());
        }

        return list;
    }

    public void add(String exercise){
        this.exercises.add(new Exercise(exercise));
    }

    public void markAsCompleted(String newExercise) {
        for(Exercise exercise:exercises){
            if(exercise.getName().equals(newExercise)){
                exercise.setCompleted(true);
            }
        }
    }

    public boolean isCompleted(String newExercise) {
        for(Exercise exercise:exercises){
            if(exercise.getName().equals(newExercise)){
                return exercise.isCompleted();
            }
        }

        return false;
    }
}
