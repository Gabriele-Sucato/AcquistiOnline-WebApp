package main.java.api.servlets;

import main.java.model.Client;
import main.java.repository.interfaces.ClientRepository;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/clients")
public class ClientServlet extends HttpServlet {
    private final ClientRepository clientRepository;

    public ClientServlet(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Gestisce le richieste GET per l'elenco dei clienti
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Ottieni l'elenco dei clienti dal repository
            List<Client> clients = clientRepository.getAllClients();

            // Crea un oggetto JSON che rappresenta l'elenco dei clienti
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Client client : clients) {
                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("clientCode", client.getClientCode())
                        .add("name", client.getName())
                        .add("lastName", client.getLastName())
                        .build());
            }

            // Imposta la risposta HTTP con il contenuto JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Scrivi il JSON nella risposta
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonArrayBuilder.build().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero dei clienti");
        }
    }
}
