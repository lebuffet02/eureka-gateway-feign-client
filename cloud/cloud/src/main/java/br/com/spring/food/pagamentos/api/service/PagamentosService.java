package br.com.spring.food.pagamentos.api.service;

import br.com.spring.food.pagamentos.api.DTO.PagamentoDTO;
import br.com.spring.food.pagamentos.api.db.entity.PagamentoEntity;
import br.com.spring.food.pagamentos.api.db.repository.PagamentoRepository;
import br.com.spring.food.pagamentos.api.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentosService {

    @Autowired
    PagamentoRepository repository;

    public List<PagamentoEntity> getAllPagamentos() {
        return repository.findAll();
    }

    public Page<Object> obterTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(pagamentoEntity -> MapperUtils.getMapper(pagamentoEntity, PagamentoDTO.class));
    }
}
