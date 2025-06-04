import java.util.Random;

public class Controlador {

    private static final int CANTIDAD_CONTRIBUYENTES = 3;
    private Vista vista = new Vista();

    public void ejecutar() {
        Metodos[] declaraciones = new Metodos[CANTIDAD_CONTRIBUYENTES];

        for (int i = 0; i < CANTIDAD_CONTRIBUYENTES; i++) {
            String nombre = "Contribuyente" + (i + 1);
            String cedula = generarCedulaAleatoria();
            String direccion = "Direccion #" + (100 + i);

            Metodos declaracion = new Metodos(nombre, cedula, direccion);

            for (int mes = 0; mes < 12; mes++) {
                double sueldo = 1000 + Math.random() * 2000;
                declaracion.agregarSueldo(mes, sueldo);
            }

            for (String categoria : Metodos.CATEGORIAS) {
                double montoFactura = Math.random() * 1500;
                declaracion.agregarFactura(montoFactura, categoria);
            }

            declaracion.calcularTotalIngresos();
            declaracion.calcularDeducciones();
            declaracion.calcularImpuestos();

            declaraciones[i] = declaracion;
            vista.mostrarDeclaracion(declaracion);
        }
    }

    private String generarCedulaAleatoria() {
        Random rand = new Random();
        StringBuilder cedula = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            cedula.append(rand.nextInt(10));
        }
        return cedula.toString();
    }
}
