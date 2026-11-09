package uniquindio.edu.co.restauranteparcial3.model;

public enum CategoriaProducto {
    PLATO_FUERTE(0.20),
    BEBIDA(0.10),
    POSTRE(0.15);

    private final double porcentajeIncremento;

    CategoriaProducto(double porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public double getPorcentajeIncremento() {
        return porcentajeIncremento;
    }
}
