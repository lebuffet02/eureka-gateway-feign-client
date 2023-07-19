package br.com.spring.food.pagamentos.api.controller;

import br.com.spring.food.pagamentos.api.service.PagamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PagamentosController {

    @Autowired
    PagamentosService service;
}
