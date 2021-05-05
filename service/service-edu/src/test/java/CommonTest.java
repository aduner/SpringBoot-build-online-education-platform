import com.aduner.eduService.entity.EduTeacher;
import org.junit.Test;

/**
 * @author Aduner
 */

public class CommonTest {
    @Test
    public void Test1() {
        EduTeacher t1=new EduTeacher();
        t1.setId("1");
        EduTeacher t2=t1;
        EduTeacher t3=t2;
        t2=new EduTeacher();
        t2.setId("2");
        System.out.println(t3.getId());
    }
}