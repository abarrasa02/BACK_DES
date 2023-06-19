package com.example.AzentBACK.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracionCors  implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Esto aplica la configuración CORS a todos los endpoints
                .allowedOrigins("http://localhost:4200") // Aquí indicas desde qué origen se permiten las solicitudes
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","PATCH") // Métodos HTTP permitidos
                .allowCredentials(true); // Permite que se envíen cookies
    }
}
