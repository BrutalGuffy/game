package mm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "game")
public class User {
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ",user login='" + login + '\'' +
                '}';
    }
}