package ar.org.centro8.curso.java.managedbean;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.connectors.Connector;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named()
@SessionScoped()
public class ArticuloMB implements Serializable {

    private I_ArticuloRepository ar = new ArticuloRepository(Connector.getConnection());
    private Articulo articulo;
    private String mensaje = "";
    private String buscarDescripcion = "";
   
   

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
      public void openNew() {
        this.articulo = new Articulo();
    }

    public void guardar() {
        if (articulo.getId() == 0) {
            save();
        } else {
            update();
        }

    }

    public void save() {
        try {
            ar.save(this.articulo);
            if (articulo.getId() > 0) {
                mensaje = "Se guardo el articulo id=" + articulo.getId();
              
            } else {
                mensaje = "Ocurrio un error!";
            }
          
        } catch (Exception e) {
             e.printStackTrace();
            mensaje = "Ocurrio un error! " + e.getMessage();
           
        }
         addMessage(FacesMessage.SEVERITY_INFO, "Info Message", mensaje);
             PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
    }

    public void update() {
        ar.update(articulo);
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Se actualizó el articulo id=" + this.articulo.getId());
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
    }

    public void remove() {
      try { 
            ar.remove(this.articulo);
            if (ar.getById(articulo.getId()).getId() == 0) {
                mensaje = "Se eliminó el articulo id=" + this.articulo.getId();
                addMessage(FacesMessage.SEVERITY_INFO, "Info Message", mensaje);
            } else {
                mensaje = "Ocurrio un error! ";
                addMessage(FacesMessage.SEVERITY_FATAL, "Info Message", mensaje);
            }
        } catch (Exception e) {
            mensaje = "Ocurrio un error! " + e.getMessage();
            e.printStackTrace();
            addMessage(FacesMessage.SEVERITY_FATAL, "Info Message", mensaje);
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
        articulo = null;

       
    }
    


    public List<Articulo> getAll() {
        return ar.getAll();
    }

    public List<Articulo> getLikeDescripcion() {
        return ar.getLikeDescripcion(buscarDescripcion);
    }
   

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscarDescripcion() {
        return buscarDescripcion;
    }

    public void setBuscarDescripcion(String buscarDescripcion) {
        this.buscarDescripcion = buscarDescripcion;
    }

   

}
