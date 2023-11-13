package main.java.repository.interfaces;

import java.util.List;

import main.java.model.Client;

public interface ClientRepository {
    Client findById(int clientCode);

    List<Client> findAll();

    void save(Client client);

    void update(Client client);

    void delete(int clientCode);

    List<Client> getAllClients();
}
