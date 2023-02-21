package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Column(name = "condition_name")
    @Enumerated(EnumType.STRING)
    private ConditionName conditionName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Condition() {}

    public Condition(ConditionName conditionName, String description) {
        this.conditionName = conditionName;
        this.description = description;
    }

    public ConditionName getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
