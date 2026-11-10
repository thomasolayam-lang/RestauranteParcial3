package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntregaSeguraTest {

    EntregaSegura segura = new EntregaSegura();

    @Test
    void aceptarRecorridoValido() {
        assertTrue(segura.aceptarRecorrido(10.0));
    }

    @Test
    void aceptarRecorridoExcedido() {
        assertFalse(segura.aceptarRecorrido(25.0));
    }

    @Test
    void calcularCostoSinRecargo() {
        assertEquals(10000.0, segura.calcularCosto(3.0));
    }

    @Test
    void calcularCostoConRecargoPorUmbral() {
        assertEquals(16000.0, segura.calcularCosto(6.0));
    }

    @Test
    void obtenerNombreEmpresaCorrecto() {
        assertEquals("EntregaSegura", segura.getNombreEmpresa());
    }
}