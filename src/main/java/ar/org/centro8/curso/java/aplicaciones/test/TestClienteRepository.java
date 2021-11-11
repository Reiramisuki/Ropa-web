package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ClienteRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ClienteRepository;
import ar.org.centro8.curso.java.connectors.Connector;


public class TestClienteRepository {
    public static void main(String[] args) { 
       I_ClienteRepository cr = new ClienteRepository(Connector.getConnection());
       
       
//     SAVE  
       cr.save(new Cliente("Carlos", "Pedernera", 23, TipoDocumento.DNI,"11345611"));
       cr.save(new Cliente("Pedro", "Pereyra", 30,  TipoDocumento.DNI,"21345622"));
       cr.save(new Cliente("José", "Flores", 18, TipoDocumento.DNI,"31345610"));
       cr.save(new Cliente("Jonatan", "Moreno", 21, TipoDocumento.DNI,"41345606"));
              
//     REMOVE 
        cr.remove(cr.getById(15));
//     UPDATE 
       Cliente cliente = cr.getById(4);
       if(cliente!=null && cliente.getId()!=0){
           cliente.setNombre("Jorge");
           cr.update(cliente);
       }
//     ALL
       System.out.println("***** Todos los clientes ********");
       cr.getAll().forEach(System.out::println);
       
//     LikeApellido
       System.out.println("***** Clientes cuyo apellido contiene P ********");
       cr.getLikeApellido("P").forEach(System.out::println);
      
      
//     LikeNumeroDocumento
       System.out.println("***** Cliente con documento DNI contiene 11 ********");
       cr.getLikeNumeroDocumento("41345606").forEach(System.out::println);
       
//     LikeDocumentoYApellido
       System.out.println("***** Cliente con documento DNI cuyo documento comienza con 3 o su apellido contiene Per*******");
       cr.getLikeDocumentoYApellido("3", "Per").forEach(System.out::println);
    }
    
}
