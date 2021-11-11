package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
import ar.org.centro8.curso.java.connectors.Connector;

public class TestArticuloRepository {
    public static void main(String[] args) {
       
        I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
        
        ar.save(null);
        ar.save(new Articulo("Botas de Cuero", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Remera", Tipo.ROPA, "Rojo", "L", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Blusa", Tipo.ROPA, "Rojo", "M", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(new Articulo("Sandalias", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0, 0, Temporada.VERANO));
        ar.save(null);
        
        ar.remove(ar.getById(30));
        
        Articulo articulo=ar.getById(28);
        if(articulo!=null && articulo.getId()!=0){
            articulo.setColor("Negro");
            ar.update(articulo);
        }
        
        ar.getAll().forEach(System.out::println);
        System.out.println("*********************************************");
        ar.getLikeDescripcion("bl").forEach(System.out::println);
        
    }
}
