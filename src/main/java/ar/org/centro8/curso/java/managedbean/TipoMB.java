package ar.org.centro8.curso.java.managedbean;

import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named()
@SessionScoped()
public class TipoMB implements Serializable{
    public List<Tipo> getTipos(){
        return List.of(Tipo.values());
    }
}