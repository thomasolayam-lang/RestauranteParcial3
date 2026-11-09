public class EntregaSegura implements ServicioDomicilio {
    public static final double TARIFA_BASE = 7000.0;
    public static final double TARIFA_KM = 1000.0;
    public static final double RECARGO = 3000.0;
    public static final double UMBRAL_RECARGO = 5.0;
    public static final double DISTANCIA_MAXIMA = 20.0;

    @Override
    public String getNombreEmpresa() {
        return "EntregaSegura";
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
        double costo = TARIFA_BASE + (distanciaKm * TARIFA_KM);
        if (distanciaKm > UMBRAL_RECARGO) {
            costo += RECARGO;
        }
        return costo;
    }
}