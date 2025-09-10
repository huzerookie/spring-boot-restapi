package com.tutorial.restapi.util;

import org.springframework.http.converter.json.MappingJacksonValue;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Utils {

    private static final String FILTER_NAME = "CustomDynamicFilter";

    private static <T> MappingJacksonValue applyFilter(T model, SimpleBeanPropertyFilter filter) {
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter(FILTER_NAME, filter);

        MappingJacksonValue mapping = new MappingJacksonValue(model);
        mapping.setFilters(filters);
        return mapping;
    }

    public static <T> MappingJacksonValue includeFields(T model, String... fields) {
        return applyFilter(model, SimpleBeanPropertyFilter.filterOutAllExcept(fields));
    }

    public static <T> MappingJacksonValue excludeFields(T model, String... fields) {
        return applyFilter(model, SimpleBeanPropertyFilter.serializeAllExcept(fields));
    }

    public static <T> MappingJacksonValue noFilter(T model) {
        return applyFilter(model, SimpleBeanPropertyFilter.serializeAll()); // keep all fields
    }
}
