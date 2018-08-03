package club.janna.acaqj.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by guopanbo on 18/7/9.
 */
public class ApplicationProvider {

    private ApplicationContext applicationContext;

    private ApplicationProvider() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
    }

    private static class ApplicationProviderFactory {
        private static ApplicationProvider applicationProvider = new ApplicationProvider();
    }

    public static ApplicationProvider getInstance() {
        return ApplicationProviderFactory.applicationProvider;
    }

    public ApplicationContext getApplicationContext() {
        if(applicationContext == null)
            throw new RuntimeException("application context is null");
        return applicationContext;
    }

    public <T> T getBean(Class<T> tClass) {
        if(applicationContext == null)
            throw new RuntimeException("application context is null");
        return applicationContext.getBean(tClass);
    }
}
