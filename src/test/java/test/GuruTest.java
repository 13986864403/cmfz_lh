package test;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.List;

public class GuruTest extends BaticTest {
    @Autowired
    private GuruService guruService;

    @Test
    public void queryAll(){
        List<Guru> all = guruService.findAll();
        for (Guru guru : all) {
            System.out.println(guru);
        }
    }
}
