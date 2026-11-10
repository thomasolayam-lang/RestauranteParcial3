package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BebidaTest {

    Bebida bebidaFria = new Bebida("B01", "Jugo", "Guanabana", 5000.0, 5, 400.0, 5.0, TipoBebida.FRIA, false);
    Bebida bebidaCaliente = new Bebida("B02", "Café", "Negro", 3000.0, 3, 150.0, 65.0, TipoBebida.CALIENTE, false);

    @Test
    void bebidaTengaLosDatosCorrectos() {
        assertEquals("B01", bebidaFria.getCodigo());
        assertEquals("Jugo", bebidaFria.getNombre());
        assertEquals(400.0, bebidaFria.getVolumenMl());
    }

    @Test
    void calcularPrecioVentaBebida() {
        assertEquals(5500.0, bebidaFria.calcularPrecioVenta());
    }

    @Test
    void cumpleCalidadBebidaFriaValida() {
        assertTrue(bebidaFria.cumpleCalidad());
    }

    @Test
    void noCumpleCalidadBebidaFriaTemperaturaAlta() {
        Bebida bebidaTiboa = new Bebida("B03", "Jugo", "Tibio", 5000.0, 5, 400.0, 15.0, TipoBebida.FRIA, false);
        assertFalse(bebidaTiboa.cumpleCalidad());
    }

    @Test
    void cumpleCalidadBebidaCalienteValida() {
        assertTrue(bebidaCaliente.cumpleCalidad());
    }
}
