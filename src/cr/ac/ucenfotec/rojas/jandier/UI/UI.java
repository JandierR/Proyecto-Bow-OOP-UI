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
        imprimirMensajeLn("        Elija una opción del menu: ");
        imprimirMensajeLn("-".repeat(50));
        imprimirMensajeLn("""
                1. Registrar departamento
                2. Registrar ticket
                3. Registrar palabra emocional
                4. Registrar palabra técnica
                5. Listar usuarios
                6. Listar departamentos
                7. Listar tickets
                8. Eliminar usuario
                9. Eliminar departamento
                10. Eliminar ticket
                11. Eliminar palabra emocional
                12. Eliminar palabra técnica
                13. Modificar usuario
                14. Modificar departamento
                15. Modificar ticket
                16. Modificar palabra emocional
                17. Modificar palabra técnica
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
