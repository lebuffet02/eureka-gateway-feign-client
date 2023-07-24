package br.com.spring.food.pagamentos.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {


    public static <T> T getMapper (Object objeto, Class<T> objetoParaConverter) throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(objeto, objetoParaConverter);
    }
}
