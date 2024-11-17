package uniquindio.edu.co.util;

public class UtilidadesValidacion {

    public static boolean esDocumentoValido(String documento) {
        return documento != null && documento.matches("\\d{8,10}");
    }

    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean esCampoNoVacio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }
}
