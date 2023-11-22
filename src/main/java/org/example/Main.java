package org.example;

import java.util.Scanner;

/**
 * Crea un programa que presente un menú al usuario con las siguientes opciones:
 * 1. Crear la base de datos (permitirá crear la estructura y cargar los datos del archivo adjunto)
 * 2. Borrar la base de datos
 * 3. Listar los profesores que imparten clase en una determinada especialidad que el usuario elegirá de la lista de especialidades posibles que se le mostrarán por pantalla.
 * 4. Insertar un profesor nuevo por consola(tanto la especialidad como el centro deberá elegirse de entre los ya existentes) realizando todas las validaciones pertinentes.
 */


public class Main {
    public static void main(String[] args) {

        // DECLARACIONES
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String opcion = "";

        // BUCLE PARA MOSTRAR EL MENÚ DE OPCIONES
        while (!salir) {

            System.out.println("""
                    \n----------------------------------------------
                    1. Crear base de datos y cargar datos
                    2. Borrar base de datos
                    3. Listar profesores por su especialidad
                    4. Insertar nuevo profesor             
                    0. Salir
                    ----------------------------------------------""");

            opcion = sc.nextLine();                                                                         // Leer y guardar la opción del usuario.

            // ESTRUCTURA PARA LA LLAMADA A LOS MÉTODOS
            switch (opcion) {
                case "0" ->
                        salir = true;                                                                   // Fin de la ejecución del menú.

                case "1" -> {
                    database.EstructuraDB.crearDB();
                    database.EstructuraDB.crearTablaAsignaturas();
                    database.EstructuraDB.crearTablaCentros();
                    database.EstructuraDB.crearTablaEspecialidad();
                    database.EstructuraDB.crearTablaProfesores();
                    database.EstructuraDB.crearTablaAsigProf();
                } // Llamada para crear la BBDD y las tablas con sus datos correspondientes.

                 case "2" -> database.EstructuraDB.eliminarBBDD();


                case "3" -> database.ListarProfesores.listarEspecialidades();                                            // Llamada para crear nuevo departamento.

                case "4" ->
                        database.insNuevoProfesor.crearNuevoProfesor();                                            // Llamada para crear nuevo empleado.

                default ->
                        System.err.println("\n>>> OPCIÓN NO VÁLIDA: Introduzca una opción del menú");        // Informar al usuario de un error cometido.
            }
        }
    }
}