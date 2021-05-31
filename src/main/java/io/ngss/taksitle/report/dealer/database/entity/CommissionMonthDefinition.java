package io.ngss.taksitle.report.dealer.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "CommissionMonthDefinition")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CommissionMonthDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int startMonthExcluding;

    private int endMonthIncluding;

    public Long getId() {
        return id;
    }

    public int getStartMonthExcluding() {
        return startMonthExcluding;
    }

    public void setStartMonthExcluding(int startMonthExcluding) {
        this.startMonthExcluding = startMonthExcluding;
    }

    public int getEndMonthIncluding() {
        return endMonthIncluding;
    }

    public void setEndMonthIncluding(int endMonthIncluding) {
        this.endMonthIncluding = endMonthIncluding;
    }
}
