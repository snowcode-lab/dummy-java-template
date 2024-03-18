package com.snowcode.lab.dummy.java.template.controller.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.skyscreamer.jsonassert.JSONAssert;

@UtilityClass
public class JsonMatchers {

    public static Matcher<String> jsonEquals(String expected, boolean strict) {
        return new CustomMatcher<>("Json custom matcher") {
            @Override
            @SneakyThrows
            public boolean matches(Object actual) {
                JSONAssert.assertEquals(expected, actual.toString(), strict);
                return true;
            }
        };
    }

}
