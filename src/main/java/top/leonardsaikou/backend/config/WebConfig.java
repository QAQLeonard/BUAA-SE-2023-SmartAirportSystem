package top.leonardsaikou.backend.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import top.leonardsaikou.backend.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(@org.jetbrains.annotations.NotNull InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080","http://10.192.73.98:8080","http://10.192.231.236:8080","http://10.192.89.184:8080","http://10.193.224.236:8080","http://10.192.204.196:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}