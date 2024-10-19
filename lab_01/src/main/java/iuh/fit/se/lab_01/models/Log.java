package iuh.fit.se.lab_01.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long log_id;
    private Timestamp login_time;
    private Timestamp logout_time;
    private String note;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Log() {
    }

    public Log(Timestamp login_time, String note, Account account) {
        this.login_time = login_time;
        this.note = note;
        this.account = account;
    }


    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public Timestamp getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Timestamp login_time) {
        this.login_time = login_time;
    }

    public Timestamp getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(Timestamp logout_time) {
        this.logout_time = logout_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Log{" +
                "log_id=" + log_id +
                ", login_time=" + login_time +
                ", logout_time=" + logout_time +
                ", note='" + note + '\'' +
                ", account=" + account +
                '}';
    }
}
