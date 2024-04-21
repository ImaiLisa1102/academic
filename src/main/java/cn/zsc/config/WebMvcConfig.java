package cn.zsc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("cn.zsc")
public class WebMvcConfig implements WebMvcConfigurer {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedOriginPatterns("*") // 使用allowedOriginPatterns替代allowedOrigins
              .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
              .maxAge(3600)
              .allowCredentials(true);
   }
}
