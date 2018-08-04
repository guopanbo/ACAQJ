package club.janna.acaqj.service;

import club.janna.acaqj.pojo.ErrorLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 20:56
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class ErrorLogServiceTest {
    @Autowired
    private ErrorLogService errorLogService;

    @Test
    public void testInsert() {
        ErrorLog e = new ErrorLog();
        e.setMsg("abc");
        errorLogService.insert(e);
    }

    @Test
    public void testGetByTotal() {
        List<ErrorLog> errorLogs = errorLogService.getByTotal(100);
        System.out.println(errorLogs.size());
    }

    @Test
    public void testDeleteByIds() {
        errorLogService.deleteByIds(Arrays.asList(1, 2, 3));
    }
}
