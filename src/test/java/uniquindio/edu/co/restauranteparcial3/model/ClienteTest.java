package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    Cliente cliente = new Cliente("1094123", "Juan Pérez", "3001234567", "juan@gmail.com", "Calle 10");

    @Test
    void clienteTengaLosDatosCorrectos() {
        assertEquals("1094123", cliente.getDocumento());
        assertEquals("Juan Pérez", cliente.getNombreCompleto());
        assertEquals("juan@gmail.com", cliente.getCorreo());
        assertEquals("Calle 10", cliente.getDireccion());
    }

    @Test
    void guardarCorreoCorrectamente() {
        assertEquals("juan@gmail.com", cliente.getCorreo());
    }

    @Test
    void correoDebeTenerArroba() {
        assertTrue(cliente.getCorreo().contains("@"));
    }

    @Test
    void agregarPedidoExitosamente() {
        Pedido pedido = new Pedido("PED-01", null, cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        assertTrue(cliente.agregarPedido(pedido));
        assertEquals(1, cliente.consultarPedidos().size());
    }

    @Test
    void consultarPedidosVacioAlInicio() {
        assertNotNull(cliente.consultarPedidos());
        assertTrue(cliente.consultarPedidos().isEmpty());
    }
}