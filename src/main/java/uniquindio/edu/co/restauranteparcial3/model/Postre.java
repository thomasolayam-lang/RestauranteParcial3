package uniquindio.edu.co.restauranteparcial3.model;

public class Postre extends Producto {
    private double pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion,
                  double pesoGramos, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }
    // MÉTODOS
    @Override
    public CategoriaProducto getCategoria() {
        return CategoriaProducto.POSTRE;
    }

    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * (1 + getCategoria().getPorcentajeIncremento());
    }

    @Override
    public boolean cumpleCalidad() {
        if (requiereRefrigeracion) {
            return temperaturaAlmacenamiento <= 4.0;
        }
        return true;
    }
}