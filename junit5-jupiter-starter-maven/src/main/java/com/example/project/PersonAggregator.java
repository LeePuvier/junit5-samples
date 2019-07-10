package com.example.project;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.time.LocalDate;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/2  下午9:34
 * @ContentUse ：
 */
public class PersonAggregator  implements ArgumentsAggregator {
    @Override
    public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.get(2, Gender.class),
                arguments.get(3, LocalDate.class)
        );

        return person;
    }
}
