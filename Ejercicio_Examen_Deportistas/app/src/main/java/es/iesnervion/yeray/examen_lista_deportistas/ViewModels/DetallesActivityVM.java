package es.iesnervion.yeray.examen_lista_deportistas.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import es.iesnervion.yeray.examen_lista_deportistas.DetallesActivity;

public class DetallesActivityVM extends ViewModel {
    private String _nombreActualDeportista;
    private int _foto;
    private MutableLiveData<String> _nombreDeportistaEdit;
    private MutableLiveData<String> _numeroPuntosPorPartido;
    private MutableLiveData<String> _numeroRebotesPorPartido;
    private MutableLiveData<String> _numeroAsistenciasPorPartido;
    private ArrayList<String> _posiciones;

    public DetallesActivityVM(){
        _posiciones = new ArrayList<String>();
    }

    public MutableLiveData<String> getNombreDeportistaEdit() {
        if (_nombreDeportistaEdit == null) {
            _nombreDeportistaEdit = new MutableLiveData<String>();
        }
        return _nombreDeportistaEdit;
    }

    public MutableLiveData<String> getNumeroPuntosPorPartido() {
        if (_numeroPuntosPorPartido == null) {
            _numeroPuntosPorPartido = new MutableLiveData<String>();
        }
        return _numeroPuntosPorPartido;
    }

    public MutableLiveData<String> getNumeroRebotesPorPartido() {
        if (_numeroRebotesPorPartido == null) {
            _numeroRebotesPorPartido = new MutableLiveData<String>();
        }
        return _numeroRebotesPorPartido;
    }

    public MutableLiveData<String> getNumeroAsistenciasPorPartido() {
        if (_numeroAsistenciasPorPartido == null) {
            _numeroAsistenciasPorPartido = new MutableLiveData<String>();
        }
        return _numeroAsistenciasPorPartido;
    }

    public String get_nombreActualDeportista() {
        return _nombreActualDeportista;
    }

    public void set_nombreActualDeportista(String _nombreActualDeportista) {
        this._nombreActualDeportista = _nombreActualDeportista;
    }

    public ArrayList<String> get_posiciones() {
        return _posiciones;
    }

    public void set_posiciones(ArrayList<String> _posiciones) {
        this._posiciones = _posiciones;
    }

    public int get_foto() {
        return _foto;
    }

    public void set_foto(int _foto) {
        this._foto = _foto;
    }
}
