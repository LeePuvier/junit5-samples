/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTests {

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		Calculator calculator = new Calculator();
		assertEquals(3, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({
			"0,    0,   1",
			"1,    2,   3",
			"49,  51, 100",
			"1,  100, 101"
	})
	void add(int first, int second, int expectedResult) {
		Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

	@ParameterizedTest
	@ValueSource(strings = {"One", "Two", "Three", "Four"})
	void palindromes(String candoidate){
		assertTrue(StringUtils.isNotBlank(candoidate));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	void testArgumentTypeInt(int argument){
		assertTrue(argument > 0 && argument < 4);
	}

	/**
	 * NUll EMPTY判断：@NullAndEmptySource @NullSource @EmptySource
	 */

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"", " ", "\t", "\n"})
	void testArgumentTupeStringNullEmpty(String argument){
		assertTrue(argument == null || argument.trim().isEmpty());
	}


	/**
	 * Enum 类型
	 */

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class )
	void testArgumentTypeEnum(TimeUnit timeUnit){
		assertNotNull(timeUnit);
	}


	@ParameterizedTest
	@EnumSource(value = TimeUnit.class , names = {"DAYS", "HOURS"})
	void testArgumentTypeEnumName(TimeUnit timeUnit){
		assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class , mode = EnumSource.Mode.EXCLUDE, names = {"DAYS", "HOURS"})
	void testArgumentTypeEnumMode(TimeUnit timeUnit){
		assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
		assertTrue(timeUnit.name().length() > 5);
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, mode = EnumSource.Mode.MATCH_ALL, names = "^(M|N).+SECONDS")
	void testArgumentEnumModeRegx(TimeUnit timeUnit){
		assertTrue(timeUnit.name().startsWith("M") || timeUnit.name().startsWith("N"));
		assertTrue(timeUnit.name().endsWith("SECONDS"));
	}


	/**
	 * Method 方法：static、Stream
	 * 1、name：指定方法
	 * 2、defalut: 默认查询相同方法名
	 */

	@ParameterizedTest
	@MethodSource("stringProvider")
	void testArgumetnMethod(String argument){
		assertNotNull(argument);
	}

	static Stream<String> stringProvider(){
		return Stream.of("Apple", "Banana");
	}

	@ParameterizedTest
	@MethodSource
	void testArgumetnMethodDefault(String argument){
		assertNotNull(argument);
	}

	static Stream<String> testArgumetnMethodDefault(){
		return Stream.of("Apple", "Banana");
	}

	@ParameterizedTest
	@MethodSource("streamProvider")
	void testArgumetnMethodStream(int argument){
		assertNotEquals(9, argument);
	}

	static IntStream streamProvider(){
		return IntStream.range(0, 20).skip(10);
	}


	@ParameterizedTest
	@MethodSource("stringIntAndListProvider")
	void testArgumetnMethodMultiArg(String str, int num, List<String> list){
		assertEquals(5, str.length());
		assertTrue(num >=1 && num <=2);
		assertEquals(2,list.size());
	}

	static Stream<Arguments> stringIntAndListProvider(){
		return Stream.of(
				arguments("apple", 1, Arrays.asList("a", "b")),
				arguments("lemon", 2, Arrays.asList("x", "y"))
		);

	}


	/**
     *  CsvFileSource
     */


    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String country, int reference){

        assertNotNull(country);
        assertNotEquals(0, reference);
    }


    /**
     *  ArgumentsProvider Class
     *
     * @param argument
     *
     */

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }

    @Test
    void test(){
        File f = new File(".");
            System.out.println(f.getAbsoluteFile());
    }


	@DisplayName("Display name of container")
	@ParameterizedTest(name = "{index} ==> fruit=''{0}'', rank={1}")
	@CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
	void testWithCustomDisplayNames(String fruit, int rank) {
	}
}