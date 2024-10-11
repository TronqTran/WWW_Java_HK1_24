package iuh.fit.se.lab_01.models;

import iuh.fit.se.lab_01.enums.AccountStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    private String account_id;
    @Column(name = "full_name")
    private String username;
    private String password;
    private String email;
    private String phone;

    private AccountStatus status;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<GrantAccess> grantAccess;
    public Account() {
    }

    public Account(String account_id, String username, String password, String email, String phone, AccountStatus status) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String acount_id) {
        this.account_id = acount_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public List<GrantAccess> getGrantAccess() {
        return grantAccess;
    }

    public void setGrantAccess(List<GrantAccess> grantAccess) {
        this.grantAccess = grantAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return account_id == account.account_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id='" + account_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", grantAccess=" + grantAccess +
                '}';
    }
}
