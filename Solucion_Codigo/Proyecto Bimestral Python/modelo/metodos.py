class Metodos:
    CATEGORIAS = ["vivienda", "educacion", "alimentacion", "vestimenta", "salud", "turismo"]

    def __init__(self, nombre, cedula, direccion):
        self.nombre = nombre
        self.cedula = cedula
        self.direccion = direccion
        self.sueldos_mensuales = [0.0] * 12
        self.facturas_por_categoria = [0.0] * len(self.CATEGORIAS)
        self.total_ingresos = 0.0
        self.total_deducciones = 0.0
        self.impuestos_calculados = 0.0

    def agregar_sueldo(self, mes, monto):
        if 0 <= mes < 12 and monto > 0:
            self.sueldos_mensuales[mes] = monto

    def agregar_factura(self, monto, categoria):
        try:
            index = self.CATEGORIAS.index(categoria.lower())
            self.facturas_por_categoria[index] += monto
        except ValueError:
            pass

    def calcular_total_ingresos(self):
        self.total_ingresos = sum(self.sueldos_mensuales)

    def calcular_deducciones(self):
        total_facturas = sum(self.facturas_por_categoria)
        limite = min(14754.00, self.total_ingresos * 0.5)
        self.total_deducciones = min(total_facturas, limite)

    def calcular_impuestos(self):
        base = self.total_ingresos - self.total_deducciones
        if base <= 11722:
            self.impuestos_calculados = 0
        elif base <= 14930:
            self.impuestos_calculados = (base - 11722) * 0.05
        elif base <= 18660:
            self.impuestos_calculados = (base - 14930) * 0.10 + 160
        else:
            self.impuestos_calculados = (base - 18660) * 0.12 + 516

    def generar_declaracion(self):
        return (f"\n========= Declaración =========\n"
                f"Nombre: {self.nombre}\n"
                f"Cédula: {self.cedula}\n"
                f"Dirección: {self.direccion}\n"
                f"Ingresos Totales: ${self.total_ingresos:.2f}\n"
                f"Deducciones Aplicadas: ${self.total_deducciones:.2f}\n"
                f"Impuestos Calculados: ${self.impuestos_calculados:.2f}\n")