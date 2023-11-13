package main.java.model;

public class Client {
    private long clientCode;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    public Client() {

    }

    public Client(long clientCode, String name, String lastName, String address, String email, String phoneNumber) {
        this.clientCode = clientCode;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client(int clientCode, String name, String lastName) {
        this.clientCode = clientCode;
        this.name = name;
        this.lastName = lastName;
    }

    public long getClientCode() {
        return clientCode;
    }

    public void setClientCode(long clientCode) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
