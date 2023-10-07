package quanvan.todoapp.request;

public class UpdateTodoRequest {

    private String content;
    private Boolean completed;

    public UpdateTodoRequest(String content, Boolean completed) {
        this.content = content;
        this.completed = completed;
    }

    public UpdateTodoRequest() {
    }

    public String getContent() {
        return content;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
