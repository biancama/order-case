package com.afkl.travel.exercise.endpoint;

import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static liquibase.repackaged.org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LocationController.class)
public class LocationControllerTest {
    @Autowired
    protected WebApplicationContext context;
    //@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
    @MockBean
    private LocationService locationService;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
    @Test
    @DisplayName("/locations should return an array of locations")
    public void givenSomeLocationThenTheyAreReturned() throws Exception {
        List<Location> expectedLocations = asList(
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build()
                );

        given(locationService.findLocations(any(HttpServletRequest.class))).willReturn(expectedLocations);
        ResultActions resultActions = mockMvc
                .perform(get("/locations")
                        .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));

        for (int i = 0; i < 5; i++) {
            resultActions
                    .andExpect(jsonPath(format("$[%d].code", i)).value(expectedLocations.get(i).getCode()))
                    .andExpect(jsonPath(format("$[%d].type", i)).value(expectedLocations.get(i).getType()))
                    .andExpect(jsonPath(format("$[%d].description", i)).value(expectedLocations.get(i).getDescription()));
        }

    }

    @Test
    @DisplayName("/locations/{type}/{code} should return a location")
    public void givenLocationWhenCalledWithCorrectCodeThenItIsReturned() throws Exception {
        var expectedLocation = Optional.of(
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build()
        );

        given(locationService.findLocation(eq("FLC"), eq(LocationType.AIRPORT), any(HttpServletRequest.class))).willReturn(expectedLocation);
        ResultActions resultActions = mockMvc
                .perform(get("/locations/airport/FLC")
                        .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$").value(expectedLocation.get()));


    }
}
