package me.swat018.demoinfleanrestapi.events;


import me.swat018.demoinfleanrestapi.events.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflern Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        Event event = new Event();
        String name = "Event";
        String description = "Spring";

        // When
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    @ParameterizedTest
    @MethodSource("paramsForTestFree")
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        // When
        event.upadte();
        // Then
        assertThat(event.isFree()).isEqualTo(isFree);

    }

    private Object[] paramsForTestFree() {
        return new Object[] {
            new Object[] {0, 0, true},
            new Object[] {100, 0, false},
            new Object[] {0, 100, false},
            new Object[] {100, 200, false}
        };
    }

    @ParameterizedTest
    @MethodSource("paramsForTestOffline")
    public void TestOffline(String location, boolean isOffline) {
        // Given
        Event event = Event.builder()
                .location(location)
                .build();
        // When
        event.upadte();
        // Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private Object[] paramsForTestOffline() {
        return new Object[] {
                new Object[] {"강남", true},
                new Object[] {null, false},
                new Object[] {"       ", false}
        };
    }
}