package database;

import java.sql.*;

public class ListarProfesores {

    public static void listarEspecialidades() {

        System.out.println("\n ----[ LISTADO DE ESPECIALIDADES ]----\n");

        try (Connection miCon = ConexionDB.conectar()) {
            String codigo = "SELECT ESPECIALIDAD, NOMBRE_ESPE FROM C1_ESPECIALIDAD";
            PreparedStatement stmt = miCon.prepareStatement(codigo);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String key = resultSet.getString("ESPECIALIDAD");
                String nombre = resultSet.getString("NOMBRE_ESPE");
                System.out.println(key + ". " + nombre);
            }

            if (!listarProfesores()) {
                System.err.println("\n>>>ERROR: No se ha encontrado la especialidad");
            }

        } catch (SQLException e) {
            System.err.println(">>> ERROR EN LA EXTRACCIÓN DE DATOS (TABLA C1_ESPECIALIDAD). ERROR: " + e.getMessage());
        }
    }

    public static boolean listarProfesores() {

        String seleccion = libs.Leer.pedirCadena("\nIntroduce el id de la especialidad:");
        Boolean encontrado = false;

        try (Connection miCon = ConexionDB.conectar()) {

            System.out.println("\n----[ LISTADO DE PROFESORES DE LA ESPECIALIDAD " + seleccion + " ]----");

            String codigo = "SELECT * FROM C1_PROFESORES WHERE ESPECIALIDAD = ?";
            PreparedStatement stmt = miCon.prepareStatement(codigo);
            stmt.setString(1, seleccion.toUpperCase());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int codigoProf = resultSet.getInt("COD_PROF");
                String nombre = resultSet.getString("NOMBRE_APE");
                String especialidad = resultSet.getString("ESPECIALIDAD");
                String fechaNac = resultSet.getString("FECHA_NAC");
                String sexo = resultSet.getString("SEXO");

                System.out.println("\n---------------------------------------");
                System.out.println("\n- Código Profesor: " + codigoProf);
                System.out.println("- Nombre: " + nombre);
                System.out.println("- Especialidad: " + especialidad);
                System.out.println("- Fecha de nacimiento: " + fechaNac);
                System.out.println("- Sexo: " + sexo);

                encontrado = true;
            }

        } catch (SQLException e) {
            System.err.println(">>> ERROR EN LA EXTRACCIÓN DE DATOS (TABLA C1_PROFESORES). ERROR: " + e.getMessage());
        }

        return encontrado;
    }
}


