package com.example.webservice.config;

import com.example.webservice.services.CompteRestJaxRSAPI;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
    @Bean
    @Primary
    public ResourceConfig resourceConfig() {
        ResourceConfig jerseyServlet = new ResourceConfig();
        jerseyServlet.register(CompteRestJaxRSAPI.class);
        return jerseyServlet;
    }

    public JerseyConfig() {
        register(CompteRestJaxRSAPI.class);
        register(MoxyXmlFeature.class);
        register(JacksonFeature.class);
    }
}