package es.iesnervion.yeray.listaequiposnba.Datas;

import java.util.ArrayList;

import es.iesnervion.yeray.listaequiposnba.Models.Player;
import es.iesnervion.yeray.listaequiposnba.Models.Team;

public class Lists {
    /*
    Interfaz
    Nombre: obtenerListadoEquipos
    Comentario: Este método nos permite obtener un listado de equipos.
    Cabecera: public static ArrayList<Team> obtenerListadoEquipos()
    Saliada:
        -ArrayList<Team> listado
    Postcondiciones: Este método devuelve un array list del tipo Team asociado
    al nombre, que es la lista con todos los equipos.
     */
    public static ArrayList<Team> obtenerListadoEquipos(){
        ArrayList<Team> listado = new ArrayList<Team>();

        listado.add(new Team(1, "CompadreTeam"));
        listado.add(new Team(2, "Salamanders"));
        listado.add(new Team(3, "GuluGulu"));
        listado.add(new Team(4, "BloodBrothers"));
        listado.add(new Team(5, "ChipsBrothers"));

        return listado;
    }

    /*
    * Interfaz
    * Nombre: obtenerListadoJugadores
    * Comentario: Este método nos permite obtener un listado de jugadores,
    * según un equipo.
    * Cabecera: public static ArrayList<Player> obtenerListadoJugadores(int teamId)
    * Entrada:
    *   -int teamId
    * Salida:
    *   -ArrayList<Player> listado
    * Postcondiciones: El método devuelve un array list del tipo Player
    * asociado al nombre, que son los jugadores de un equipo concreto.
    * */
    public static ArrayList<Player> obtenerListadoJugadores(int teamId){
        ArrayList<Player> listaJugadores = new ArrayList<Player>();

        switch(teamId){
            case 1:
                listaJugadores.add(new Player(1, "Pablo", "Garcia", 1));
                listaJugadores.add(new Player(2, "Paula", "Lerón", 1));
                listaJugadores.add(new Player(3, "Perny", "Lago", 1));
                listaJugadores.add(new Player(4, "Pastafar", "Lee", 1));
                break;
            case 2:
                listaJugadores.add(new Player(5, "Viena", "LaGarcia", 2));
                listaJugadores.add(new Player(6, "Pedro", "Lerón", 2));
                listaJugadores.add(new Player(7, "Maria", "Lago", 2));
                listaJugadores.add(new Player(8, "Rebeca", "Zen", 2));
                break;
            case 3:
                listaJugadores.add(new Player(9, "Juan", "Garcia", 3));
                listaJugadores.add(new Player(10, "Juanita", "Lerón", 3));
                listaJugadores.add(new Player(11, "Juanjo", "Lago", 3));
                listaJugadores.add(new Player(12, "Julia", "Lee", 3));
                break;
            case 4:
                listaJugadores.add(new Player(13, "Salchi", "Papa", 4));
                listaJugadores.add(new Player(14, "Queue", "Rica", 4));
                listaJugadores.add(new Player(15, "Estevan", "Lee", 4));
                listaJugadores.add(new Player(16, "Salchipa", "Phan", 4));
                break;
            case 5:
                listaJugadores.add(new Player(17, "Victor", "Garcia", 5));
                listaJugadores.add(new Player(18, "José", "Lerón", 5));
                listaJugadores.add(new Player(19, "Gato", "Lago", 5));
                listaJugadores.add(new Player(20, "Pablo", "Escobar", 5));
                break;
        }

        return  listaJugadores;
    }
}
