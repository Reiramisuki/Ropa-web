package ar.org.centro8.curso.java.aplicaciones.repositories.jdbc;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticuloRepository implements I_ArticuloRepository{
    
    private Connection conn;

    public ArticuloRepository(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void save(Articulo articulo) {
        if(articulo==null) return;
        try (PreparedStatement ps=conn.prepareStatement(
                "insert into articulos (descripcion,tipo,color,talle_num,stock,stockMin,stockMax,costo,precio,temporada) "
                        + "values (?,?,?,?,?,?,?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, articulo.getDescripcion());
            ps.setString(2, articulo.getTipo().toString());
            ps.setString(3, articulo.getColor());
            ps.setString(4, articulo.getTalle_num());
            ps.setInt(5, articulo.getStock());
            ps.setInt(6, articulo.getStockMin());
            ps.setInt(7, articulo.getStockMax());
            ps.setDouble(8, articulo.getCosto());
            ps.setDouble(9, articulo.getPrecio());
            ps.setString(10, articulo.getTemporada().toString());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) articulo.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Articulo articulo) {
        if(articulo==null) return;
        try (PreparedStatement ps=conn.prepareStatement("delete from articulos where id=?")){
            ps.setInt(1, articulo.getId());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public void update(Articulo articulo) {
        if(articulo==null) return;
        try (PreparedStatement ps=conn.prepareStatement(
                "update articulos set descripcion=? ,tipo=?, color=?, talle_num=?, "
                + "stock=?, stockMin=?, stockMax=?, costo=?, precio=?, temporada=? "
                + "where id=?"
        )){
            ps.setString(1, articulo.getDescripcion());
            ps.setString(2, articulo.getTipo().toString());
            ps.setString(3, articulo.getColor());
            ps.setString(4, articulo.getTalle_num());
            ps.setInt(5, articulo.getStock());
            ps.setInt(6, articulo.getStockMin());
            ps.setInt(7, articulo.getStockMax());
            ps.setDouble(8, articulo.getCosto());
            ps.setDouble(9, articulo.getPrecio());
            ps.setString(10, articulo.getTemporada().toString());
            ps.setInt(11, articulo.getId());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Articulo> getAll() {
        List<Articulo> list=new ArrayList();
        try (ResultSet rs=conn.createStatement().executeQuery("select * from articulos")){
            while(rs.next()){
                list.add(
                        new Articulo(
                                rs.getInt("id"), 
                                rs.getString("descripcion"), 
                                Tipo.valueOf(rs.getString("tipo")), 
                                rs.getString("color"), 
                                rs.getString("talle_num"), 
                                rs.getInt("stock"), 
                                rs.getInt("stockMin"), 
                                rs.getInt("stockMax"), 
                                rs.getDouble("costo"), 
                                rs.getDouble("precio"), 
                                Temporada.valueOf(rs.getString("temporada"))
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}