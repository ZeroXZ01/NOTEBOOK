import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExerciseManagementTest {
    private ExerciseManagement exerciseManagement;

    // This method will run before the below tests run, which cleans the repetitive creation of object for ExerciseManagement Class
    @Before
    public void initialize(){
        exerciseManagement = new ExerciseManagement();
    }

    @Test
    public void exerciseListEmptyAtBeginning(){
//        ExerciseManagement exerciseManagement=new ExerciseManagement();
        assertEquals(0, exerciseManagement.exerciseList().size());
    }

    @Test
    public void addingExerciseGrowListByOne(){
//        ExerciseManagement exerciseManagement=new ExerciseManagement();
        exerciseManagement.add("Write a test");
        assertEquals(1, exerciseManagement.exerciseList().size());
    }

    @Test
    public void addedExerciseIsInList(){
//        ExerciseManagement exerciseManagement=new ExerciseManagement();
        exerciseManagement.add("Write a test");
        assertTrue(exerciseManagement.exerciseList().contains("Write a test"));
    }

    @Test
    public void exerciseCanBeMarkedAsCompleted(){
        exerciseManagement.add("New exercise");
        exerciseManagement.markAsCompleted("New exercise");
        assertTrue(exerciseManagement.isCompleted("New exercise"));
    }
}
