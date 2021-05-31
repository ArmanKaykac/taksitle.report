package io.ngss.taksitle.report.customermanagement.enums;

public enum WorkingType {

    PrivateSector("1",0),
    Public("2",1),
    SelfEmployment("3",2),
    WorkingRetiredSelf("4",3),
    WorkingRetiredPaid("5",4),
    Retired("6",5),
    SeasonalEmployment("7",6),
    Student("8",7),
    Housewife("9",8),
    Agriculture("10",9),
    Unemployment("11",10);

    private String definition;

    private int order;

    WorkingType(String definition,int order) {
        this.definition = definition;this.order=order;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}