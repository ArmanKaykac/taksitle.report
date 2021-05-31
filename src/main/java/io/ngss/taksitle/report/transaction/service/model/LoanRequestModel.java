package io.ngss.taksitle.report.transaction.service.model;

import io.ngss.taksitle.report.bank.database.entity.LoanRequest;

public class LoanRequestModel {
    private Long id;
    private Long date;
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LoanRequestModel(Long id, Long date, Double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public static LoanRequestModel convertToLoanOffer(LoanRequest loanRequest) {

        return new LoanRequestModel(loanRequest.getId(),loanRequest.getTransaction().getInitialDate(),loanRequest.getRequestedLoanAmount());

    }

}
