package uniquindio.edu.co.restauranteparcial3.model;

    public class Bebida extends Producto {
        private double volumenMl;
        private double temperaturaServicio;
        private TipoBebida tipoBebida;
        private boolean requierePreparacionEspecial;

        public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, double volumenMl,
                      double temperaturaServicio, TipoBebida tipoBebida, boolean requierePreparacionEspecial) {
            super(codigo, nombre, descripcion, precioBase, tiempoPreparacion);
            this.volumenMl = volumenMl;
            this.temperaturaServicio = temperaturaServicio;
            this.tipoBebida = tipoBebida;
            this.requierePreparacionEspecial = requierePreparacionEspecial;
        }

        public double getVolumenMl() {
            return volumenMl;
        }

        public void setVolumenMl(double volumenMl) {
            this.volumenMl = volumenMl;
        }

        public double getTemperaturaServicio() {
            return temperaturaServicio;
        }

        public void setTemperaturaServicio(double temperaturaServicio) {
            this.temperaturaServicio = temperaturaServicio;
        }

        public TipoBebida getTipoBebida() {
            return tipoBebida;
        }

        public void setTipoBebida(TipoBebida tipoBebida) {
            this.tipoBebida = tipoBebida;
        }

        public boolean isRequierePreparacionEspecial() {
            return requierePreparacionEspecial;
        }

        public void setRequierePreparacionEspecial(boolean requierePreparacionEspecial) {
            this.requierePreparacionEspecial = requierePreparacionEspecial;
        }

    }
