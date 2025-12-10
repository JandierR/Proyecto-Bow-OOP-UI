package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.*;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.*;
import cr.ac.ucenfotec.rojas.jandier.dl.Data;

import java.io.IOException;


public class Controller {
    private Data data;
    private GestorUsuario gestorUsuario;
    private GestorDepartamento gestorDepartamento;
    private GestorTicket gestorTicket;
    private GestorDiccionarioEmocional gestorDiccionarioEmocional;
    private GestorDiccionarioTecnico gestorDiccionarioTecnico;
    private Login login;
    private UI interfaz;
    private AnalisisBoW analisisBoW;


    public Controller() {
        data = new Data();
        gestorUsuario = new GestorUsuario(data);
        gestorDepartamento = new GestorDepartamento(data);
        gestorTicket = new GestorTicket(data);
        gestorDiccionarioEmocional = new GestorDiccionarioEmocional(data);
        gestorDiccionarioTecnico = new GestorDiccionarioTecnico(data);
        login = new Login(data);
        interfaz = new UI();
        analisisBoW = new AnalisisBoW(data);
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
            case 4 -> registrarPalabraEmocional();
            case 5 -> registrarPalabraTecnica();
            case 6 -> imprimirUsuarios();
            case 7 -> imprimirDepartamentos();
            case 8 -> imprimirTickets();
            case 9 -> iniciarSesion();
            case 0 -> interfaz.imprimirMensajeLn("Saliendo del sistema");
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


        interfaz.imprimirMensajeLn(gestorUsuario.registrarUsuario(nombre, correo, contrasena, telefono, rol, id));


    }

    public void imprimirUsuarios() {
        for (String usuario : gestorUsuario.obtenerUsuarios()) {

            interfaz.imprimirMensajeLn(usuario);
        }
    }

    public void registrarPalabraEmocional() throws IOException {
        System.out.println("Digite la palabra: ");
        String palabraEmocional = interfaz.leerTexto();

        System.out.println("Digite el tipo de emoción: ");
        String emocion = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDiccionarioEmocional.registrarPalabraEmocional(palabraEmocional, emocion));

    }

    public void registrarPalabraTecnica() throws IOException {
        System.out.println("Digite la palabra: ");
        String palabraEmocional = interfaz.leerTexto();

        System.out.println("Digite la categoria: ");
        String categoria = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDiccionarioTecnico.registrarPalabraTecnica(palabraEmocional, categoria));

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


        interfaz.imprimirMensajeLn(gestorDepartamento.registrarDepartamento(nombreDepartamento, descripcion, correo, id));
    }

    public void imprimirDepartamentos() {
        for (String departamento : gestorDepartamento.obtenerDepartamentos()) {
            interfaz.imprimirMensajeLn(departamento);
        }
    }

    public void registrarTicket() throws IOException {
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


        interfaz.imprimirMensajeLn(gestorTicket.registrarTicket(id, asunto, descripcion, estado, idUsuario, idDepartamento));

        interfaz.imprimirMensajeLn("Categorías sugeridas para el ticket");
        interfaz.imprimirMensajeLn(String.valueOf(analisisBoW.detectarCategoriaTecnica(descripcion)));

        interfaz.imprimirMensajeLn("Estado de animo del usuario: " + analisisBoW.detectarEstadoAnimo(descripcion));

    }

    public void imprimirTickets() {
        for (String ticket : gestorTicket.obtenerTickets()) {
            interfaz.imprimirMensajeLn(ticket);
        }
    }

    public void iniciarSesion() throws IOException {
        interfaz.imprimirMensaje("Digite el id de su usuario: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        Usuario usuario = gestorUsuario.buscarPorId(id);

        interfaz.imprimirMensaje("Digite su contraseña: ");
        String contrasena = interfaz.leerTexto();

        boolean iniciaSesion = login.iniciaSesion(usuario, contrasena);

        if (!iniciaSesion) {
            interfaz.imprimirMensajeLn("Lo sentimos. Usuario incorrecto o contraseña incorrecta!");
        } else {
            interfaz.imprimirMensajeLn("Inicio de sesión exitoso!");

        }
    }

}
