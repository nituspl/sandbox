package com.nts.drools.model;

import lombok.Data;

@Data
public class UserData {

    private Integer age;
    private String profession;
    private String postalCode;
    private Boolean allAbove24;
    private String typeOfUse;
    private Integer annualMileage;

}
