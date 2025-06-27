package utils.Encriptar;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Matias
 */
public class Encriptar {
    // Genera el hash de la contraseña
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verifica si la contraseña ingresada coincide con el hash guardado
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}