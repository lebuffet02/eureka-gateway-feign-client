package br.com.spring.food.pagamentos.api.db.repository;

import br.com.spring.food.pagamentos.api.db.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

}
