package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.interfaces.ConditionService;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public Condition findConditionByName(ConditionName conditionName) {
        return this.conditionRepository.findByConditionName(conditionName).orElse(null);
    }
}
