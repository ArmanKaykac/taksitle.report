package io.ngss.taksitle.report.customermanagement.enums;

public enum HomeOwnership {

    Self("1"),
    Family("2"),
    Boarding("3"),
    Tenant("4");

    private final String sekerString;

    HomeOwnership(String sekerString){
        this.sekerString=sekerString;
    }

    public String getSekerString() {
        return sekerString;
    }

}
