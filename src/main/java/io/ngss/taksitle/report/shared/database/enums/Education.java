package io.ngss.taksitle.report.shared.database.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Education {

    EGITIMSIZ("EGITIMSIZ", "1",0),
    ILKOKUL("İLK OKUL", "2",1),
    ORTAOKUL("ORTA OKUL", "3",2),
    LISE("LİSE", "4",3),
    YUKSEKOKUL("YUKSEK OKUL", "5",4),
    LISANS("LISANS", "6",5),
    LISANSUSTU("LISANS ÜSTÜ", "7",6),
    DOKTORA("DOKTORA", "8",7);

    private final String friendlyName;
    private final String sekerString;
    private final int id;

    Education(String friendlyName, String sekerString,int id) {
        this.friendlyName = friendlyName;
        this.sekerString = sekerString;
        this.id=id;
    }

    @JsonProperty("friendlyName")
    public String getName(){
        return this.friendlyName;
    }

    @JsonProperty("sekerString")
    public String getSekerString() {
        return this.sekerString;
    }

    public int getId() {
        return id;
    }
}