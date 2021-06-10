package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.bank.LoanCategory;

import javax.persistence.*;

@Entity
@Table(name = "media_definition")
public class MediaDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_category")
    private LoanCategory loanCategory;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    public MediaDefinition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanCategory getLoanCategory() {
        return loanCategory;
    }

    public void setLoanCategory(LoanCategory loanCategory) {
        this.loanCategory = loanCategory;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
