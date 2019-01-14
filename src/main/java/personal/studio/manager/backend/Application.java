package personal.studio.manager.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import personal.studio.manager.backend.restful.RestfulAPIConfig;

@SpringBootApplication//(exclude=DispatcherServletAutoConfiguration.class)
public class Application {
	


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


    @Bean
    public ServletRegistrationBean foo() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(RestfulAPIConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/restful-api/*");
        servletRegistrationBean.setName("restful-api");
        return servletRegistrationBean;
    }

}

