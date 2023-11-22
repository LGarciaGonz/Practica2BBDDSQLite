package database;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    //definimos como constantes los datos de la conexión a la base de datos
    //como es una base de datos portable será un archivo en nuestro sistema de archivos (sin puerto).
    private static final String URL = "jdbc:sqlite:target/escuela.dat";
    //no necesitamos usuario ni clave.
    //private static final String USUARIO = "";
    //private static final String CLAVE = "";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            //si la base de datos no existe la crea.
            conexion = DriverManager.getConnection((URL));
            System.out.println("\n>>> Conexión OK.\n");
        }catch (SQLException e) {
            System.err.println("\n>>> ERROR: Error en la conexión. " + e.getMessage());
        } catch (InvalidModuleDescriptorException e) {
            System.err.println("\n>>> ERROR PAM");
        }
        return conexion;
    }
}
