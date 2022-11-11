package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	final private String filtername = "SomeBeanFilter";

	@GetMapping("filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		return filterDynamically(someBean, "field1");
	}

	@GetMapping("filtering-list")
	public MappingJacksonValue filteringList() {
		Object someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
		return filterDynamically(someBeanList, "field2", "field3", "field1");
	}

	/**
	 * Filtering logic 
	 * 
	 * @param objectToBeFiltered
	 * @param toBeIncluded
	 * @return
	 */
	private MappingJacksonValue filterDynamically(Object objectToBeFiltered, String... toBeIncluded) {
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(objectToBeFiltered);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(toBeIncluded);
		FilterProvider filters = new SimpleFilterProvider().addFilter(filtername, filter);
		// jetzt zusätzlich @JsonFilter("SomeBeanFilter") in SomeBean nötig!!!
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

}
