package quanvan.todoapp.request;

public class AddUserRequest {

    private String name;
    private String password;

    public AddUserRequest() {
    }

    public AddUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
