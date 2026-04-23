package br.com.fiap3esr.checkpoint2.Controller;


import br.com.fiap3esr.checkpoint2.model.Pedido;
import br.com.fiap3esr.checkpoint2.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Object> criarPedido(@Valid @RequestBody Pedido pedido) {
        Pedido pedidoCriado = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @GetMapping
    public ResponseEntity<Object> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPedidoPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.buscarPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedido
    ) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}