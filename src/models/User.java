package models;

import enums.Language;
import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {
    private UUID id;
    private String login;
    private String password;
    private Language language;

    public User(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.language = Language.EN;
    }

    public void login() {
        System.out.println(login + " logged in.");
    }

    public void logout() {
        System.out.println(login + " logged out.");
    }
   
    public abstract String getInfo();

    public UUID getId() { return id; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public String toString() {
        return "User{login='" + login + "', language=" + language + "}";
    }
}