package ar.org.centro8.curso.java.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ArticuloRepository {
    void save(Articulo articulo);
    void remove(Articulo articulo);
    void update(Articulo articulo);
    List<Articulo>getAll();
    default Articulo getById(int id){
        return getAll()
                .stream()
                .filter(a->a.getId()==id)
                .findAny()
                .orElse(new Articulo());
    }
    default List<Articulo>getLikeDescripcion(String descripcion){
        if(descripcion==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a!=null && a.getDescripcion()!=null &&
                        a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }
    default List<Articulo>getByTemporada(Temporada temporada){
        if(temporada==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getTemporada()==temporada)
                .collect(Collectors.toList());
    }
    default List<Articulo>getByTipo(Tipo tipo){
        if(tipo==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getTipo()==tipo)
                .collect(Collectors.toList());
    }
    default List<Articulo>getLikeTipoDescripcion(Tipo tipo, String descripcion){
        if(tipo==null || descripcion==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getTipo()==tipo && 
                        a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }
    default List<Articulo>getLikeTipoDescripcionColor(Tipo tipo, String descripcion, String color){
        if(tipo==null || descripcion==null || color==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getTipo()==tipo && 
                        a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()) &&
                        a.getColor().toLowerCase().contains(color.toLowerCase()))
                .collect(Collectors.toList());
    }
    default List<Articulo>getLikeDescripcionTalle(String descripcion, String talle){
        if(descripcion==null || talle==null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a->a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()) &&
                        a.getTalle_num().toLowerCase().contains(talle.toLowerCase()))
                .collect(Collectors.toList());
    }
    default List<Articulo>getReponer(){
        return getAll()
                .stream()
                .filter(a->a.getStock()<a.getStockMin())
                .collect(Collectors.toList());
    }
}