package com.example.project;

import org.junit.jupiter.params.aggregator.AggregateWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/2  下午9:37
 * @ContentUse ：
 */
@Retention( RetentionPolicy.RUNTIME)
@Target( ElementType.PARAMETER)
@AggregateWith(PersonAggregator.class)
@interface CsvToPerson {
}
