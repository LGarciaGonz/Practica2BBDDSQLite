package database;

import classes.Profesor;
import libs.Leer;

import java.sql.*;

public class insNuevoProfesor {

    public static void crearNuevoProfesor() {

        // PEDIR NOMBRE ---------
        String nombreProfesor = Leer.pedirCadena("\nIntroduce el nombre y apellidos del profesor: ");

        // PEDIR FECHA NACIMIENTO ---------
        Date fechaNac = Date.valueOf(Leer.pedirCadena("\nIntroduce la fecha de nacimiento (YYYY-MM-DD: "));

        // PEDIR SEXO ---------
        String sexo = Leer.pedirCadena("\nIntroduce el sexo (H / M): ");

        // PEDIR CENTRO --------------
        listadoCentros();
        int codCentro = Leer.pedirEntero("\nIntroduce el ID del centro: ");

        // PEDIR ESPECIALIDAD -------------
        ListarProfesores.listarEspecialidades();
        String especialidad = Leer.pedirCadena("\nIntroduce el ID de la especialidad: ");

        insertarProfesor(nombreProfesor, String.valueOf(fechaNac),sexo,codCentro,especialidad);
    }

    private static void insertarProfesor(String nombreProfesor, String fechaNac, String sexo, int codCentro, String especialidad) {

        //datos a insertar
        Profesor newProfe = new Profesor();
        newProfe.setNombre_ape(nombreProfesor);
        newProfe.setEspecialidad(especialidad);
        newProfe.setFecha_nac(Date.valueOf(fechaNac));
        newProfe.setSexo(sexo);
        newProfe.setCod_centro(codCentro);

        try(Connection miCon = ConexionDB.conectar()){
            PreparedStatement pstmt = miCon.prepareStatement("INSERT INTO profesores VALUES (?,?,?,?,?)");

            pstmt.setString(1, nombreProfesor);
            pstmt.setString(2, especialidad);
            pstmt.setDate(3, Date.valueOf(fechaNac));
            pstmt.setString(4, sexo);
            pstmt.setInt(5, codCentro);
            pstmt.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("\n>>> ERROR: No se cumple una condición de integridad de la base de datos");;
        } catch (SQLSyntaxErrorException e){
            System.err.println("\n>>> ERROR: Hay un error de sintaxis" + e.getMessage());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void listadoCentros() {

        try (Connection miCon = ConexionDB.conectar()) {

            System.out.println("\n----[ LISTADO DE CENTROS ]----");

            String codigo = "SELECT * FROM C1_CENTROS";
            PreparedStatement stmt = miCon.prepareStatement(codigo);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int codigoCentro = resultSet.getInt("COD_CENTRO");
                String nombre = resultSet.getString("NOM_CENTRO");

                System.out.println("\n---------------------------------------");
                System.out.println("\n- Código Profesor: " + codigoCentro);
                System.out.println("- Nombre: " + nombre);
            }

        } catch (SQLException e) {
            System.err.println(">>> ERROR EN LA EXTRACCIÓN DE DATOS (TABLA C1_CENTROS). ERROR: " + e.getMessage());
        }
    }


}
