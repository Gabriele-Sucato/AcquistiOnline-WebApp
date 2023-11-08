package main.java.repository;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Client;

public class HandleClientRepo implements ClientRepository {
    private List<Client> clients = new ArrayList<>();

    @Override
    public Client findById(int clientCode) {
        for (Client clients : client) {
            if (client.getClientCode() == clientCode) {
                return client;
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    @Override
    public void update(Client client) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientCode() == client.getClientCode()) {
                clients.set(i, client);
                return;
            }
        }
    }

    @Override
    public void delete(int clientCode) {
        clients.removeIf(client -> client.getClientCode() == clientCode);
    }
}
