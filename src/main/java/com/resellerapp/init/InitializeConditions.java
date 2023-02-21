package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitializeConditions implements CommandLineRunner {
    private final static String EXCELLENT_CONDITION_TEXT = "In perfect condition";
    private final static String GOOD_CONDITION_TEXT = "Some signs of wear and tear or minor defects";
    private final static String ACCEPTABLE_CONDITION_TEXT = "The item is fairly worn but continues to function properly";

    private final ConditionRepository conditionRepository;

    public InitializeConditions(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() <= 0) {
            Condition excellentCondition = new Condition(ConditionName.valueOf("EXCELLENT"), EXCELLENT_CONDITION_TEXT);
            Condition goodCondition = new Condition(ConditionName.valueOf("GOOD"), GOOD_CONDITION_TEXT);
            Condition acceptableCondition = new Condition(ConditionName.valueOf("ACCEPTABLE"), ACCEPTABLE_CONDITION_TEXT);

            List<Condition> conditions = new ArrayList<>(List.of(excellentCondition, goodCondition, acceptableCondition));

            this.conditionRepository.saveAll(conditions);
        }
    }
}
