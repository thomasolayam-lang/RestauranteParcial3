package uniquindio.edu.co.restauranteparcial3.model;

public class RapidoExpress implements ServicioDomicilio {
    public static final double TARIFA_BASE = 5000.0;
    public static final double TARIFA_KM = 1500.0;
    public static final double DISTANCIA_MAXIMA = 12.0;

    @Override
    public String getNombreEmpresa() {
        return "Rápido Express";
    }

    @Override
    public boolean aceptarRecorrido(double distanciaKm) {
        return distanciaKm > 0 && distanciaKm <= DISTANCIA_MAXIMA;
    }

    @Override
    public double calcularCosto(double distanciaKm) {
        if (!aceptarRecorrido(distanciaKm)) {
            return 0.0;
        }
        return TARIFA_BASE + (distanciaKm * TARIFA_KM);
    }
}