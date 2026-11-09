package uniquindio.edu.co.restauranteparcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {


    private String nombreComercial;
    private String nit;
    private String direccion;

    //listas
    private List<Producto> menu;
    private List<Cliente> listaClientes;
    private List<Pedido> historialPedidos;
    private List<ServicioDomicilio> serviciosDomicilio;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;

        this.menu = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.historialPedidos = new ArrayList<>();
        this.serviciosDomicilio = new ArrayList<>();
    }


    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Producto> getMenu() {
        return menu;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }

    public List<ServicioDomicilio> getServiciosDomicilio() {
        return serviciosDomicilio;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;

    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "Nombre Comercial:'" + nombreComercial + '\'' +
                ", nit:'" + nit + '\'' +
                ", direccion:'" + direccion + '\'' +
                ", menu:" + menu +
                ", lista de Clientes:" + listaClientes +
                ", historial de Pedidos:" + historialPedidos +
                ", servicios a Domicilio:" + serviciosDomicilio;
    }

    //metodos


    public static boolean registrarProducto(producto: Producto){
        return true;
    }
}
