package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


//ZAPIS DO BAZY DANYCH

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @Schema(description = "ID")
    private Long id;
    public String no;
    public String effectiveDate;

//    @Schema(description = "Mid Value")
    public double mid;

    public String getNo() {
        return no;
    }

    public double getMid() {
        return mid;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

