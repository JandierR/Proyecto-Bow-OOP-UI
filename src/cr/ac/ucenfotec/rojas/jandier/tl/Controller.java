package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.entities.*;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.*;

import java.io.IOException;


public class Controller {
    private GestorUsuario gestorUsuario;
    private GestorDepartamento gestorDepartamento;
    private GestorTicket gestorTicket;
    private GestorDiccionarioEmocional gestorDiccionarioEmocional;
    private GestorDiccionarioTecnico gestorDiccionarioTecnico;
    private Login login;
    private UI interfaz;
    private AnalisisBoW analisisBoW;


    public Controller() {
        gestorUsuario = new GestorUsuario();
        gestorDepartamento = new GestorDepartamento();
        gestorTicket = new GestorTicket();
        gestorDiccionarioEmocional = new GestorDiccionarioEmocional();
        gestorDiccionarioTecnico = new GestorDiccionarioTecnico();
        login = new Login();
        interfaz = new UI();
        analisisBoW = new AnalisisBoW();
    }

    public void start() throws IOException {
        int opcion;
        boolean sesionIniciada = false;

        while (!sesionIniciada) {
            interfaz.menuInicioSesion();
            opcion = interfaz.leerOpcion();

            if (opcion == 0) {
                interfaz.imprimirMensajeLn("Saliendo del sistema");
                return;
            }

            if (opcion == 1) {
                registrarUsuario();
            } else if (opcion == 2) {
                sesionIniciada = iniciarSesion();
            } else {
                interfaz.imprimirMensajeLn("Valor invalido");
            }
        }

        do {
            interfaz.imprimirMenu();
            opcion = interfaz.leerOpcion();
            procesarOpcion(opcion);

        } while (opcion != 0);

    }

