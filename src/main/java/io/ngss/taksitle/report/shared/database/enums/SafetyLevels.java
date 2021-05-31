package io.ngss.taksitle.report.shared.database.enums;

public enum SafetyLevels {

    VERY_SAFE(0,"VS"),
    SAFE(1,"S"),
    UNSAFE(2,"US"),
    VERY_UNSAFE(3, "VU");

    private int order ;
    private String sekerSkor;

    SafetyLevels(int order,String sekerSkor){
        this.sekerSkor = sekerSkor; this.order=order;
    }

    public String getSekerSkor() {
        return sekerSkor;
    }

    public void setSekerSkor(String sekerSkor) {
        this.sekerSkor = sekerSkor;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
