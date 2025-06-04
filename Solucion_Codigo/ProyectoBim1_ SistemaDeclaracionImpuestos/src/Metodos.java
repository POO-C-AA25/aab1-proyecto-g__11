
public class Metodos {

    private String nombre;
    private String cedula;
    private String direccion;

    private double[] sueldosMensuales = new double[12];
    private double[] facturasPorCategoria = new double[6];

    private double totalIngresos;
    private double totalDeducciones;
    private double impuestosCalculados;

    public static final String[] CATEGORIAS = {
        "vivienda", "educacion", "alimentacion", "vestimenta", "salud", "turismo"
    };

    public Metodos(String nombre, String cedula, String direccion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public void agregarSueldo(int mes, double monto) {
        if (mes >= 0 && mes < 12 && monto > 0) {
            sueldosMensuales[mes] = monto;
        }
    }

    public void agregarFactura(double monto, String categoria) {
        int index = obtenerIndiceCategoria(categoria);
        if (index != -1 && monto > 0) {
            facturasPorCategoria[index] += monto;
        }
    }

    private int obtenerIndiceCategoria(String categoria) {
        for (int i = 0; i < CATEGORIAS.length; i++) {
            if (CATEGORIAS[i].equalsIgnoreCase(categoria)) {
                return i;
            }
        }
        return -1;
    }

    public void calcularTotalIngresos() {
        totalIngresos = 0;
        for (double sueldo : sueldosMensuales) {
            totalIngresos += sueldo;
        }
    }

    public void calcularDeducciones() {
        double sumaFacturas = 0;
        for (double monto : facturasPorCategoria) {
            sumaFacturas += monto;
        }
        double limiteDeduccion = Math.min(14754.00, totalIngresos * 0.5);
        totalDeducciones = Math.min(sumaFacturas, limiteDeduccion);
    }

    public void calcularImpuestos() {
        double baseImponible = totalIngresos - totalDeducciones;

        if (baseImponible <= 11722) {
            impuestosCalculados = 0;
        } else if (baseImponible <= 14930) {
            impuestosCalculados = (baseImponible - 11722) * 0.05;
        } else if (baseImponible <= 18660) {
            impuestosCalculados = (baseImponible - 14930) * 0.10 + 160;
        } else {
            impuestosCalculados = (baseImponible - 18660) * 0.12 + 516;
        }
    }

    public String generarDeclaracion() {
        return String.format("""
            ========= Declaracion =========
            Nombre: %s
            Cedula: %s
            Direccion: %s
            Ingresos Totales: $%.2f
            Deducciones Aplicadas: $%.2f
            Impuestos Calculados: $%.2f
            """, nombre, cedula, direccion, totalIngresos, totalDeducciones, impuestosCalculados);
    }
}