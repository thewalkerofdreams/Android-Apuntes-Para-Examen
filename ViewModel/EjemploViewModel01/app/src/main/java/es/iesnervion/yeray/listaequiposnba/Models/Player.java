package es.iesnervion.yeray.listaequiposnba.Models;

public class Player {
    private int _id;
    private String _firstName;
    private String _lastName;
    private int _idTeam;

    public Player(){
        _id = 0;
        _firstName = "DEFAULT";
        _lastName = "DEFAULT";
        _idTeam = 0;
    }

    public Player(int id, String firstName, String lastName, int idTeam){
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _idTeam = idTeam;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public int get_idTeam() {
        return _idTeam;
    }

    public void set_idTeam(int _idTeam) {
        this._idTeam = _idTeam;
    }

    @Override
    public String toString(){
        return get_firstName() +" "+ get_lastName();
    }
}
