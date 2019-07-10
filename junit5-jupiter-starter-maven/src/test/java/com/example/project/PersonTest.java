package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/2  下午9:22
 * @ContentUse ：
 */
class PersonTest {

    @ParameterizedTest
    @CsvSource( {
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    } )
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {

        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.get(2, Gender.class),
                arguments.get(3, LocalDate.class)
        );


//        if (person.getFirstName().equals("Jane")) {
//            assertEquals(Gender.F, person.getGender());
//        }
//        else {
//            assertEquals(Gender.M, person.getGender());
//        }
        assertEquals("Doe", person.getLastName());
        assertEquals(1990, person.getDateOfBirth().getYear());
    }


    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        // perform assertions against person
    }


    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
        // perform assertions against person
    }

}