package br.com.alurafood.pedidos.service;

import br.com.alurafood.pedidos.config.MapperConfig;
import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.model.Pedido;
import br.com.alurafood.pedidos.model.Status;
import br.com.alurafood.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    MapperConfig mapperConfig;


    public List<PedidoDto> obterTodos() {
        return repository.findAll().stream()
                .map(p -> mapperConfig.getModelMapper(p, PedidoDto.class))
                .collect(Collectors.toList());
    }

    public PedidoDto obterPorId(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapperConfig.getModelMapper(pedido, PedidoDto.class);
    }

    public PedidoDto criarPedido(PedidoDto dto) {
        Pedido pedido = mapperConfig.getModelMapper(dto, Pedido.class);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        Pedido salvo = repository.save(pedido);
        return mapperConfig.getModelMapper(salvo, PedidoDto.class);
    }

    public PedidoDto atualizaStatus(Long id, StatusDto dto) {
        Pedido pedido = repository.porIdComItens(id);
        if (pedido == null) {
            throw new EntityNotFoundException();
        }
        pedido.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), pedido);
        return mapperConfig.getModelMapper(pedido, PedidoDto.class);
    }

    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = repository.porIdComItens(id);
        if (pedido == null) {
            throw new EntityNotFoundException();
        }
        pedido.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, pedido);
    }
}
