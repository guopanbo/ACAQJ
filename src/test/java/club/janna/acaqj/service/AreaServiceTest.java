package club.janna.acaqj.service;

import club.janna.acaqj.pojo.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: guopanbo
 * @Date: 2018/8/2 20:43
 * @Description:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class AreaServiceTest {


//    @Autowired
    private AreaService areaService;

//    @Test
    public void testInsert() {
        Area area = new Area();
        area.setCode("1");
        area.setName("b");
        areaService.insert(area);
    }
}
