package br.com.fiap3esr.checkpoint2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String clienteNome;

    private LocalDate dataPedido;

    @DecimalMin(value = "0.0", inclusive = true, message = "O valor total não pode ser negativo.")
    private double valorTotal;

    @PrePersist
    public void prePersist() {
        if (dataPedido == null) {
            dataPedido = LocalDate.now();
        }
    }
}