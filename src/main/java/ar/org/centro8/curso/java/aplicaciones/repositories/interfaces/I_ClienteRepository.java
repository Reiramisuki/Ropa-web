package ar.org.centro8.curso.java.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ClienteRepository {

    void save(Cliente cliente);

    void remove(Cliente cliente);

    void update(Cliente cliente);

    List<Cliente> getAll();

    default Cliente getById(int id) {
        return getAll()
                .stream()
                .filter(c -> c.getId() == id)
                .findAny()
                .orElse(new Cliente());
    }

    default List<Cliente> getLikeApellido(String apellido) {
        if (apellido == null) {
            return new ArrayList<Cliente>();
        }
        return getAll()
                .stream()
                .filter(c -> c.getApellido().toLowerCase()
                .contains(apellido.toLowerCase()))
                .collect(Collectors.toList());

    }

    default List<Cliente> getApellido(String apellido) {
        if (apellido == null) {
            return new ArrayList<Cliente>();
        }
        return getAll()
                .stream()
                .filter(
                        c -> c.getApellido().toLowerCase()
                                .contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
    }

    default List<Cliente> getLikeNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null) {
            return new ArrayList<Cliente>();
        }
        return getAll()
                .stream()
                .filter(c -> c.getNumeroDocumento().toLowerCase()
                .contains(numeroDocumento.toLowerCase()))
                .collect(Collectors.toList());
    }

    default List<Cliente> getLikeDocumentoYApellido(String numeroDocumento, String apellido) {

        if (apellido == null && numeroDocumento == null) {
            return new ArrayList<Cliente>();
        }

        return getAll()
                .stream()
                .filter(
                        c -> c.getApellido().toLowerCase()
                              .contains(apellido.toLowerCase())
                        && c.getNumeroDocumento().toLowerCase()
                              .contains(numeroDocumento.toLowerCase()))
                .collect(Collectors.toList());

    }
}
