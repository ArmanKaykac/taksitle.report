package io.ngss.taksitle.report.transaction.service;

public enum TimeRange {
    time0_6(0),
    time6_12(1),
    time12_36(2),
    time36_60(3),
    time60plus(5);

    private final int sekerValue;

    TimeRange(int sekerValue){
        this.sekerValue=sekerValue;
    }

    public int getSekerString() {
        return sekerValue;
    }
}
