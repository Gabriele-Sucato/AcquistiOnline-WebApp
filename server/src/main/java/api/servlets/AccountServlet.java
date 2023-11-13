package main.java.api.servlets;

import main.java.model.Client;
import main.java.repository.interfaces.ClientRepository;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/account")
public class AccountServlet extends HttpServlet {
    private final ClientRepository clientRepository;

    public AccountServlet(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String clientCode = getClientCodeFromRequest(request);

            // Get client from repository
            Client client = clientRepository.findById(Integer.parseInt(clientCode));

            // create json with client info
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("clientCode", client.getClientCode())
                    .add("name", client.getName())
                    .add("last_name", client.getLastName())
                    .build();

            // set http request with json
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // write json in the response
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error: is impossible to retrieve account data!");
        }
    }

    private String getClientCodeFromRequest(HttpServletRequest request) {
        return request.getParameter("clientCode");
    }
}
