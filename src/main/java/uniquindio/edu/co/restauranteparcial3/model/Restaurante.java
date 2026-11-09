package uniquindio.edu.co.restauranteparcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private List<Producto> menu;
    private List<Cliente> clientes;
    private List<Pedido> historialPedidos;
    private List<ServicioDomicilio> serviciosDomicilio;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.menu = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.historialPedidos = new ArrayList<>();
        this.serviciosDomicilio = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // 1. Métodos de Registro y Búsqueda
    public boolean registrarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) {
            return false;
        }
        return menu.add(producto);
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || buscarCliente(cliente.getDocumento()) != null) {
            return false;
        }
        return clientes.add(cliente);
    }

    public boolean registrarServicioDomicilio(ServicioDomicilio servicio) {
        if (servicio == null) {
            return false;
        }
        return serviciosDomicilio.add(servicio);
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : menu) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equalsIgnoreCase(documento)) {
                return c;
            }
        }
        return null;
    }

    // 2. Métodos de Consultas y Calidad
    public List<Producto> consultarMenu() {
        return menu;
    }

    public List<Producto> consultarProductosNoAptos() {
        List<Producto> noAptos = new ArrayList<>();
        for (Producto p : menu) {
            if (!p.cumpleCalidad()) {
                noAptos.add(p);
            }
        }
        return noAptos;
    }

    // 3. Métodos de Pedidos y Ciclo de Vida
    public boolean registrarPedido(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        for (Pedido p : historialPedidos) {
            if (p.getCodigo().equalsIgnoreCase(pedido.getCodigo())) {
                return false;
            }
        }
        historialPedidos.add(pedido);
        if (pedido.getCliente() != null) {
            pedido.getCliente().agregarPedido(pedido);
        }
        return true;
    }

    public boolean confirmarPedido(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        return pedido.confirmar();
    }

    public EstadoPedido consultarEstadoPedido(String codigoPedido) {
        for (Pedido p : historialPedidos) {
            if (p.getCodigo().equalsIgnoreCase(codigoPedido)) {
                return p.getEstado();
            }
        }
        return null;
    }

    public List<Pedido> consultarPedidosPorCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente != null) {
            return cliente.consultarPedidos();
        }
        return new ArrayList<>();
    }

    public boolean marcarPedidoEntregado(String codigoPedido) {
        for (Pedido p : historialPedidos) {
            if (p.getCodigo().equalsIgnoreCase(codigoPedido)) {
                if (p.getEstado() == EstadoPedido.CONFIRMADO) {
                    p.setEstado(EstadoPedido.ENTREGADO);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean cancelarPedido(String codigoPedido) {
        for (Pedido p : historialPedidos) {
            if (p.getCodigo().equalsIgnoreCase(codigoPedido)) {
                if (p.getEstado() != EstadoPedido.ENTREGADO) {
                    p.setEstado(EstadoPedido.CANCELADO);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    // 4. Métodos de Reportes y Estadística (Sin Map/HashMap)
    public double calcularIngresosRestaurante() {
        double ingresos = 0.0;
        for (Pedido p : historialPedidos) {
            if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                ingresos += p.calcularSubtotalProductos();
            }
        }
        return ingresos;
    }

    public double calcularIngresosDomicilios() {
        double ingresosDomicilio = 0.0;
        for (Pedido p : historialPedidos) {
            if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                ingresosDomicilio += p.calcularCostoDomicilio();
            }
        }
        return ingresosDomicilio;
    }

    public Producto calcularProductoMasVendido() {
        Producto masVendido = null;
        int maxCantidad = 0;

        for (Producto prod : menu) {
            int cantidadTotalProducto = 0;

            for (Pedido p : historialPedidos) {
                if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido d : p.getDetalles()) {
                        if (d.getProducto().getCodigo().equalsIgnoreCase(prod.getCodigo())) {
                            cantidadTotalProducto += d.getCantidad();
                        }
                    }
                }
            }

            if (cantidadTotalProducto > maxCantidad) {
                maxCantidad = cantidadTotalProducto;
                masVendido = prod;
            }
        }
        return masVendido;
    }

    public double calcularIngresosPorCategoria(CategoriaProducto categoria) {
        double ingresosCategoria = 0.0;
        if (categoria == null) {
            return ingresosCategoria;
        }

        for (Pedido p : historialPedidos) {
            if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido d : p.getDetalles()) {
                    if (d.getProducto().getCategoria() == categoria) {
                        ingresosCategoria += d.calcularSubtotal();
                    }
                }
            }
        }
        return ingresosCategoria;
    }

    public CategoriaProducto calcularCategoriaMayoresIngresos() {
        CategoriaProducto mayorCategoria = null;
        double maxIngreso = -1.0;

        for (CategoriaProducto cat : CategoriaProducto.values()) {
            double ingresoCat = calcularIngresosPorCategoria(cat);
            if (ingresoCat > maxIngreso) {
                maxIngreso = ingresoCat;
                mayorCategoria = cat;
            }
        }
        return mayorCategoria;
    }
}