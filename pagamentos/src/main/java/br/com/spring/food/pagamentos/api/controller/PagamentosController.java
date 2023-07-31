package br.com.spring.food.pagamentos.api.controller;

import br.com.spring.food.pagamentos.api.DTO.PagamentoDTO;
import br.com.spring.food.pagamentos.api.service.PagamentosService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/pagamentos")
public class PagamentosController {

    @Autowired
    PagamentosService service;

    @GetMapping
    public ResponseEntity<Page<PagamentoDTO>> obterTodosPagamentos(
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.obterTodosPagamentos(pageable));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PagamentoDTO> obterPorId(
            @PathVariable(name = "id") @NonNull Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamentos(
            @RequestBody @Valid PagamentoDTO pagamentoDTO, UriComponentsBuilder uriBuilder) {
        PagamentoDTO pagamento = service.criarPagamentos(pagamentoDTO);
        URI http = uriBuilder.path("/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(http).body(pagamento);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamentos(
            @PathVariable(name = "id") @NonNull Long id,
            @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(service.atualizarPagamentos(id, pagamentoDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deletarPagamentosPorId(
            @PathVariable(name = "id") @NonNull Long id) {
        service.deletarPagamentosPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deletarTodos() {
        service.deletarTodos();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{id}/confirmar")
    public ResponseEntity<?> confirmarPagamento(
            @PathVariable(name = "id") @NonNull Long id) {
        service.confirmarPagamento(id);
        return ResponseEntity.ok().build();
    }
}

