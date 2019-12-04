package es.iesnervion.yeray.routemarker.RouteList;

import java.util.ArrayList;

import es.iesnervion.yeray.routemarker.Entities.LocalizationPoint;

public class ItemRouteList {
    private int _id;
    private String _routeName;

    public ItemRouteList(){
        _id = -1;
        _routeName = "DEFAULT";
    }

    public ItemRouteList(int id, String routeName){
        _id = id;
        _routeName = routeName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_routeName() {
        return _routeName;
    }

    public void set_routeName(String _routeName) {
        this._routeName = _routeName;
    }
}
