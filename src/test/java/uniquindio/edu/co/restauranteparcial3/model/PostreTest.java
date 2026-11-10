package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostreTest {

    Postre postre = new Postre("POS01", "Tres Leches", "Casero", 8000.0, 10, 200.0, 3.0, true);

    @Test
    void postreTengaLosDatosCorrectos() {
        assertEquals("POS01", postre.getCodigo());
        assertEquals("Tres Leches", postre.getNombre());
        assertEquals(8000.0, postre.getPrecioBase());
    }

    @Test
    void calcularPrecioVentaPostre() {
        assertEquals(9200.0, postre.calcularPrecioVenta());
    }

    @Test
    void cumpleCalidadPostreRefrigeradoValido() {
        assertTrue(postre.cumpleCalidad());
    }

    @Test
    void noCumpleCalidadPostreRefrigeradoTemperaturaAlta() {
        Postre postreMalo = new Postre("POS02", "Flan", "Malo", 7000.0, 5, 150.0, 10.0, true);
        assertFalse(postreMalo.cumpleCalidad());
    }

    @Test
    void postreSinRefrigeracionSiempreCumpleCalidad() {
        Postre galleta = new Postre("POS03", "Galleta", "Seca", 3000.0, 2, 50.0, 25.0, false);
        assertTrue(galleta.cumpleCalidad());
    }
}