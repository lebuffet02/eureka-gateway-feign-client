package br.com.spring.food.pagamentos.api.db;

import br.com.spring.food.pagamentos.api.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 19)
    private String numero;

    @NotBlank
    @Size(max = 7)
    private String expiracao;

    @NotBlank
    @Size(max = 3)
    private String codigo;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NonNull
    private Long pedidoId;

    @NonNull
    private Long meioPagamento;

}
