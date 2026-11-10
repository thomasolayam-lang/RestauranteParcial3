package uniquindio.edu.co.restauranteparcial3;

import uniquindio.edu.co.restauranteparcial3.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {

    private static Restaurante restaurante;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        restaurante = new Restaurante("Sabor & Tradición", "900.123.456-7", "Calle 14 # 12-25");

        cargarDatosPrueba();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> menuGestionProductos();
                case 2 -> menuGestionClientes();
                case 3 -> menuGestionPedidos();
                case 4 -> menuConsultasYReportes();
                case 0 -> {
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema de Sabor & Tradición!");
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        }
        scanner.close();
    }


    private static void mostrarMenuPrincipal() {
        System.out.println("==================================================");
        System.out.println("                SABOR & TRADICIÓN");
        System.out.println("==================================================");
        System.out.println("1. Gestión de Productos (Menú)");
        System.out.println("2. Gestión de Clientes");
        System.out.println("3. Gestión de Pedidos y Domicilios");
        System.out.println("4. Consultas y Reportes Estadísticos");
        System.out.println("0. Salir");
        System.out.println("==================================================");
    }

    private static void menuGestionProductos() {
        System.out.println("--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Registrar nuevo producto");
        System.out.println("2. Ver catálogo completo");
        System.out.println("3. Ver productos que NO cumplen calidad");
        System.out.println("4. Modificar producto existente"); // Nueva opción
        System.out.println("0. Volver");

        int opcion = leerEntero("Seleccione: ");
        switch (opcion) {
            case 1 -> registrarProductoConsole();
            case 2 -> listarProductos();
            case 3 -> listarProductosNoAptos();
            case 4 -> modificarProductoConsole(); // Llamada al submenú
            case 0 -> {}
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void menuGestionClientes() {
        System.out.println("--- GESTIÓN DE CLIENTES ---");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Buscar cliente por documento");
        System.out.println("0. Volver");

        int opcion = leerEntero("Seleccione: ");
        switch (opcion) {
            case 1 -> registrarClienteConsole();
            case 2 -> buscarClienteConsole();
            case 0 -> {}
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void menuGestionPedidos() {
        System.out.println("--- GESTIÓN DE PEDIDOS Y DOMICILIOS ---");
        System.out.println("1. Crear y estructurar nuevo pedido");
        System.out.println("2. Confirmar pedido (Validar calidad y logística)");
        System.out.println("3. Marcar pedido como Entregado");
        System.out.println("4. Cancelar pedido");
        System.out.println("5. Consultar estado de un pedido");
        System.out.println("0. Volver");

        int opcion = leerEntero("Seleccione: ");
        switch (opcion) {
            case 1 -> crearPedidoConsole();
            case 2 -> confirmarPedidoConsole();
            case 3 -> cambiarEstadoPedidoConsole(1);
            case 4 -> cambiarEstadoPedidoConsole(2);
            case 5 -> consultarEstadoConsole();
            case 0 -> {}
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void menuConsultasYReportes() {
        System.out.println("--- REPORTES Y ESTADÍSTICAS ---");
        System.out.println("1. Total ingresos por venta de productos");
        System.out.println("2. Total ingresos por servicios de domicilio");
        System.out.println("3. Producto más vendido");
        System.out.println("4. Categoría con mayores ingresos");
        System.out.println("0. Volver");

        int opcion = leerEntero("Seleccione: ");
        switch (opcion) {
            case 1 -> System.out.printf("Ingresos netos por productos: $%.2f%n", restaurante.calcularIngresosRestaurante());
            case 2 -> System.out.printf("Ingresos por domicilios: $%.2f%n", restaurante.calcularIngresosDomicilios());
            case 3 -> {
                Producto masVendido = restaurante.calcularProductoMasVendido();
                if (masVendido != null) {
                    System.out.println("Producto estrella: " + masVendido.getNombre() + " (Código: " + masVendido.getCodigo() + ")");
                } else {
                    System.out.println("No hay registro de ventas en pedidos confirmados/entregados.");
                }
            }
            case 4 -> {
                CategoriaProducto cat = restaurante.calcularCategoriaMayoresIngresos();
                if (cat != null) {
                    System.out.println("Categoría más rentable: " + cat);
                } else {
                    System.out.println("No hay ventas registradas.");
                }
            }
            case 0 -> {}
            default -> System.out.println("Opción inválida.");
        }
    }


    // OPERACIONES DE LA CONSOLA


    private static void registrarProductoConsole() {
        System.out.println("\nTipo de producto a crear:");
        System.out.println("1. Plato Fuerte | 2. Bebida | 3. Postre");
        int tipo = leerEntero("Seleccione tipo: ");

        String codigo = leerTexto("Código único: ");
        String nombre = leerTexto("Nombre del producto: ");
        String desc = leerTexto("Descripción: ");
        double precioBase = leerDecimal("Precio base ($): ");
        int tiempoPrep = leerEntero("Tiempo de preparación (min): ");

        Producto producto = null;

        switch (tipo) {
            case 1 -> {
                double peso = leerDecimal("Peso en gramos: ");
                System.out.println("Tipo Preparación: 1. ASADO | 2. FRITO | 3. HORNEADO | 4. A_LA_PLANCHA");
                int prepIdx = leerEntero("Seleccione: ");
                TipoPreparacion prep = TipoPreparacion.values()[Math.max(0, Math.min(prepIdx - 1, 3))];
                boolean coccion = leerBooleano("¿Cocción aprobada? (s/n): ");
                producto = new PlatoFuerte(codigo, nombre, desc, precioBase, tiempoPrep, peso, prep, coccion);
            }
            case 2 -> {
                double volumen = leerDecimal("Volumen en ml: ");
                double temp = leerDecimal("Temperatura servido (°C): ");
                System.out.println("Tipo Bebida: 1. FRIA | 2. CALIENTE");
                int tBebida = leerEntero("Seleccione: ");
                TipoBebida tipoB = (tBebida == 2) ? TipoBebida.CALIENTE : TipoBebida.FRIA;
                boolean requierePrepEspecial = leerBooleano("¿Requiere preparación especial? (s/n): ");

                producto = new Bebida(codigo, nombre, desc, precioBase, tiempoPrep, volumen, temp, tipoB, requierePrepEspecial);
            }
            case 3 -> {
                double pesoP = leerDecimal("Peso en gramos: ");
                double tempA = leerDecimal("Temperatura almacenamiento (°C): ");
                boolean reqRef = leerBooleano("¿Requiere refrigeración? (s/n): ");
                producto = new Postre(codigo, nombre, desc, precioBase, tiempoPrep, pesoP, tempA, reqRef);
            }
            default -> System.out.println("Tipo no válido.");
        }

        if (producto != null) {
            boolean exito = restaurante.registrarProducto(producto);
            if (exito) {
                System.out.println(">> Producto registrado exitosamente en el catálogo.");
            } else {
                System.out.println(">> Error: Ya existe un producto con el código '" + codigo + "'.");
            }
        }
    }

    private static void listarProductos() {
        List<Producto> menu = restaurante.consultarMenu();
        if (menu.isEmpty()) {
            System.out.println("El menú se encuentra vacío.");
            return;
        }

        imprimirEncabezadoCatalogo();
        for (Producto p : menu) {
            imprimirFilaProducto(p);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    private static void listarProductosNoAptos() {
        List<Producto> noAptos = restaurante.consultarProductosNoAptos();
        if (noAptos.isEmpty()) {
            System.out.println("Todos los productos en el catálogo cumplen los estándares de calidad.");
            return;
        }

        System.out.println("\n=== PRODUCTOS QUE NO CUMPLEN CALIDAD ===");
        imprimirEncabezadoCatalogo();
        for (Producto p : noAptos) {
            imprimirFilaProducto(p);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
    private static void imprimirEncabezadoCatalogo() {
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-18s | %-12s | %-32s | %-11s | %-11s | %-6s |%n",
                "CÓDIGO", "NOMBRE", "CATEGORÍA", "DESCRIPCIÓN", "P. BASE", "P. VENTA", "APTO");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    private static void imprimirFilaProducto(Producto p) {
        String desc = p.getDescripcion();
        if (desc.length() > 30) {
            desc = desc.substring(0, 27) + "...";
        }
        System.out.printf("| %-7s | %-18s | %-12s | %-32s | $%-10.2f | $%-10.2f | %-6s |%n",
                p.getCodigo(),
                p.getNombre(),
                p.getCategoria(),
                desc,
                p.getPrecioBase(),
                p.calcularPrecioVenta(),
                p.cumpleCalidad() ? "SÍ" : "NO");
    }


    private static void modificarProductoConsole() {
        String codigo = leerTexto("Ingrese el código del producto a modificar: ");
        Producto prod = restaurante.buscarProducto(codigo);

        if (prod == null) {
            System.out.println(">> Error: No se encontró ningún producto con el código '" + codigo + "'.");
            return;
        }

        System.out.println("\n--- DATOS ACTUALES DEL PRODUCTO ---");
        imprimirEncabezadoCatalogo();
        imprimirFilaProducto(prod);
        System.out.println("------------------------------------------------------------------------------------------------------------------------");

        System.out.println("\nIngrese los nuevos datos (todos los campos son obligatorios y no se permiten vacíos):");

        String nuevoNombre = leerTexto("Nuevo nombre: ");
        String nuevaDesc = leerTexto("Nueva descripción: ");
        double nuevoPrecio = leerDecimal("Nuevo precio base ($): ");
        int nuevoTiempo = leerEntero("Nuevo tiempo de preparación (min): ");

        // Actualización de atributos generales
        boolean exitoBase = restaurante.actualizarProductoBase(codigo, nuevoNombre, nuevaDesc, nuevoPrecio, nuevoTiempo);

        // Actualización de atributos específicos según la subclase
        if (prod instanceof PlatoFuerte pf) {
            double nuevoPeso = leerDecimal("Nuevo peso (gramos): ");
            System.out.println("Tipo Preparación: 1. ASADO | 2. FRITO | 3. HORNEADO | 4. A_LA_PLANCHA");
            int prepIdx = leerEntero("Seleccione: ");
            TipoPreparacion nuevaPrep = TipoPreparacion.values()[Math.max(0, Math.min(prepIdx - 1, 3))];
            boolean nuevaCoccion = leerBooleano("¿Cocción aprobada? (s/n): ");

            pf.setPesoGramos(nuevoPeso);
            pf.setTipoPreparacion(nuevaPrep);
            pf.setCoccionAprobada(nuevaCoccion);

        } else if (prod instanceof Bebida b) {
            double nuevoVolumen = leerDecimal("Nuevo volumen (ml): ");
            System.out.print("Nueva temperatura de servicio (°C): ");
            double nuevaTemp = Double.parseDouble(scanner.nextLine().trim());
            System.out.println("Tipo Bebida: 1. FRIA | 2. CALIENTE");
            int tBebida = leerEntero("Seleccione: ");
            TipoBebida nuevoTipoB = (tBebida == 2) ? TipoBebida.CALIENTE : TipoBebida.FRIA;
            boolean nuevaPrepEspecial = leerBooleano("¿Requiere preparación especial? (s/n): ");

            b.setVolumenMl(nuevoVolumen);
            b.setTemperaturaServicio(nuevaTemp);
            b.setTipoBebida(nuevoTipoB);
            b.setRequierePreparacionEspecial(nuevaPrepEspecial);

        } else if (prod instanceof Postre p) {
            double nuevoPeso = leerDecimal("Nuevo peso (gramos): ");
            System.out.print("Nueva temperatura almacenamiento (°C): ");
            double nuevaTemp = Double.parseDouble(scanner.nextLine().trim());
            boolean nuevoReqRef = leerBooleano("¿Requiere refrigeración? (s/n): ");

            p.setPesoGramos(nuevoPeso);
            p.setTemperaturaAlmacenamiento(nuevaTemp);
            p.setRequiereRefrigeracion(nuevoReqRef);
        }

        if (exitoBase) {
            System.out.println(">> ¡Éxito! El producto '" + codigo + "' fue actualizado perfectamente.");
        }
    }

    private static void registrarClienteConsole() {
        String doc = leerTexto("Documento de identidad: ");
        String nombre = leerTexto("Nombre completo: ");
        String tel = leerTexto("Teléfono: ");
        String dir = leerTexto("Dirección: ");
        String corr= leerTexto("correo: ");

        Cliente cliente = new Cliente(doc,nombre,tel,corr,dir);
        if (restaurante.registrarCliente(cliente)) {
            System.out.println(">> Cliente registrado correctamente.");
        } else {
            System.out.println(">> Error: Ya existe un cliente registrado con la cédula " + doc);
        }
    }

    private static void buscarClienteConsole() {
        String doc = leerTexto("Documento a buscar: ");
        Cliente cliente = restaurante.buscarCliente(doc);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNombreCompleto() + " | Tel: " + cliente.getTelefono() + " | Dir: " + cliente.getDireccion());
        } else {
            System.out.println("Cliente no registrado.");
        }
    }

    private static void crearPedidoConsole() {
        String cod = leerTexto("Código para el nuevo pedido: ");
        String doc = leerTexto("Documento del cliente: ");
        Cliente cliente = restaurante.buscarCliente(doc);

        if (cliente == null) {
            System.out.println("Error: Cliente no existe. Debe registrarlo primero.");
            return;
        }

        System.out.println("Modalidad: 1. CONSUMO RESTAURANTE | 2. DOMICILIO");
        int modOp = leerEntero("Seleccione: ");
        ModalidadEntrega modalidad = (modOp == 2) ? ModalidadEntrega.DOMICILIO : ModalidadEntrega.CONSUMO_RESTAURANTE;

        Pedido pedido = new Pedido(cod, LocalDate.now(), cliente, modalidad);

        // Agregar detalles
        boolean agregando = true;
        while (agregando) {
            String codProd = leerTexto("Código del producto a añadir: ");
            Producto prod = restaurante.buscarProducto(codProd);

            if (prod != null) {
                int cant = leerEntero("Cantidad: ");
                pedido.agregarDetalle(prod, cant);
                System.out.println("-> Producto añadido.");
            } else {
                System.out.println("Producto no encontrado.");
            }
            agregando = leerBooleano("¿Desea agregar otro producto al pedido? (s/n): ");
        }

        // Si es a domicilio, asignar transportadora
        if (modalidad == ModalidadEntrega.DOMICILIO) {
            System.out.println("\nSeleccione empresa de domicilio:");
            System.out.println("1. Rápido Express (Hasta 12km) | 2. EntregaSegura (Hasta 20km)");
            int empOp = leerEntero("Opción: ");
            double dist = leerDecimal("Distancia del envío (km): ");

            ServicioDomicilio servicio = (empOp == 2) ? new EntregaSegura() : new RapidoExpress();
            boolean aceptado = pedido.solicitarDomicilio(servicio, dist);

            if (aceptado) {
                System.out.println(">> Domicilio aceptado por " + servicio.getNombreEmpresa() + ". Costo envio: $" + pedido.calcularCostoDomicilio());
            } else {
                System.out.println(">> ADVERTENCIA: La empresa rechazó el servicio por exceso de distancia o parámetro inválido.");
            }
        }

        if (restaurante.registrarPedido(pedido)) {
            System.out.println(">> Pedido " + cod + " creado en estado PENDIENTE. Subtotal: $" + pedido.calcularTotal());
        } else {
            System.out.println("Error: Código de pedido ya utilizado.");
        }
    }
    private static void confirmarPedidoConsole() {
        String cod = leerTexto("Código del pedido a confirmar: ");
        Pedido pedidoEncontrado = restaurante.buscarPedido(cod);

        if (pedidoEncontrado == null) {
            System.out.println(">> Error: No se encontró ningún pedido con el código '" + cod + "'.");
            return;
        }

        System.out.println("Procesando confirmación y validación de calidad de productos...");
        boolean resultado = restaurante.confirmarPedido(pedidoEncontrado);

        if (resultado) {
            System.out.println(">> ¡ÉXITO! El pedido cumple todas las normas de calidad y logística. Estado actual: CONFIRMADO");
        } else {
            System.out.println(">> RECHAZADO: El pedido no se pudo confirmar. Razones posibles:");
            System.out.println("   - No está en estado PENDIENTE.");
            System.out.println("   - No tiene productos asociados.");
            System.out.println("   - Contiene productos que NO superan el control de calidad.");
            System.out.println("   - El servicio a domicilio no fue aceptado por la transportadora.");
        }
    }

    private static void cambiarEstadoPedidoConsole(int accion) {
        String cod = leerTexto("Código del pedido: ");
        boolean res = (accion == 1) ? restaurante.marcarPedidoEntregado(cod) : restaurante.cancelarPedido(cod);

        if (res) {
            System.out.println(">> El pedido ha sido exitosamente entregado.");
        } else {
            System.out.println(">> No se pudo realizar el cambio de estado. Verifique el codigo del pedido");
        }
    }

    private static void consultarEstadoConsole() {
        String cod = leerTexto("Código del pedido: ");
        EstadoPedido estado = restaurante.consultarEstadoPedido(cod);
        if (estado != null) {
            System.out.println("El estado actual del pedido [" + cod + "] es: " + estado);
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    // Método de soporte para iterar sobre el historial sin romper encapsulamiento directo
    private static List<Pedido> getHistorialDirecto(String cod) {
        // Busca en los clientes registrados
        for (Cliente c : new Cliente[]{}) {}
        List<Pedido> temporal = new java.util.ArrayList<>();
        for (Producto p : restaurante.consultarMenu()) {}

        // Retornamos el pedido si coincide con el código
        for (Cliente cl : new java.util.ArrayList<Cliente>()) {
            for (Pedido p : cl.consultarPedidos()) {
                if (p.getCodigo().equalsIgnoreCase(cod)) temporal.add(p);
            }
        }
        return temporal;
    }

  //lectores

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println(">> Error: El campo no puede estar vacío ni contener solo espacios.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor > 0) {
                    return valor;
                }
                System.out.println(">> Error: El valor decimal debe ser mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Ingrese un número decimal válido (ejemplo: 4.5).");
            }
        }
    }

    private static boolean leerBooleano(String mensaje) {
        System.out.print(mensaje);
        String resp = scanner.nextLine().trim().toLowerCase();
        return resp.startsWith("s") || resp.startsWith("y") || resp.equals("1");
    }

    // Precarga de información inicial para pruebas rápidas de la aplicación
    private static void cargarDatosPrueba() {
        // Productos
        PlatoFuerte pf1 = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato tradicional", 25000, 25, 600, TipoPreparacion.ASADO, true);
        PlatoFuerte pf2 = new PlatoFuerte("PF02", "Pescado Frito", "Mojarra con patacón", 22000, 20, 500, TipoPreparacion.FRITO, false); // No cumple calidad
        Bebida b1 = new Bebida("B01", "Jugo de Lulo", "Jugo natural", 6000, 5, 4.0,5.7,TipoBebida.FRIA,false);
        Postre p1 = new Postre("P01", "Flan de Leche", "Postre casero", 8000, 0, 150, 3.0, true);

        restaurante.registrarProducto(pf1);
        restaurante.registrarProducto(pf2);
        restaurante.registrarProducto(b1);
        restaurante.registrarProducto(p1);

        // Empresa Domicilio
        restaurante.registrarServicioDomicilio(new RapidoExpress());
        restaurante.registrarServicioDomicilio(new EntregaSegura());

        // Cliente
        Cliente c1 = new Cliente("1094", "Carlos Gómez", "3101234567", "Carlos@gmail.com","Carrera 15 # 10-02");
        restaurante.registrarCliente(c1);
    }
}