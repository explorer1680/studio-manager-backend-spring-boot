package personal.studio.manager.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import personal.studio.manager.backend.restful.RestfulAPIConfig;

@SpringBootApplication//(exclude=DispatcherServletAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {
	


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//    @Configuration
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .httpBasic()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/index.html", "/", "/home", "*.js", "/*.js", "/login").permitAll()
//                    .anyRequest().authenticated()
//                    .and().csrf()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        }
//    }

//
//    @Bean
//    public ServletRegistrationBean foo() {
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.register(RestfulAPIConfig.class);
//        dispatcherServlet.setApplicationContext(applicationContext);
//        ServletRegistrationBean servletRegistrationBean =
//                new ServletRegistrationBean<>(dispatcherServlet, "/restful-api/*");
//        servletRegistrationBean.setName("restful-api");
//        return servletRegistrationBean;
//    }

}

