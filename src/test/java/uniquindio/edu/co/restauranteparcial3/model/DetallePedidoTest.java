package uniquindio.edu.co.restauranteparcial3.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DetallePedidoTest {

    PlatoFuerte plato = new PlatoFuerte("PF01", "Carne", "Asada", 10000.0, 15, 300.0, TipoPreparacion.ASADO, true);
    DetallePedido detalle = new DetallePedido(plato, 3);

    @Test
    void detallePedidoTengaDatosCorrectos() {
        assertEquals(plato, detalle.getProducto());
        assertEquals(3, detalle.getCantidad());
    }

    @Test
    void calcularPrecioUnitarioConIncrementoDeCategoria() {
        assertEquals(12000.0, detalle.getPrecioUnitario());
    }

    @Test
    void calcularSubtotalCorrectamente() {
        assertEquals(36000.0, detalle.calcularSubtotal());
    }

    @Test
    void modificarCantidadDetalle() {
        detalle.setCantidad(5);
        assertEquals(5, detalle.getCantidad());
        assertEquals(60000.0, detalle.calcularSubtotal());
    }

    @Test
    void detalleConProductoNuloManejaCero() {
        DetallePedido detalleNulo = new DetallePedido(null, 2);
        assertEquals(0.0, detalleNulo.getPrecioUnitario());
        assertEquals(0.0, detalleNulo.calcularSubtotal());
    }
}