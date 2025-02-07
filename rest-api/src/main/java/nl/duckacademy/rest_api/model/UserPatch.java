package nl.duckacademy.rest_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserPatch(
                        String name,
                        BigDecimal salary) {

}
