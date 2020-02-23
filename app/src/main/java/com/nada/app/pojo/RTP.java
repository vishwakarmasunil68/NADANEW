package com.nada.app.pojo;

public class RTP {
    String sno;
    String name;
    String gender;
    String sports;
    String discipline;

    public RTP(String sno, String name, String gender, String sports, String discipline) {
        this.sno = sno;
        this.name = name;
        this.gender = gender;
        this.sports = sports;
        this.discipline = discipline;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
