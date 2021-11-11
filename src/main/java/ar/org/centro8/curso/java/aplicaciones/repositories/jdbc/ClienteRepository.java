package ar.org.centro8.curso.java.aplicaciones.repositories.jdbc;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;

public class ClienteRepository implements I_ClienteRepository {
    private Connection conn;
    
    public ClienteRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Cliente cliente) {
        if(cliente==null) return;
        if(cliente.getTipoDocumento()==null) return;
        try (PreparedStatement ps = conn.prepareStatement(
                "insert into clientes (nombre, apellido, edad, direccion, email, telefono, tipoDocumento, numeroDocumento)"
                        + "values(?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS
        )){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getEdad());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTelefono());
            ps.setString(7, cliente.getTipoDocumento().toString());
            ps.setString(8, cliente.getNumeroDocumento());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) cliente.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Cliente cliente) {
        if(cliente==null) return;
        if(cliente.getTipoDocumento()==null) return;
        try (PreparedStatement ps = conn.prepareStatement("delete from clientes where id=?")){
            ps.setInt(1, cliente.getId());
            ps.execute();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Cliente cliente) {
        if(cliente==null) return;
        if(cliente.getTipoDocumento()==null) return;
        try (PreparedStatement ps = conn.prepareStatement(
                "update clientes set nombre=?, apellido=?, edad=?, direccion=?, email=?, telefono=?, "
                        + "tipoDocumento=?, numeroDocumento=? where id=?"
        )){
          ps.setString(1, cliente.getNombre());
          ps.setString(2, cliente.getApellido());
          ps.setInt(3, cliente.getEdad());
          ps.setString(4, cliente.getDireccion());
          ps.setString(5, cliente.getEmail());
          ps.setString(6, cliente.getTelefono());
          ps.setString(7, cliente.getTipoDocumento().toString());
          ps.setString(8, cliente.getNumeroDocumento());
          ps.setInt(9, cliente.getId());
          ps.execute();
            
        } catch (Exception e) {
        }
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> list = new ArrayList();
        try (ResultSet rs = conn.createStatement().executeQuery("select * from clientes")){
            while(rs.next()){
                list.add(
                        new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            TipoDocumento.valueOf(rs.getString("tipoDocumento")),
                            rs.getString("numeroDocumento")
                            )
                        );
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
         return list;
    }
}
