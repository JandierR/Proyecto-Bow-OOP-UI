package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Departamento;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.Usuario;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.DepartamentoManager;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.TicketManager;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.UsuarioManager;

import java.io.IOException;


public class Controller {
    private UI interfaz = new UI();
    private UsuarioManager usuarioManager = new UsuarioManager();
    private DepartamentoManager departamentoManager = new DepartamentoManager();
    private TicketManager ticketManager = new TicketManager();


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

        interfaz.imprimirMensajeLn("Usuario registrado exitosamente!");


        usuarioManager.registrarUsuario(nombre, correo, contrasena, telefono, rol);

    }

    public void imprimirUsuarios() {
        interfaz.imprimirMensajeLn(usuarioManager.getListaUsuario().toString());
    }

    public void registrarDepartamento() throws IOException {
        interfaz.imprimirMensaje("Digite el nombre del departamento: ");
        String nombreDepartamento = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la descripción del departamento: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el correo del departamento: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensajeLn("Departamento registrado exitosamente!");
        departamentoManager.registrarDepartamento(nombreDepartamento, descripcion, correo);
    }

    public void imprimirDepartamentos() {
        interfaz.imprimirMensajeLn(departamentoManager.getListaDepartamento().toString());
    }

    public void registrarTicket() throws IOException {
        //El id es unico por ticket
        interfaz.imprimirMensaje("Digite el ID del ticket: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el asunto: ");
        String asunto = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la descripción: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el estado: ");
        String estado = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el correo del usuario al que pertenece el ticket: ");
        String correoUsuario = interfaz.leerTexto();

//        Usuario usuarioTmp = new Usuario(correo);
        Usuario usuario = usuarioManager.buscarUsuarioPorCorreo(usuarioManager.getListaUsuario(), correoUsuario);

        interfaz.imprimirMensaje("Digite el nombre del departamento al que pertenece el ticket: ");
        String correoDepartamento = interfaz.leerTexto();

        Departamento departamento = departamentoManager.buscarDepartamentoPorNombre(departamentoManager.getListaDepartamento(), correoDepartamento);

        if (usuario == null) {
            if (departamento == null) {
                interfaz.imprimirMensajeLn("Lo sentimos. Este usuario no existe!");
                interfaz.imprimirMensajeLn("Lo sentimos. Este departamento no existe!");
                return;
            }
        }

        interfaz.imprimirMensajeLn("Ticket registrado exitosamente!");
        ticketManager.registrarTicket(id, asunto, descripcion, estado, usuario, departamento);
    }

    public void imprimirTickets() {
        if (ticketManager.getListaTickets().isEmpty()) {
            interfaz.imprimirMensajeLn("[Lista vacía]");
        } else {
            interfaz.imprimirMensajeLn(ticketManager.getListaTickets().toString());
        }
    }
}
