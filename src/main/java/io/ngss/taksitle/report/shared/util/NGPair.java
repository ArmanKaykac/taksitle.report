package io.ngss.taksitle.report.shared.util;

import java.util.List;

public class NGPair {

    //private Long dealerId; //first
    //private Long bankId; //second
    private String bankName;
    private List<String> bankService;

    public NGPair(String bankName, List<String> bankService) {
        this.bankName = bankName;
        this.bankService = bankService;
    }

    /*public NGPair(Long first, Long second) {
        this.first = first;
        this.second = second;
    }*/

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<String> getBankService() {
        return bankService;
    }

    public void setBankService(List<String> bankService) {
        this.bankService = bankService;
    }
}
