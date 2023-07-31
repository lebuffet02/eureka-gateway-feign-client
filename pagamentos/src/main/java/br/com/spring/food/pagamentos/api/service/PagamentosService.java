package br.com.spring.food.pagamentos.api.service;

import br.com.spring.food.pagamentos.api.DTO.PagamentoDTO;
import br.com.spring.food.pagamentos.api.config.MapperConfig;
import br.com.spring.food.pagamentos.api.constants.Status;
import br.com.spring.food.pagamentos.api.db.entity.PagamentoEntity;
import br.com.spring.food.pagamentos.api.db.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentosService {

    @Autowired
    PagamentoRepository repository;
    @Autowired
    MapperConfig mapper;
//    @Autowired
//    PedidoClient client;


    public Page<PagamentoDTO> obterTodosPagamentos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(p -> mapper.getModelMapper(p, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long id) {
        PagamentoEntity pagamento = repository.findById(id).orElseThrow(RuntimeException::new);
        return mapper.getModelMapper(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO criarPagamentos(PagamentoDTO pagamentoDTO) {
        return getPagamento(pagamentoDTO);
    }

    public PagamentoDTO atualizarPagamentos(Long id, PagamentoDTO pagamentoDTO) {
       return getPagamento(id, pagamentoDTO);
    }

    public void deletarPagamentosPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public void deletarTodos() {
        repository.deleteAll();
    }

    public void confirmarPagamento(Long id) {
        Optional<PagamentoEntity> pagamento = repository.findById(id);
        if (pagamento.isPresent()) {
            pagamento.get().setStatus(Status.CONFIRMADO);
            repository.save(pagamento.get());
            //client.atualizaPagamento(pagamento.get().getPedidoId());
        }
        throw new EntityNotFoundException("id not found");
    }

    private PagamentoDTO getPagamento(PagamentoDTO pagamentoDTO) {
        return getPagamento(null, pagamentoDTO);
    }

    private PagamentoDTO getPagamento(Long id, PagamentoDTO pagamentoDTO) {
        PagamentoEntity pagamento = mapper.getModelMapper(pagamentoDTO, PagamentoEntity.class);
        pagamento.setId(id);
        if (pagamentoDTO.getStatus() != null) {
            pagamento.setStatus(pagamentoDTO.getStatus());
        }
        pagamento = repository.save(pagamento);
        return mapper.getModelMapper(pagamento, PagamentoDTO.class);
    }
}
