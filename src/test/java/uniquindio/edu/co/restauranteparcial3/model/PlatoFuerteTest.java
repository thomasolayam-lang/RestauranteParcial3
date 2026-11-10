package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlatoFuerteTest {

    PlatoFuerte plato = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato típico", 20000.0, 30, 500.0, TipoPreparacion.ASADO, true);

    @Test
    void platoFuerteTengaLosDatosCorrectos() {
        assertEquals("PF01", plato.getCodigo());
        assertEquals("Bandeja Paisa", plato.getNombre());
        assertEquals(20000.0, plato.getPrecioBase());
        assertEquals(500.0, plato.getPesoGramos());
    }

    @Test
    void calcularPrecioVentaConRecargoPorCategoria() {
        assertEquals(24000.0, plato.calcularPrecioVenta());
    }

    @Test
    void cumpleCalidadSiCoccionEstaAprobada() {
        assertTrue(plato.cumpleCalidad());
    }

    @Test
    void noCumpleCalidadSiCoccionNoEstaAprobada() {
        PlatoFuerte platoQuemado = new PlatoFuerte("PF02", "Carne", "Asada", 15000.0, 20, 300.0, TipoPreparacion.A_LA_PLANCHA, false);
        assertFalse(platoQuemado.cumpleCalidad());
    }

    @Test
    void verificarCategoriaCorrecta() {
        assertEquals(CategoriaProducto.PLATO_FUERTE, plato.getCategoria());
    }
}