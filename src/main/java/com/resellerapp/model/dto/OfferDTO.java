package com.resellerapp.model.dto;

import com.resellerapp.model.enums.ConditionName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OfferDTO {

    @Size(min = 2, max = 50)
    @NotNull
    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotNull(message = "You must select a condition!")
    private ConditionName conditionName;

    public OfferDTO() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ConditionName getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
    }
}
