package ar.org.centro8.curso.java.managedbean;

import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named()
@SessionScoped()
public class TemporadaMB implements Serializable{
    public List<Temporada> getTemporadas(){
        return List.of(Temporada.values());
    }
}