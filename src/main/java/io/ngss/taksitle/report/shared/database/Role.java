package io.ngss.taksitle.report.shared.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ngss.taksitle.report.dealer.database.entity.backoffice.BackOfficeUser;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUser;
import io.ngss.taksitle.report.shared.database.entity.Privilege;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Collection<DealerUser> users;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Collection<BackOfficeUser> bofficeUsers;

    @ManyToMany
    @JsonBackReference(value="userroles")
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Collection<BackOfficeUser> getBofficeUsers() {
        return bofficeUsers;
    }

    public void setBofficeUsers(Collection<BackOfficeUser> bofficeUsers) {
        this.bofficeUsers = bofficeUsers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<DealerUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<DealerUser> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}