package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);
        ac.refresh();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(
                "app", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
