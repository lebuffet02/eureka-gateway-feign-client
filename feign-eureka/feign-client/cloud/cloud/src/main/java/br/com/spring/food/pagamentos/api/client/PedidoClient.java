package br.com.spring.food.pagamentos.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pedidos-ms")
public interface PedidoClient {

    @RequestMapping(value = "/pedidos/{id}/pago", method = RequestMethod.PUT)
    void atualizaPagamento(@PathVariable(name = "id") Long id);


}
