package database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EstructuraDB {

    public static void crearDB() {
        try (Connection miCon = ConexionDB.conectar()) {
            if (miCon != null) {
                DatabaseMetaData meta = miCon.getMetaData();
                System.out.println(">>> Base de datos creada.");
            }
        } catch (SQLException e) {
            System.err.println(">>> ERROR EN LA CREACIÓN DE LA BASE DE DATOS. ERROR: " + e.getMessage());
        }
    }

    public static void crearTablaAsignaturas() {

        //al utilizar el try with resources con un objeto closeable, este se cerrará en cualquier caso.
        try (Connection miCon = ConexionDB.conectar()) {

            //sentencias SQL para crear tabla departamentos
            String tablaAsiganturas = "CREATE TABLE C1_ASIGNATURAS" +
                    "(" +
                    "COD_ASIG CHAR(6) NOT NULL PRIMARY KEY," +
                    "NOMBRE_ASI VARCHAR(30)" +
                    ");";

            //sentencias SQL para añadir los valores de varios departamentos a la tabla
            List<String> addAsignaturas = Arrays.asList(
                    "insert into C1_ASIGNATURAS VALUES ('IF0001','DAHC');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0002','RAL');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0003','IMSI');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0004','DPEG');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0006','PLE');",
                    "insert into C1_ASIGNATURAS VALUES ('IF0007','FPE');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0001','Lengua 1 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0002','Lengua 2 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0003','Lengua 3 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('LG0004','Lengua 4 ESO');",
                    "insert into C1_ASIGNATURAS VALUES ('DB0001','Pl�stica');",
                    "insert into C1_ASIGNATURAS VALUES ('DB0002','Taller cer�mica');",
                    "insert into C1_ASIGNATURAS VALUES ('DB0003','Dibujo T�cnico');",
                    "insert into C1_ASIGNATURAS VALUES ('MT0001','Matem�ticas 1 BAC');",
                    "insert into C1_ASIGNATURAS VALUES ('MT0002','Matem�ticas 2 BAC');"
            );

            //variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaAsignaturas = miCon.createStatement();

            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            crearTablaAsignaturas.executeUpdate("DROP TABLE IF EXISTS C1_ASIGNATURAS");
            crearTablaAsignaturas.executeUpdate(tablaAsiganturas);

            for (String a : addAsignaturas) {
                crearTablaAsignaturas.executeUpdate(a);
            }

            System.out.println(">>> TABLA C1_ASIGNATURAS CREADA CORRECTAMENTE");

        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> ERROR: Error en la sintaxis de la sentencia SQL" + e.getMessage() + " (TABLA C1_ASIGNATURAS)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> ERROR: La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage() + " (TABLA C1_ASIGNATURAS)");
        } catch (SQLException e) {
            System.err.println(">>> ERROR: No se puede conectar a la base de datos (TABLA C1_ASIGNATURAS");
        }
    }

    public static void crearTablaCentros() {
        try (Connection miCon = ConexionDB.conectar()) {

            //crear tabla Centros
            String tablaCentros = "CREATE TABLE C1_CENTROS" +
                    "(" +
                    "COD_CENTRO NUMERIC(4) NOT NULL PRIMARY KEY ," +
                    "NOM_CENTRO VARCHAR(20)," +
                    "DIRECTOR NUMERIC(4)," +
                    "DIRECCION VARCHAR(25)," +
                    "LOCALIDAD VARCHAR(20)," +
                    "PROVINCIA VARCHAR(20)" +
                    ") ;";

            List<String> addCentros = Arrays.asList(
                    "INSERT INTO C1_CENTROS VALUES (1000,'IES El Quijote', 1000, 'Avda. Los Molinos 25', 'GUADALAJARA', 'GUADALAJARA');",
                    "INSERT INTO C1_CENTROS VALUES (1015,'CP Los Danzantes', 1010, 'C/Las Musas s/n','PASTRANA', 'GUADALAJARA');",
                    "INSERT INTO C1_CENTROS VALUES (1022, 'IES Planeta Tierra',2000, 'C/Mina 45', 'AZUQUECA', 'GUADALAJARA');",
                    "INSERT INTO C1_CENTROS VALUES (1045, 'CP Manuel Hidalgo', NULL, 'C/Granada 5', 'GUADALAJARA', 'GUADALAJARA');",
                    "INSERT INTO C1_CENTROS VALUES (1050, 'IES Anto�ete', NULL, 'C/Los Toreros 21', 'SIGUENZA', 'GUADALAJARA');"
            );

            Statement crearTablaCentros = miCon.createStatement();

            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            crearTablaCentros.executeUpdate("DROP TABLE IF EXISTS C1_CENTROS");
            crearTablaCentros.executeUpdate(tablaCentros);

            for (String c : addCentros) {
                crearTablaCentros.execute(c);
            }

            System.out.println(">>> TABLA C1_CENTROS CREADA CORRECTAMENTE");

        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> ERROR: Error en la sintaxis de la sentencia SQL" + e.getMessage() + " (TABLA C1_CENTROS)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> ERROR: La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage() + " (TABLA C1_CENTROS)");
        } catch (SQLException e) {
            System.err.println(">>> ERROR: No se puede conectar a la base de datos (TABLA C1_CENTROS)");
        }

    }

    public static void crearTablaEspecialidad() {

        try (Connection miCon = ConexionDB.conectar()) {

            //crear tabla Especialidad
            String tablaEspecialidad = "CREATE TABLE C1_ESPECIALIDAD(" +
                    "ESPECIALIDAD CHAR(2) NOT NULL PRIMARY KEY," +
                    "NOMBRE_ESPE VARCHAR(25)" +
                    ") ;";

            List<String> addEspecialidad = Arrays.asList(
                    "insert into C1_ESPECIALIDAD VALUES ('IF','Informática');",
                    "insert into C1_ESPECIALIDAD VALUES ('IN','Inglés');",
                    "insert into C1_ESPECIALIDAD VALUES ('FQ','Física y Química');",
                    "insert into C1_ESPECIALIDAD VALUES ('GH','Geografía e Historia');",
                    "insert into C1_ESPECIALIDAD VALUES ('TG','Tecnología');",
                    "insert into C1_ESPECIALIDAD VALUES ('LG','Lengua');",
                    "insert into C1_ESPECIALIDAD VALUES ('DB','Dibujo');",
                    "insert into C1_ESPECIALIDAD VALUES ('MT','Matemáticas');"
            );

            Statement crearTablaEspecialidad = miCon.createStatement();

            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            crearTablaEspecialidad.executeUpdate("DROP TABLE IF EXISTS C1_ESPECIALIDAD");
            crearTablaEspecialidad.executeUpdate(tablaEspecialidad);

            for (String e : addEspecialidad) {
                crearTablaEspecialidad.execute(e);
            }

            System.out.println(">>> TABLA C1_ESPECIALIDAD CREADA CORRECTAMENTE");

        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> ERROR: Error en la sintaxis de la sentencia SQL" + e.getMessage() + " (TABLA C1_ESPECIALIDAD)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> ERROR: La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage() + " (TABLA C1_ESPECIALIDAD)");
        } catch (SQLException e) {
            System.err.println(">>> ERROR: No se puede conectar a la base de datos (TABLA C1_ESPECIALIDAD)");
        }
    }

    public static void crearTablaProfesores() {

        try (Connection miCon = ConexionDB.conectar()) {

            //crear tabla Profesores
            String tablaProfesores = "CREATE TABLE C1_PROFESORES" +
                    "(" +
                    "COD_PROF NUMERIC(4) NOT NULL PRIMARY KEY," +
                    "NOMBRE_APE VARCHAR(30)," +
                    "ESPECIALIDAD CHAR(2) REFERENCES C1_ESPECIALIDAD(ESPECIALIDAD )," +
                    "JEFE_DEP NUMERIC(4)," +
                    "FECHA_NAC DATE," +
                    "SEXO CHAR(1)," +
                    "COD_CENTRO NUMERIC(4) NOT NULL REFERENCES C1_CENTROS (COD_CENTRO)" +
                    ") ;";

            List<String> addProfesores = Arrays.asList(
                    "INSERT INTO C1_PROFESORES VALUES (1000, 'Mart�nez Salas, Fernando', 'IF', 1001, '1961-09-07', 'H', 1000);",
                    "INSERT INTO C1_PROFESORES VALUES (1001, 'Bueno Zarco, Elisa', 'IF',NULL, '1960-02-17', 'M', 1000);",
                    "INSERT INTO C1_PROFESORES VALUES (2002, 'Rivera Silvestre, Ana','DB',3000, '1950-10-10', 'M',1000);",
                    "INSERT INTO C1_PROFESORES VALUES (3000, 'De Lucas Fdez, M.Angel','DB',NULL, '1980-09-09','M',1000);",
                    "INSERT INTO C1_PROFESORES VALUES (1010, 'Montes Garc�a, M.Pilar', 'MT', 1011,'1970-10-10', 'M', 1015);",
                    "INSERT INTO C1_PROFESORES VALUES (1011, 'Arroba Conde, Manuel', 'MT', NULL,'1970-10-12', 'H', 1015);",
                    "INSERT INTO C1_PROFESORES VALUES (1022, 'Ruiz Lafuente, Manuel','MT',1011, '1966-11-11', 'H',1015);",
                    "INSERT INTO C1_PROFESORES VALUES (2000, 'Ramos Ruiz, Luis','LG',2003, '1963-08-08', 'H',1022 );",
                    "INSERT INTO C1_PROFESORES VALUES (2003, 'Segura Molina, Irene','LG',NULL, '1963-07-08', 'M',1022 );",
                    "INSERT INTO C1_PROFESORES VALUES (1045, 'Serrano Lagu�a, Mar�a','IF',NULL,'1976-01-02', 'M', 1022);"
            );

            Statement crearTablaProfesores = miCon.createStatement();

            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
//            crearTablaProfesores.executeUpdate("DROP TABLE IF EXISTS C1_ESPECIALIDAD");
//            crearTablaProfesores.executeUpdate("DROP TABLE IF EXISTS C1_CENTROS");
            crearTablaProfesores.executeUpdate("DROP TABLE IF EXISTS C1_PROFESORES");
            crearTablaProfesores.executeUpdate(tablaProfesores);

            for (String p : addProfesores) {
                crearTablaProfesores.execute(p);
            }

            System.out.println(">>> TABLA C1_PROFESORES CREADA CORRECTAMENTE");

        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> ERROR: Error en la sintaxis de la sentencia SQL" + e.getMessage() + " (TABLA C1_PROFESORES)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> ERROR: La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage() + " (TABLA C1_PROFESORES)");
        } catch (SQLException e) {
            System.err.println(">>> ERROR: No se puede conectar a la base de datos (TABLA C1_PROFESORES)");
        }
    }

    public static void crearTablaAsigProf() {

        try (Connection miCon = ConexionDB.conectar()) {

            //crear tabla AsigProf
            String tablaAsigProf = "CREATE TABLE C1_ASIGPROF" +
                    "(" +
                    "COD_ASIG CHAR(6) NOT NULL REFERENCES C1_ASIGNATURAS (COD_ASIG)," +
                    "COD_PROF NUMERIC(4) NOT NULL REFERENCES C1_PROFESORES (COD_PROF)," +
                    "    PRIMARY KEY (COD_ASIG,COD_PROF)\n" +
                    ") ;";

            List<String> addAsigProf = Arrays.asList(
                    "insert into C1_ASIGPROF VALUES ('IF0002',1001);",
                    "insert into C1_ASIGPROF VALUES ('IF0003',1001);",
                    "insert into C1_ASIGPROF VALUES ('IF0001',1000);",
                    "insert into C1_ASIGPROF VALUES ('LG0001',2000);",
                    "insert into C1_ASIGPROF VALUES ('LG0002',2000);",
                    "insert into C1_ASIGPROF VALUES ('LG0003',2003);",
                    "insert into C1_ASIGPROF VALUES ('LG0004',2003);",
                    "insert into C1_ASIGPROF VALUES ('DB0001',2002);",
                    "insert into C1_ASIGPROF VALUES ('DB0002',2002);",
                    "insert into C1_ASIGPROF VALUES ('DB0003',3000);",
                    "insert into C1_ASIGPROF VALUES ('MT0001',1010);",
                    "insert into C1_ASIGPROF VALUES ('MT0001',1011);",
                    "insert into C1_ASIGPROF VALUES ('MT0001',1022);",
                    "insert into C1_ASIGPROF VALUES ('MT0002',1010);"
            );

            Statement crearTablaAsigProf = miCon.createStatement();

            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
//            crearTablaAsigProf.executeUpdate("DROP TABLE IF EXISTS C1_ASIGNATURAS");
//            crearTablaAsigProf.executeUpdate("DROP TABLE IF EXISTS C1_PROFESORES");
            crearTablaAsigProf.executeUpdate("DROP TABLE IF EXISTS C1_ASIGPROF");
            crearTablaAsigProf.executeUpdate(tablaAsigProf);

            for (String a : addAsigProf) {
                crearTablaAsigProf.execute(a);
            }

            System.out.println(">>> TABLA C1_ASIGPROF CREADA CORRECTAMENTE");

        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> ERROR: Error en la sintaxis de la sentencia SQL" + e.getMessage() + " (TABLA C1_ASIGPROF)");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> ERROR: La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage() + " (TABLA C1_ASIGPROF)");
        } catch (SQLException e) {
            System.err.println(">>> ERROR: No se puede conectar a la base de datos (TABLA C1_ASIGPROF)");
        }
    }

    public static void eliminarBBDD() {

        Path p = Path.of("target/escuela.dat");       // Ruta del archivo a leer

        //File archivoBD = p.toFile();

        // Cerrar cualquier conexión existente a la base de datos antes de eliminarla
        try {
            // Verificar si el archivo de la base de datos existe
            if (Files.exists(p)) {
                // Eliminar el archivo de la base de datos

                if (Files.deleteIfExists(p)) {
                    System.out.println("\n>>> Base de datos eliminada con éxito.");
                } else {
                    System.err.println("\n>>> ERROR: No se pudo eliminar la base de datos.");
                }

            } else {
                System.err.println("\n>>> ERROR: La base de datos no existe.");
            }
        } catch (IOException e) {
            System.err.println(">>>ERROR: Error en la conexión. " + e.getMessage());

        }
    }
}
