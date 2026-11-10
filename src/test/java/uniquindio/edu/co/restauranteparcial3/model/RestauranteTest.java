package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    Restaurante restaurante = new Restaurante("Sabor Tradicional", "900123-1", "Cra 14");
    Cliente cliente = new Cliente("1094", "Carlos", "300", "carlos@gmail.com", "Calle");
    PlatoFuerte plato = new PlatoFuerte("PF01", "Sancocho", "Típico", 15000.0, 20, 400.0, TipoPreparacion.ASADO, true);

    @Test
    void registrarClienteExitosamente() {
        assertTrue(restaurante.registrarCliente(cliente));
    }

    @Test
    void noPermiteClientesDuplicados() {
        restaurante.registrarCliente(cliente);
        Cliente clienteDuplicado = new Cliente("1094", "Carlos Copia", "301", "otro@gmail.com", "Otra");
        assertFalse(restaurante.registrarCliente(clienteDuplicado));
    }

    @Test
    void registrarProductoEnElMenu() {
        assertTrue(restaurante.registrarProducto(plato));
    }

    @Test
    void buscarClientePorDocumento() {
        restaurante.registrarCliente(cliente);
        assertNotNull(restaurante.buscarCliente("1094"));
        assertEquals("Carlos", restaurante.buscarCliente("1094").getNombreCompleto());
    }

    @Test
    void consultarProductosNoAptos() {
        PlatoFuerte platoMalo = new PlatoFuerte("PF02", "Pescado", "Malo", 15000.0, 20, 400.0, TipoPreparacion.FRITO, false);
        restaurante.registrarProducto(plato);
        restaurante.registrarProducto(platoMalo);
        assertEquals(1, restaurante.consultarProductosNoAptos().size());
        assertEquals("PF02", restaurante.consultarProductosNoAptos().getFirst().getCodigo());
    }
}