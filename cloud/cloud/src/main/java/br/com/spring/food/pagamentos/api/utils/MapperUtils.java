package br.com.spring.food.pagamentos.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {


    public static <T> Object getMapper (T objeto, Object objetoParaConverter) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(objeto, objetoParaConverter.getClass());
    }
}
