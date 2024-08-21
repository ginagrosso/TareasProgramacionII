/**Imagina que estás creando un juego de supermercado en tu computadora. Necesitamos que escribas un programa en Java para que
 * todo funcione como en la vida real.

 ¿Cómo lo haremos?

 Humane: Tenemos una clase base para representar a cualquier persona (empleados y clientes).
 Empleados: Son humanes que trabajan en las cajas y tienen un sueldo.
 Cajas: Son los lugares donde se pagan las compras y están atendidas por un empleado o empleada.
 Clientes: También son humanes, pero además sabemos si son mayoristas o no (para darles descuentos).

 Tu misión:

 Crea los productos: Cada producto tiene un nombre, precio y cantidad.
 Registra las compras: Cuando un cliente compra, se crea una transacción. Esta transacción guarda qué productos compró, cuánto pagó y en qué caja lo hizo.
 Simula una compra: Haz que un cliente vaya a una caja, compre varios productos y pague. ¡Muestra en pantalla
 todo lo que compró y cuánto pagó!*/

import java.util.ArrayList;
import java.util.Scanner;

class Humane {
    private String nombre;
    private String apellido;
    private int dni;

    public Humane(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI: " + dni + ")";
    }
}

class Empleado extends Humane {
    private double sueldo;

    public Empleado(String nombre, String apellido, int dni, double sueldo) {
        super(nombre, apellido, dni);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sueldo: $" + sueldo;
    }
}

class Caja {
    private int numeroDeCaja;
    private Empleado empleado;

    public Caja(int numeroDeCaja, Empleado empleado) {
        this.numeroDeCaja = numeroDeCaja;
        this.empleado = empleado;
    }

    public int getNumeroDeCaja() {
        return numeroDeCaja;
    }

    public void setNumeroDeCaja(int numeroDeCaja) {
        this.numeroDeCaja = numeroDeCaja;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Caja Nº " + numeroDeCaja + " atendida por " + empleado;
    }
}

class Cliente extends Humane {
    private boolean mayorista;

    public Cliente(String nombre, String apellido, int dni, boolean mayorista) {
        super(nombre, apellido, dni);
        this.mayorista = mayorista;
    }

    public boolean isMayorista() {
        return mayorista;
    }

    public void setMayorista(boolean mayorista) {
        this.mayorista = mayorista;
    }

    @Override
    public String toString() {
        return super.toString() + (mayorista ? " (Mayorista)" : "");
    }
}

class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return nombre + " x" + cantidad + " - $" + (precio * cantidad);
    }
}

class Transaccion {
    private Cliente cliente;
    private Caja caja;
    private ArrayList<Producto> productos;
    private double total;

    public Transaccion(Cliente cliente, Caja caja, ArrayList<Producto> productos) {
        this.cliente = cliente;
        this.caja = caja;
        this.productos = new ArrayList<>(productos);
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        if (cliente.isMayorista()) {
            total *= 0.85;
        }
        return total;
    }

    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        String detalles = "------TICKET------"; detalles += "\n";
        detalles += "Cliente: " + cliente + "\n";
        detalles += "Caja: " + caja + "\n";
        detalles += "Productos:\n";
        for (Producto p : productos) {
            detalles += "- " + p + "\n";
        }
        detalles += "Total a pagar: $" + total;
        return detalles;
    }

}

public class Super {
    public static void main(String[] args) {
        Empleado e1 = new Empleado("Juanita", "Ford", 44320210, 250000);
        Caja c1 = new Caja(1, e1);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el dni del cliente:");
        int dni = scanner.nextInt();
        System.out.println("Ingrese si el cliente es mayorista (true/false):");
        boolean mayorista = scanner.nextBoolean();
        Cliente c2 = new Cliente(nombre, apellido, dni, mayorista);

        ArrayList<Producto> productos = new ArrayList<>();
        char opcion;
        do {
            System.out.println("Ingrese el nombre del producto:");
            scanner = new Scanner(System.in);
            nombre = scanner.nextLine();
            System.out.println("Ingrese el precio del producto:");
            double precio = scanner.nextDouble();
            System.out.println("Ingrese la cantidad del producto:");
            int cantidad = scanner.nextInt();
            productos.add(new Producto(nombre, precio, cantidad));
            System.out.println("¿Quieres agregar más productos? (S/N)");
            opcion = scanner.next().charAt(0);
            scanner.nextLine();
        } while (opcion == 'S' || opcion == 's');

        Transaccion t1 = new Transaccion(c2, c1, productos);
        System.out.println(t1);
    }
}




