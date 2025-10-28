package cr.ac.ucenfotec.rojas.jandier.tl;

import cr.ac.ucenfotec.rojas.jandier.UI.UI;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.DepartamentoManager;
import cr.ac.ucenfotec.rojas.jandier.bl.logic.UsuarioManager;

import java.io.IOException;


public class Controller {
    private UI interfaz = new UI();
    private UsuarioManager usuarioManager = new UsuarioManager();
    private DepartamentoManager departamentoManager = new DepartamentoManager();


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
            case 6 -> imprimirUsuarios();
            case 7 -> imprimirDepartamentos();
            default -> interfaz.imprimirMensajeLn("Valor invalido");
        }
    }

    public void registrarUsuario() throws IOException {

        interfaz.imprimirMensaje("Digite el nombre del usuario: ");
        String nombre = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el Email del usuario: ");
        String correo = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite la contraseña: ");
        String contrasena = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el numero de telefono: ");
        String telefono = interfaz.leerTexto();

        interfaz.imprimirMensaje("Digite el rol del usuario: ");
        String rol = interfaz.leerTexto();

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

        departamentoManager.registrarDepartamento(nombreDepartamento, descripcion, correo);
    }

    public void imprimirDepartamentos() {
        interfaz.imprimirMensajeLn(departamentoManager.getListaDepartamento().toString());
    }
}
