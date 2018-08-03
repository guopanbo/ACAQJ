package club.janna.acaqj.provider;

import club.janna.acaqj.service.AreaService;
import org.springframework.context.ApplicationContext;

/**
 * @author guopanbo
 * @Title: ApplicationProviderTest
 * @Description: TODO
 * @date 2018/8/320:50
 */
public class ApplicationProviderTest {


    public static void main(String[] args) {
        AreaService areaService = ApplicationProvider.getInstance().getBean(AreaService.class);
        System.out.println(areaService);
    }
}
