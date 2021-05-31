package io.ngss.taksitle.report.transaction.service.model;

import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferProblemReason;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;

import java.util.ArrayList;
import java.util.List;

public class LoanOfferModel {

    private Long id;
    private String bankName;
    private boolean isSelected;
    private Long date;
    private Double amount;
    private Double rate;
    private Integer term;
    private Double totalCost;
    private Double approvedAmount;
    private String kkbScore;
    private LoanOfferStatus offerStatus;
    private LoanOfferProblemReason problemReason;


    public LoanOfferModel(Long id, String bankName, boolean isSelected, Long date, Double amount, Double rate, Integer term, Double totalCost,
                          Double approvedAmount, String kkbScore, LoanOfferStatus offerStatus, LoanOfferProblemReason problemReason) {
        this.id = id;
        this.bankName = bankName;
        this.isSelected = isSelected;
        this.date = date;
        this.amount = amount;
        this.term = term;
        this.rate = rate;
        this.totalCost = totalCost;
        this.approvedAmount = approvedAmount;
        this.kkbScore = kkbScore;
        this.offerStatus = offerStatus;
        this.problemReason = problemReason;
    }

    public static LoanOfferModel convertToLoanOffer(LoanOffer loanOffer, boolean onlySelected) {

        if (onlySelected) {
            if (loanOffer.isSelectedAndNonCanceled()) {
                return new LoanOfferModel(loanOffer.getId(), loanOffer.getBank().getName(), loanOffer.isSelectedAndNonCanceled(), loanOffer.getCreation(),
                        loanOffer.getTotalCost(), loanOffer.getRate(), loanOffer.getTerm(), loanOffer.getTotalCost(),
                        loanOffer.getApproverAmount(), loanOffer.getKkbScore(), loanOffer.getOfferStatus(), loanOffer.getProblemReason());
            }
        } else {
            return new LoanOfferModel(loanOffer.getId(), loanOffer.getBank().getName(), loanOffer.isSelectedAndNonCanceled(),
                    loanOffer.getCreation(), loanOffer.getTotalCost(), loanOffer.getRate(), loanOffer.getTerm(),
                    loanOffer.getTotalCost(), loanOffer.getApproverAmount(), loanOffer.getKkbScore(), loanOffer.getOfferStatus(), loanOffer.getProblemReason());
        }
        return null;
    }

    public static List<LoanOfferModel> convertToLoanOffers(List<LoanOffer> loanOfferList, boolean onlySelected) {
        List<LoanOfferModel> loanOfferModels = new ArrayList<>();
        if (loanOfferList == null || loanOfferList.isEmpty())
            return loanOfferModels;

        for (LoanOffer lo : loanOfferList) {
            LoanOfferModel lom = convertToLoanOffer(lo, onlySelected);
            if (null != lom)
                loanOfferModels.add(lom);
        }

        return loanOfferModels;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getKkbScore() {
        return kkbScore;
    }

    public void setKkbScore(String kkbScore) {
        this.kkbScore = kkbScore;
    }

    public LoanOfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(LoanOfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public LoanOfferProblemReason getProblemReason() {
        return problemReason;
    }

    public void setProblemReason(LoanOfferProblemReason problemReason) {
        this.problemReason = problemReason;
    }
}
