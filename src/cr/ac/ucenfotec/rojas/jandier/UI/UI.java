package cr.ac.ucenfotec.rojas.jandier.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    private BufferedReader in;

    public UI() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void menuInicioSesion() {
        imprimirMensajeLn("-".repeat(50));
        imprimirMensajeLn("""
                1. Registrar usuario
                2. Iniciar sesión
                0. Salir""");
        imprimirMensajeLn("-".repeat(50));

    }

    public void imprimirMenu() {
        imprimirMensajeLn("-".repeat(50));
        imprimirMensajeLn("""
                1. Registrar usuario
                2. Registrar departamento
                3. Registrar ticket
                4. Registrar palabra emocional
                5. Registrar palabra técnica
                6. Listar usuarios
                7. Listar departamentos
                8. Listar tickets
                9. Eliminar usuario
                10. Modificar usuario
                0. Salir""");
        imprimirMensajeLn("-".repeat(50));
    }

    public String leerTexto() throws IOException {
        return in.readLine();
    }

    public int leerOpcion() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    public void imprimirMensaje(String msj) {
        System.out.print(msj);
    }
    public void imprimirMensajeLn(String msj) {
        System.out.println(msj);
    }


}
