package es.iesnervion.yeray.routemarker.Entities;

import java.util.ArrayList;

public class Route {
    private int _id;
    private String _name;

    public Route(){
        _id = 0;
        _name = "DEFAULT";
    }

    public Route(int id, String name){
        _id = id;
        _name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
