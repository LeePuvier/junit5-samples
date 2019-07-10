package com.example.project;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/1  下午11:19
 * @ContentUse ：
 */
public class ToStringArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}
