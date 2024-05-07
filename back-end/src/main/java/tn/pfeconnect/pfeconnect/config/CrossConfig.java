package tn.pfeconnect.pfeconnect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CrossConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

            registry.addMapping("/stomp-endpoint")
                    .allowedOrigins("http://localhost:4200", "http://localhost:4300")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
        }
}
