package br.com.alurafood.pedidos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper MapperUtils() {
        return new ModelMapper();
    }

    public <D> D getModelMapper(Object objeto, Class<D> destino) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(objeto, destino);
    }
}