    public void procesarOpcionInicioSesion(int opcion) throws IOException {
        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> iniciarSesion();
            case 0 -> interfaz.imprimirMensajeLn("Saliendo del sistema");
            default -> interfaz.imprimirMensajeLn("Valor invalido");
        }
    }

    public void procesarOpcion(int opcion) throws IOException {

        switch (opcion) {
            case 1 -> registrarDepartamento();
            case 2 -> registrarTicket();
            case 3 -> registrarPalabraEmocional();
            case 4 -> registrarPalabraTecnica();
            case 5 -> imprimirUsuarios();
            case 6 -> imprimirDepartamentos();
            case 7 -> imprimirTickets();
            case 8 -> eliminarUsuario();
            case 9 -> eliminarDepartamento();
            case 10 -> eliminarTicket();
            case 11 -> eliminarPalabraEmocional();
            case 12 -> eliminarPalabraTecnica();
            case 13 -> modificarUsuario();
            case 14 -> modificarDepartamento();
            case 15 -> modificarTicket();
            case 16 -> modificarPalabraEmociona();
            case 17 -> modificarPalabraTecnica();
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

        String resultado = gestorTicket.registrarTicket(id, asunto, descripcion, estado, idUsuario, idDepartamento);

        interfaz.imprimirMensajeLn(resultado);
        if (resultado.equalsIgnoreCase("Ticket registrado exitosamente")) {

            interfaz.imprimirMensajeLn("Categorías sugeridas para el ticket");
            interfaz.imprimirMensajeLn(String.valueOf(analisisBoW.detectarCategoriaTecnica(descripcion)));

            interfaz.imprimirMensajeLn("Estado de animo del usuario: " + analisisBoW.detectarEstadoAnimo(descripcion));
        }


    }

    public void imprimirTickets() {
        for (String ticket : gestorTicket.obtenerTickets()) {
            interfaz.imprimirMensajeLn(ticket);
        }
    }

    public boolean iniciarSesion() throws IOException {
        interfaz.imprimirMensaje("Digite el id de su usuario: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        boolean existeUsuario = gestorUsuario.existeUsuario(id);

        if (!existeUsuario) {
            interfaz.imprimirMensajeLn("Lo sentimos. El usuario no existe");
            return false;
        }

        Usuario usuario = gestorUsuario.buscarPorId(id);

        interfaz.imprimirMensaje("Digite su contraseña: ");
        String contrasena = interfaz.leerTexto();

        boolean iniciaSesion = login.iniciaSesion(usuario, contrasena, id);

        if (!iniciaSesion) {
            interfaz.imprimirMensajeLn("Lo sentimos. Usuario incorrecto o contraseña incorrecta!");
            return false;
        } else {
            interfaz.imprimirMensajeLn("Inicio de sesión exitoso!");
            return true;
        }
    }

    public void modificarUsuario() throws IOException {

        interfaz.imprimirMensaje("Digite el #ID del usuario que desea modificar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el nuevo nombre del usuario: ");
        String nombre = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo correo del usuario: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la nueva contraseña: ");
        String contrasena = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo numero de teléfono: ");
        String telefono = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo rol del usuario: ");
        String rol = interfaz.leerTexto();

        Usuario usuarioModificado = new Usuario(nombre, correo, contrasena, telefono, rol, id);

        interfaz.imprimirMensajeLn(gestorUsuario.modificarUsuario(usuarioModificado));

    }

    public void eliminarUsuario() throws IOException {
        interfaz.imprimirMensaje("Digite el id del usuario a eliminar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensajeLn(gestorUsuario.eliminarUsuario(id));
    }

    public void modificarTicket() throws IOException {
        interfaz.imprimirMensaje("Digite el ID del ticket que desea modificar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el nuevo asunto: ");
        String asunto = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la nueva descripción: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo estado (Nuevo / En Progreso / Resuelto): ");
        String estado = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo #ID del usuario: ");
        int idUsuario = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el nuevo #ID del departamento: ");
        int idDepartamento = Integer.parseInt(interfaz.leerTexto());

        Usuario usuario = gestorUsuario.buscarPorId(idUsuario);
        Departamento departamento = gestorDepartamento.buscarPorId(idDepartamento);

        interfaz.imprimirMensajeLn(gestorTicket.modificarTicket(new Ticket(id, asunto, descripcion, estado, usuario, departamento)));
    }

    public void eliminarTicket() throws IOException {
        interfaz.imprimirMensaje("Digite el id del ticket a eliminar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensajeLn(gestorTicket.eliminarTicket(id));
    }


    public void modificarDepartamento() throws IOException {
        interfaz.imprimirMensaje("Digite el #ID del departamento que desea modificar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensaje("Digite el nuevo nombre del departamento: ");
        String nombreDepartamento = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la nueva descripción del departamento: ");
        String descripcion = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el nuevo correo del departamento: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDepartamento.modificarDepartamento(new Departamento(nombreDepartamento, descripcion, correo, id)));
    }

    public void eliminarDepartamento() throws IOException {
        interfaz.imprimirMensaje("Digite el id del departamento a eliminar: ");
        int id = Integer.parseInt(interfaz.leerTexto());

        interfaz.imprimirMensajeLn(gestorDepartamento.eliminarDepartamento(id));
    }

    public void modificarPalabraEmociona() throws IOException {
        System.out.println("Digite la palabra que desea modificar: ");
        String palabra = interfaz.leerTexto();

        System.out.println("Digite el nuevo tipo de emoción: ");
        String emocion = interfaz.leerTexto();

        PalabraEmocional palabraEmocional = new PalabraEmocional(palabra, emocion);

        interfaz.imprimirMensajeLn(gestorDiccionarioEmocional.modificarPalabraEmocional(palabraEmocional));
    }

    public void eliminarPalabraEmocional() throws IOException {
        interfaz.imprimirMensaje("Digite la palabra emocional a eliminar: ");
        String palabraEmocional = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDiccionarioEmocional.eliminarPalabraEmocional(palabraEmocional));
    }

    public void modificarPalabraTecnica() throws IOException {
        System.out.println("Digite la palabra que desea modificar: ");
        String palabraTecnica = interfaz.leerTexto();

        System.out.println("Digite la nueva categoria técnica: ");
        String categoria = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDiccionarioTecnico.modificarPalabraTecnica(new PalabraTecnica(palabraTecnica, categoria)));
    }

    public void eliminarPalabraTecnica() throws IOException {
        interfaz.imprimirMensaje("Digite la palabra técnica a eliminar: ");
        String palabraTecnica = interfaz.leerTexto();

        interfaz.imprimirMensajeLn(gestorDiccionarioTecnico.eliminarPalabraTecnica(palabraTecnica));

    }

}
