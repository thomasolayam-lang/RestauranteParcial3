package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    Cliente cliente = new Cliente("1094", "Ana", "300", "ana@gmail.com", "Calle");
    PlatoFuerte plato = new PlatoFuerte("PF01", "Carne", "Asada", 10000.0, 15, 300.0, TipoPreparacion.ASADO, true);

    @Test
    void pedidoTengaDatosCorrectos() {
        Pedido pedido = new Pedido("PED-01", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        assertEquals("PED-01", pedido.getCodigo());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    void agregarDetalleExitoso() {
        Pedido pedido = new Pedido("PED-01", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        assertTrue(pedido.agregarDetalle(plato, 2));
        assertEquals(1, pedido.getDetalles().size());
    }

    @Test
    void calcularSubtotalProductos() {
        Pedido pedido = new Pedido("PED-01", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 2);
        assertEquals(24000.0, pedido.calcularSubtotalProductos());
    }

    @Test
    void confirmarPedidoExitosamente() {
        Pedido pedido = new Pedido("PED-01", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 1);
        assertTrue(pedido.confirmar());
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
    }

    @Test
    void noPermiteConfirmarSiProductoNoCumpleCalidad() {
        PlatoFuerte platoMalo = new PlatoFuerte("PF02", "Pescado", "Malo", 10000.0, 15, 300.0, TipoPreparacion.FRITO, false);
        Pedido pedido = new Pedido("PED-02", LocalDate.now(), cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(platoMalo, 1);
        assertFalse(pedido.cumpleCondiciones());
        assertFalse(pedido.confirmar());
    }
}