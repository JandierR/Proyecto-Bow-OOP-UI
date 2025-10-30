package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Departamento;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Ticket;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Usuario;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorDepartamento;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorTicket;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.GestorUsuario;

import java.io.IOException;


public class Controller {
    private UI interfaz = new UI();
    private GestorUsuario gestorUsuario = new GestorUsuario();
    private GestorDepartamento gestorDepartamento = new GestorDepartamento();
    private GestorTicket gestorTicket = new GestorTicket();


    public Controller() {
    }

    public void start() throws IOException {
        int opcion;

        do {
            interfaz.imprimirMenu();
            opcion = interfaz.leerOpcion();
            procesarOpcion(opcion);

        } while (opcion != 0);
    }

    public void procesarOpcion(int opcion) throws IOException {

        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> registrarDepartamento();
            case 3 -> registrarTicket();
            case 6 -> imprimirUsuarios();
            case 7 -> imprimirDepartamentos();
            case 8 -> imprimirTickets();
            case 0 -> System.exit(0);
            default -> interfaz.imprimirMensajeLn("Valor invalido");
        }
    }

    public void registrarUsuario() throws IOException {

        interfaz.imprimirMensaje("Digite el nombre del usuario: ");
        String nombre = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el correo del usuario: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la contraseña: ");
        String contrasena = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el numero de telefono: ");
        String telefono = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el rol del usuario: ");
        String rol = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el #ID del usuario: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensajeLn("Usuario registrado exitosamente!");

        boolean existeUsuario = gestorUsuario.existeUsuario(gestorUsuario.getListaUsuario(), id);

        //Preguntar al profesor lo siguiente:
        //¿Este proceso de buscar si existe un usuario con X correo, debe de ir en UsuarioManager o en Controller?
        //Pregunto porque no se me ocurre como acceder de esa manera en el UsuarioManager al registrar un usuario y que verifique ya existe
        //Retornar booleano, no objetos de negocio. Para averiguar si existe el usuario.
        if (existeUsuario) {
            interfaz.imprimirMensajeLn("Lo sentimos. Ya existe un usuario con este correo. Intenta de nuevo!");
        } else {
            gestorUsuario.registrarUsuario(nombre, correo, contrasena, telefono, rol, id);
        }

    }

    public void imprimirUsuarios() {
        //Este condicional verifica si la lista esta vacia
        if (gestorUsuario.getListaUsuario().isEmpty()) {
            interfaz.imprimirMensajeLn("[Lista vacía]");
        } else {
            //En este caso que no este vacia la lista, se imprime sus elementos.
            for (Usuario usuario : gestorUsuario.getListaUsuario()) {
                interfaz.imprimirMensajeLn(usuario.toString());
            }
        }
    }

    public void registrarDepartamento() throws IOException {
        interfaz.imprimirMensaje("Digite el nombre del departamento: ");
        String nombreDepartamento = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la descripción del departamento: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el correo del departamento: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el #ID del departamento: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensajeLn("Departamento registrado exitosamente!");

        //Este booleano verifica si ya existe un departamento con base a su ID, con el metodo existeDepartamento de la clase GestorDepartamento
        boolean existeDepartamento = gestorDepartamento.existeDepartamento(gestorDepartamento.getListaDepartamento(), id);

        //Aca se verifica si existe o no un departamento con tal ID.
        //Esta condicional es importante, ya que no se permiten departamentos duplicados.
        if (existeDepartamento) {
            interfaz.imprimirMensajeLn("Lo sentimos. Ya existe un departamento con este nombre. Intenta de nuevo!");
        } else {
            gestorDepartamento.registrarDepartamento(nombreDepartamento, descripcion, correo, id);
        }
    }

    public void imprimirDepartamentos() {
        //Este condicional verifica si la lista esta vacia
        if (gestorDepartamento.getListaDepartamento().isEmpty()) {
            interfaz.imprimirMensajeLn("[Lista vacía]");
        } else {
            //En este caso que no este vacia la lista, se imprime sus elementos.
            for (Departamento departamento : gestorDepartamento.getListaDepartamento()) {
                interfaz.imprimirMensajeLn(departamento.toString());
            }
        }
    }

    public void registrarTicket() throws IOException {
        //El id es unico por ticket
        interfaz.imprimirMensaje("Digite el ID del ticket: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el asunto: ");
        String asunto = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la descripción: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el estado (Nuevo / En Progreso / Resuelto): ");
        String estado = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el #ID del usuario: ");
        int idUsuario = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el #ID del departamento: ");
        int idDepartamento = Integer.parseInt(interfaz.leerTexto());

        //Estos siguientes dos booleanos --> existeUsuario y existeDepartamento, ambos utilizan sus metodos
        //existeUsuario y existeDepartamento, para verificar la existencia de ambos con base a su ID unicos
        boolean existeUsuario = gestorUsuario.existeUsuario(gestorUsuario.getListaUsuario(), idUsuario);
        boolean existeDepartamento = gestorDepartamento.existeDepartamento(gestorDepartamento.getListaDepartamento(), idDepartamento);

        //Si ambos usuario y departamento existen, entonces se registra exitosamente el ticket.
        if (existeUsuario && existeDepartamento) {
            interfaz.imprimirMensajeLn("Ticket registrado exitosamente!");
            gestorTicket.registrarTicket(id, asunto, descripcion, estado, idUsuario, idDepartamento);

        } else {
            interfaz.imprimirMensajeLn("Lo sentimos. El usuario o departamento no existe!");
        }

    }

    public void imprimirTickets() {
        //Este condicional verifica si la lista esta vacia
        if (gestorTicket.getListaTickets().isEmpty()) {
            interfaz.imprimirMensajeLn("[Lista vacía]");
        } else {
            //En este caso que no este vacia la lista, se imprime sus elementos.
            for (Ticket ticket : gestorTicket.getListaTickets()) {
                interfaz.imprimirMensajeLn(ticket.toString());
            }
        }
    }
}
