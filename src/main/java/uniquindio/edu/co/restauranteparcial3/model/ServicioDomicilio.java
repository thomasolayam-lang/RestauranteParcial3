package uniquindio.edu.co.restauranteparcial3.model;

public interface ServicioDomicilio {
    String getNombreEmpresa();
    boolean aceptarRecorrido(double distanciaKm);
    double calcularCosto(double distanciaKm);
}
