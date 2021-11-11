package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.connectors.Connector;
import java.sql.ResultSet;
import java.time.LocalTime;
public class TestConnector {
    public static void main(String[] args) throws Exception {
        System.out.println(LocalTime.now());
        try{
            ResultSet rs=Connector
                    .getConnection()
                    .createStatement()
                    .executeQuery("select version()");
            if(rs.next()) System.out.println(rs.getString(1));
        }catch(Exception e){
            System.out.println("No se pudo conectar a la BD");
            System.out.println(e);
        }
        System.out.println(LocalTime.now());
    }
}
