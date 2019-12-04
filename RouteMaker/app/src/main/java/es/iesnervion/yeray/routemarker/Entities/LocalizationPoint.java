package es.iesnervion.yeray.routemarker.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class LocalizationPoint implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public LocalizationPoint createFromParcel(Parcel in) {
            return new LocalizationPoint(in);
        }

        public LocalizationPoint[] newArray(int size) {
            return new LocalizationPoint[size];
        }
    };

    private int _id;
    private int _pointNumber;
    private double _latitude;
    private double _longitude;
    private int _idRoute;

    public LocalizationPoint(){
        _id = 0;
        _pointNumber = 0;
        _latitude = 0;
        _longitude = 0;
        _idRoute = 0;
    }

    public LocalizationPoint(int id, int pointNumber, double latitude, double longitude, int idRoute){
        _id = id;
        _pointNumber = pointNumber;
        _latitude = latitude;
        _longitude = longitude;
        _idRoute = idRoute;
    }

    public LocalizationPoint(double latitude, double longitude){
        _id = 0;
        _pointNumber = 0;
        _latitude = latitude;
        _longitude = longitude;
        _idRoute = 0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_pointNumber() {
        return _pointNumber;
    }

    public void set_pointNumber(int _pointNumber) {
        this._pointNumber = _pointNumber;
    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    public int get_idRoute() {
        return _idRoute;
    }

    public void set_idRoute(int _idRoute) {
        this._idRoute = _idRoute;
    }

    // Parcelling part
    public LocalizationPoint(Parcel in){
        this._id = in.readInt();
        this._pointNumber = in.readInt();
        this._latitude = in.readDouble();
        this._longitude = in.readDouble();
        this._idRoute = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeInt(this._pointNumber);
        dest.writeDouble(this._latitude);
        dest.writeDouble(this._longitude);
        dest.writeInt(this._idRoute);
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id='" + _id + '\'' +
                ", _pointNumber='" + _pointNumber + '\'' +
                ", _latitude='" + _latitude + '\'' +
                ", _longitude='" + _longitude + '\'' +
                ", _idRoute='" + _idRoute + '\'' +
                '}';
    }


}
