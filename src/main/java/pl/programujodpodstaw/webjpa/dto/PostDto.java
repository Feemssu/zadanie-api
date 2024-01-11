package pl.programujodpodstaw.webjpa.dto;

import pl.programujodpodstaw.webjpa.entity.User;

public class PostDto {

    private String body;
    private String login;
    private String displayName;

    public PostDto() {
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
