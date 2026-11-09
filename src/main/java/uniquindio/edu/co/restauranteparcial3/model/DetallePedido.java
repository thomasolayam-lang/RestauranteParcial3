package uniquindio.edu.co.restauranteparcial3.model;

public class DetallePedido {

        private Producto producto;
        private int cantidad;
        private double precioUnitario;

        public DetallePedido(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
            if (producto != null) {
                this.precioUnitario = producto.calcularPrecioVenta();
            } else {
                this.precioUnitario = 0.0;
            }
        }

        // Getters y Setters
        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
            if (producto != null) {
                this.precioUnitario = producto.calcularPrecioVenta();
            }
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        // MÉTODOS
        public double calcularSubtotal() {
            return cantidad * precioUnitario;
        }

        @Override
        public String toString() {
            return "DetallePedido{" +
                    "Producto: " + (producto != null ? producto.getNombre() : "N/A") +
                    ", Cantidad: " + cantidad +
                    ", Precio Unitario: $" + precioUnitario +
                    ", Subtotal: $" + calcularSubtotal() +
                    '}';
        }
    }

