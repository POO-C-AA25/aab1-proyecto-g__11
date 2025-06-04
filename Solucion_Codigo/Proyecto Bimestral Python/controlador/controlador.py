import random
from modelo.metodos import Metodos
from vista.vista import Vista

class Controlador:
    def ejecutar(self):
        vista = Vista()
        contribuyentes = []

        for i in range(3):
            nombre = f"Contribuyente{i + 1}"
            cedula = ''.join(str(random.randint(0, 9)) for _ in range(10))
            direccion = f"Dirección #{100 + i}"
            persona = Metodos(nombre, cedula, direccion)

            for mes in range(12):
                persona.agregar_sueldo(mes, random.uniform(1000, 3000))

            for categoria in Metodos.CATEGORIAS:
                persona.agregar_factura(random.uniform(0, 1500), categoria)

            persona.calcular_total_ingresos()
            persona.calcular_deducciones()
            persona.calcular_impuestos()

            contribuyentes.append(persona)
            vista.mostrar_declaracion(persona)