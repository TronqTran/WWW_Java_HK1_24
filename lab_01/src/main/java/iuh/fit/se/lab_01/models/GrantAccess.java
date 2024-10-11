package iuh.fit.se.lab_01.models;

import jakarta.persistence.*;

@Entity
@Table(name = "grant_access")
public class GrantAccess {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role_id;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;
    private int is_grant;
    private String note;

    public GrantAccess() {
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getIs_grant() {
        return is_grant;
    }

    public void setIs_grant(int is_grant) {
        this.is_grant = is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role_id=" + role_id +
                ", account=" + account +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}
