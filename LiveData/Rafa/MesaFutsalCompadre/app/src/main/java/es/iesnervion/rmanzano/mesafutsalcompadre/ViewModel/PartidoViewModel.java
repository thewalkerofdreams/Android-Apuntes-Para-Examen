package es.iesnervion.rmanzano.mesafutsalcompadre.ViewModel;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


import es.iesnervion.rmanzano.mesafutsalcompadre.Fila;

public class PartidoViewModel extends ViewModel {
    private Integer golLocal;
    private Integer golVisitante;
    private Integer faltaLocal;
    private Integer faltaVisitante;
    ArrayList<Fila> filasLocal = new ArrayList<>();
    ArrayList<Fila> filasVisitante = new ArrayList<>();
    private MutableLiveData<ArrayList<Fila>> tarjetasLocales;
    private MutableLiveData<ArrayList<Fila>> tarjetasVisitantes;
    private String cronometro;
    private String periodo;

    public PartidoViewModel() {
        golLocal = 0;
        golVisitante = 0;
        faltaLocal = 0;
        faltaVisitante = 0;
        tarjetasLocales = new MutableLiveData<>();
        tarjetasVisitantes = new MutableLiveData<>();
        cronometro = "20:00";
        periodo = "1";
    }

    public Integer getGolLocal() {
        return golLocal;
    }

    public void setGolLocal(Integer golLocal) {
        this.golLocal = golLocal;
    }

    public Integer getGolVisitante() {
        return golVisitante;
    }

    public void setGolVisitante(Integer golVisitante) {
        this.golVisitante = golVisitante;
    }

    public Integer getFaltaLocal() {
        return faltaLocal;
    }

    public void setFaltaLocal(Integer faltaLocal) {
        this.faltaLocal = faltaLocal;
    }

    public Integer getFaltaVisitante() {
        return faltaVisitante;
    }

    public void setFaltaVisitante(Integer faltaVisitante) {
        this.faltaVisitante = faltaVisitante;
    }

    public MutableLiveData<ArrayList<Fila>> getTarjetasLocales() {
        return tarjetasLocales;
    }

    public void setTarjetasLocales(MutableLiveData<ArrayList<Fila>> tarjetasLocales) {
        this.tarjetasLocales = tarjetasLocales;
    }


    public MutableLiveData<ArrayList<Fila>> getTarjetasVisitantes() {
        return tarjetasVisitantes;
    }
    public void setTarjetasVisitantes(MutableLiveData<ArrayList<Fila>> tarjetasVisitantes) {
        this.tarjetasVisitantes = tarjetasVisitantes;
    }


    public void anhadirTarjetaLocal(Fila fila) {
        filasLocal.add(fila);
        tarjetasLocales.setValue(filasLocal);
    }

    public void anhadirTarjetaVisitante(Fila fila) {
        filasVisitante.add(fila);
        tarjetasVisitantes.setValue(filasVisitante);
    }


    public String getCronometro() {
        return cronometro;
    }

    public void setCronometro(String cronometro) {
        this.cronometro = cronometro;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
