package me.swat018.demoinfleanrestapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            errors.rejectValue("basePrice", "wrongValue","BasePrice is wrong");
            errors.rejectValue("maxPrice", "wrongValue","MaxPrice is wrong");
            errors.reject("wrongPrices", "Values of Prices are wrong");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
        endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
        endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrong");
        }

        // TODO BeginEventDateTime
        LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
        if (beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime()) ||
        beginEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())) {
            errors.rejectValue("beginEventDateTime", "wrongValue", "beginEventDateTime is wrong");
        }

        // TODO CloseEnrollmentDateTime
        LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();
        if (closeEnrollmentDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "closeEnrollmentDateTime is wrong");
        }
    }
}
