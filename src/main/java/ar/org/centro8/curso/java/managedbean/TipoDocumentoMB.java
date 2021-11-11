package ar.org.centro8.curso.java.managedbean;

import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named()
@SessionScoped()
public class TipoDocumentoMB implements Serializable{
    public List<TipoDocumento> getTipoDocumento(){
          return List.of(TipoDocumento.values());
    }
}
