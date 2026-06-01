package estructura31;
import java.util.*;

public class Estructura31 {
    
    static class Contenedor{
        String codigo;
        String descripcion;
        String area;
        double peso;
        String estado;
    }
    
    static Stack<Contenedor> deposito = new Stack<>();
    static LinkedList<Contenedor> retirados= new LinkedList<>();
    
    public static void main(String[] args) {
        Scanner sc =new Scanner (System.in);
        int op;
        
        do {
            System.out.println("\n=== Contenedores de Utileria ===");
            System.out.println("1. Ingresar contenedor");
            System.out.println("2. Consultar contenedor disponible");
            System.out.println("3. Retirar Contenedor");
            System.out.println("4. Ver contenedores almacenados");
            System.out.println("5. Ver contenedores ya retirados");
            System.out.println("6. Cuantos quedan en el deposito");
            System.out.println("0. Salir");
            System.out.print("\nOpcion: ");
            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida, ingrese un numero");
                sc.nextLine();
                op = -1;
            }
            
            switch (op){
                case 1: ingresarContenedor(sc); break;
                case 2: consultarSiguiente(); break;
                case 3: retirarContenedor(); break;
                case 4: mostrarAlmacenados(); break;
                case 5: mostrarRetirados(); break;
                case 6: System.out.println("Contenedores en deposito: " + deposito.size()); break;
                case 0: System.out.println("Fin del programa"); break;
                default: 
                    if (op != -1 && op != 0){
                        System.out.println("Opcion invalida");
                    } break;
            }
        } while (op !=0);
    }
    
    static void ingresarContenedor(Scanner sc){
        Contenedor c = new Contenedor();
        System.out.print("\nCodigo: ");
        c.codigo = sc.nextLine();
        System.out.print("Descripcion: ");
        c.descripcion = sc.nextLine();
        System.out.print("Area ('sonido', 'decoracion', 'iluminacion' o 'vestuario'): ");
        c.area = sc.nextLine().toLowerCase();
        while (!c.area.equals("sonido") && !c.area.equals("decoracion") && !c.area.equals("iluminacion") && !c.area.equals("vestuario")) {
            System.out.println("Opcion invalida. Solo: sonido, decoracion, iluminacion, vestuario");
            System.out.print("Area: ");
            c.area = sc.nextLine().toLowerCase();
        }
        System.out.print("Peso aproximado (kg): ");
        c.peso = sc.nextDouble();
        sc.nextLine();
        c.estado = "almacenado";
        deposito.push(c);
        System.out.println("\nContenedor " + c.codigo + " ingresado al deposito");        
    }
    
    static void consultarSiguiente(){
        if (deposito.isEmpty()){
            System.out.println("El deposito esta vacio");
            return;
        }
        Contenedor c = deposito.peek();
        System.out.println("-------------------");
        System.out.println("Contenedor disponible para retiro:\n");
        mostrarDatos(c);
    }
    
    static void retirarContenedor(){
         if (deposito.isEmpty()) {
            System.out.println("El deposito esta vacio, no hay nada que retirar");
            return;
        }
        Contenedor c = deposito.pop();
        c.estado = "retirado";
        retirados.add(c);
        System.out.println("Contenedor " + c.codigo + " retirado exitosamente.");
    }
    
    static void mostrarAlmacenados(){
        if (deposito.isEmpty()){
            System.out.println("No hay contenedores almacenados");
            return;
        }
        System.out.println("-------------------");
        System.out.println("=== Conetenedores Almacenados ===\n");
        Stack<Contenedor> temp = new Stack<>();
        while (!deposito.isEmpty()) {
            temp.push(deposito.pop());
        }
        while (!temp.isEmpty()) {
            Contenedor c = temp.pop();
            mostrarDatos(c);
            deposito.push(c);         
        }
    }
    
    static void mostrarRetirados() {
        if (retirados.isEmpty()) {
            System.out.println("No se ha retirado ningun contenedor");
            return;
        }
        System.out.println("=== Contenedores Retirados ===");
        for (int i = 0; i < retirados.size(); i++) {
            mostrarDatos(retirados.get(i));
        }
    }
    
    static void mostrarDatos(Contenedor c) {
        System.out.println("Codigo: " + c.codigo);
        System.out.println("Descripcion: " + c.descripcion);
        System.out.println("Area: " + c.area);
        System.out.println("Peso: " + c.peso + " kg");
        System.out.println("Estado: " + c.estado);
        System.out.println("-------------------");
        
    }
}