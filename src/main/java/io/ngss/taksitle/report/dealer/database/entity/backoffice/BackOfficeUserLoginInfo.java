package io.ngss.taksitle.report.dealer.database.entity.backoffice;

import javax.persistence.*;

@Entity
@Table(name = "backoffice_user_login_info")
public class BackOfficeUserLoginInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long time;

    public String password;

    public Long backOfficeUserId;

    public Long getTime() {
        return time;
    }
}