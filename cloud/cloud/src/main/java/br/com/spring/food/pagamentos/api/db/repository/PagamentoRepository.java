package br.com.spring.food.pagamentos.api.db.repository;

import br.com.spring.food.pagamentos.api.DTO.PagamentoDTO;
import br.com.spring.food.pagamentos.api.db.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    //Optional<PagamentoEntity> findBy(Long id);

}
