package nl.duckacademy.rest_api.model;

import java.math.BigDecimal;

public record UserPatch(
                        String name,
                        BigDecimal salary) {

}
