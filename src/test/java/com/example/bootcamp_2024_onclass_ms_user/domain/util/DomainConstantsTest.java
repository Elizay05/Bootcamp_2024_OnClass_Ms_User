package com.example.bootcamp_2024_onclass_ms_user.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainConstantsTest {

    @Test
    @DisplayName("Test Constructor Invocation")
    void testConstructorInvocation() throws NoSuchMethodException {
        Constructor<DomainConstants> constructor = DomainConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}
