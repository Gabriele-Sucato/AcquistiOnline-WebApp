package main.java.model;

public class Client {
    private int clientCode;
    private String name;
    private String lastName;

    public Client() {

    }

    public Client(int clientCode, String name, String lastName) {
        this.clientCode = clientCode;
        this.name = name;
        this.lastName = lastName;
    }

    public int getClientCode() {
        return clientCode;
    }

    public void setClientCode(int clientCode) {
        this.clientCode = clientCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
