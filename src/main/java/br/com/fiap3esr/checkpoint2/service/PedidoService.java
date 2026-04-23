package br.com.fiap3esr.checkpoint2.service;

import br.com.fiap3esr.checkpoint2.model.Pedido;
import br.com.fiap3esr.checkpoint2.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedidoExistente = buscarPedidoPorId(id);

        pedidoExistente.setClienteNome(pedidoAtualizado.getClienteNome());
        pedidoExistente.setValorTotal(pedidoAtualizado.getValorTotal());

        return pedidoRepository.save(pedidoExistente);
    }

    public void deletarPedido(Long id) {
        Pedido pedido = buscarPedidoPorId(id);
        pedidoRepository.delete(pedido);
    }
}