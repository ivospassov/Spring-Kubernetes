package com.resellerapp.service.interfaces;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;

public interface ConditionService {

    Condition findConditionByName(ConditionName conditionName);
}
