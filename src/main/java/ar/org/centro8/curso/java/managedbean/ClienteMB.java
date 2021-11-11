package ar.org.centro8.curso.java.managedbean;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
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

public class ClienteMB implements Serializable {

    private I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
    private Cliente cliente;
    private String buscarNumeroDocumento = "";
    private String buscarApellido = "";
    private String mensaje = "";

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void openNew() {
        this.cliente = new Cliente();
    }

    public void guardar() {
        if (cliente.getId() == 0) {
            save();
        } else {
            update();
        }
    }

    public void save() {
        try {
            cr.save(this.cliente);
            //Si el id=0 hubo un error en el save
            if (cliente.getId() > 0) {
                mensaje = "Se guardó el cliente id=" + cliente.getId();

            } else {
                mensaje = "Ocurrió un error";
            }

        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Ocurrio un error! " + e.getMessage();
        }

        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", mensaje);
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
    }

    public void update() {
        cr.update(this.cliente);
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Se actualizó el cliente id=" + this.cliente.getId());
        PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");

    }

    public void remove() {
        try {
            cr.remove(this.cliente);
            if (cr.getById(cliente.getId()).getId() == 0) {
                mensaje = "Se eliminó el cliente id=" + this.cliente.getId();
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
        cliente = null;

    }

    public List<Cliente> getAll() {
        return cr.getAll();
    }

    public List<Cliente> getLikeDocumenoYApellido() {
        return cr.getLikeDocumentoYApellido(buscarNumeroDocumento, buscarApellido);
    }

   

    public Cliente getCliente() {
        return cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

  

    public String getBuscarNumeroDocumento() {
        return buscarNumeroDocumento;
    }

    

    public String getBuscarApellido() {
        return buscarApellido;
    }

   

    public void setBuscarNumeroDocumento(String buscarNumeroDocumento) {
        this.buscarNumeroDocumento = buscarNumeroDocumento;
    }

  
    public void setBuscarApellido(String buscarApellido) {
        this.buscarApellido = buscarApellido;
    }

}
