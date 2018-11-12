package test;

import com.baizhi.entity.Course;
import com.baizhi.entity.User;
import com.baizhi.service.CourseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.Date;
import java.util.List;

public class CourseTest extends BaticTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void queryAll(){
        List<Course> byPage = courseService.findByPage(1, 2,"yes");
        for (Course course : byPage) {
            System.out.println(course);
        }
    }
}
