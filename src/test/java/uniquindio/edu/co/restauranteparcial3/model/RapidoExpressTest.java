package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RapidoExpressTest {

    RapidoExpress express = new RapidoExpress();

    @Test
    void aceptarRecorridoValido() {
        assertTrue(express.aceptarRecorrido(5.0));
    }

    @Test
    void aceptarRecorridoExcedido() {
        assertFalse(express.aceptarRecorrido(15.0));
    }

    @Test
    void calcularCostoRecorridoValido() {
        assertEquals(8000.0, express.calcularCosto(2.0));
    }

    @Test
    void calcularCostoRecorridoInvalidoRetornaCero() {
        assertEquals(0.0, express.calcularCosto(15.0));
    }

    @Test
    void obtenerNombreEmpresaCorrecto() {
        assertEquals("Rápido Express", express.getNombreEmpresa());
    }
}