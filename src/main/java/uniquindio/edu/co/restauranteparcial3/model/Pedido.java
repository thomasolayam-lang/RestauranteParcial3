package uniquindio.edu.co.restauranteparcial3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private ModalidadEntrega modalidad;
    private EstadoPedido estado;
    private List<DetallePedido> detalles;
    private ServicioDomicilio servicioDomicilio;
    private double distanciaKm;
    private boolean domicilioAceptado;

    public Pedido(String codigo, LocalDate fecha, Cliente cliente, ModalidadEntrega modalidad) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.modalidad = modalidad;
        this.estado = EstadoPedido.PENDIENTE;
        this.detalles = new ArrayList<>();
        this.distanciaKm = 0.0;
        this.domicilioAceptado = false;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ModalidadEntrega getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadEntrega modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public ServicioDomicilio getServicioDomicilio() {
        return servicioDomicilio;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public boolean isDomicilioAceptado() {
        return domicilioAceptado;
    }

    // MÉTODOS

    // Agrega un producto a la lista de detalles del pedido
    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (producto != null && cantidad > 0) {
            DetallePedido detalle = new DetallePedido(producto, cantidad);
            return this.detalles.add(detalle);
        }
        return false;
    }

    // Asigna el servicio de domicilio y verifica si la empresa acepta el recorrido
    public boolean solicitarDomicilio(ServicioDomicilio servicio, double distanciaKm) {
        if (this.modalidad == ModalidadEntrega.DOMICILIO && servicio != null && distanciaKm > 0) {
            this.servicioDomicilio = servicio;
            this.distanciaKm = distanciaKm;
            this.domicilioAceptado = servicio.aceptarRecorrido(distanciaKm);
            return this.domicilioAceptado;
        }
        return false;
    }

    // Verifica que haya productos y que todos cumplan los controles de calidad
    public boolean cumpleCondiciones() {
        if (detalles.isEmpty()) {
            return false;
        }

        // Si la modalidad es a domicilio, la empresa debe haber aceptado el servicio
        if (modalidad == ModalidadEntrega.DOMICILIO) {
            if (servicioDomicilio == null || !domicilioAceptado) {
                return false;
            }
        }

        // Validar control de calidad de cada producto
        for (DetallePedido d : detalles) {
            if (!d.getProducto().cumpleCalidad()) {
                return false;
            }
        }
        return true;
    }

    // CAMBIA el estado de PENDIENTE a CONFIRMADO si cumple condiciones
    public boolean confirmar() {
        if (estado == EstadoPedido.PENDIENTE && cumpleCondiciones()) {
            this.estado = EstadoPedido.CONFIRMADO;
            return true;
        }
        return false;
    }

    // CAMBIA el estado de CONFIRMADO a ENTREGADO
    public boolean marcarEntregado() {
        if (estado == EstadoPedido.CONFIRMADO) {
            this.estado = EstadoPedido.ENTREGADO;
            return true;
        }
        return false;
    }

    // Cancela el pedido siempre que no haya sido entregado aún
    public boolean cancelar() {
        if (estado != EstadoPedido.ENTREGADO) {
            this.estado = EstadoPedido.CANCELADO;
            return true;
        }
        return false;
    }

    // Calcula la suma de todos los productos incluidos
    public double calcularTotalProductos() {
        double total = 0.0;
        for (DetallePedido d : detalles) {
            total += d.calcularSubtotal();
        }
        return total;
    }

    // Calcula el costo del flete/domicilio si aplica
    public double calcularCostoDomicilio() {
        if (modalidad == ModalidadEntrega.DOMICILIO && servicioDomicilio != null && domicilioAceptado) {
            return servicioDomicilio.calcularCosto(distanciaKm);
        }
        return 0.0;
    }

    // Suma del consumo de comida/bebida + domicilio
    public double calcularTotal() {
        return calcularTotalProductos() + calcularCostoDomicilio();
    }
}