package es.iesnervion.yeray.listaequiposnba.ViewModels;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.iesnervion.yeray.listaequiposnba.Datas.Lists;
import es.iesnervion.yeray.listaequiposnba.Models.Player;
import es.iesnervion.yeray.listaequiposnba.Models.Team;

public class MainActivityVM extends ViewModel {
    private ArrayList<Team> _teamList;
    private ArrayList<Player> _playerList;
    private Team _selectedTeam;

    public MainActivityVM(){
        _teamList = Lists.obtenerListadoEquipos();
        _playerList = new ArrayList<Player>();
        _selectedTeam = null;
    }

    public ArrayList<Team> get_teamList() {
        return _teamList;
    }

    public void set_teamList(ArrayList<Team> _teamList) {
        this._teamList = _teamList;
    }

    public ArrayList<Player> get_playerList() {
        return _playerList;
    }

    public void set_playerList(ArrayList<Player> _playerList) {
        this._playerList = _playerList;
    }

    public Team get_selectedTeam() {
        return _selectedTeam;
    }

    public void set_selectedTeam(Team _selectedTeam) {
        this._selectedTeam = _selectedTeam;
    }

    /*
    * Interfaz
    * Nombre: cargarListaJugadores
    * Comentario: Este método nos permite obtener la lista de jugadores
    * de una equipo. Este método modifica el estado del atributo _playerList.
    * Cabecera: public void cargarListaJugadores(int idEquipo)
    * Entrada:
    *   -int idEquipo
    * Postcondiciones: El método modifica el estado del atributo _playerList.
    * */
    public void cargarListaJugadores(int idEquipo){
        set_playerList(Lists.obtenerListadoJugadores(idEquipo));
    }
}
