package com.example.book_store.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class ComponentConfiguration {


    @Bean
    public ModelMapper initModelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setAmbiguityIgnored(true);
        return mm;
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer() {
        return (resolver) -> {
            resolver.setOneIndexedParameters(true);
        };
    }
}
