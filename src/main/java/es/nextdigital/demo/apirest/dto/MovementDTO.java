package es.nextdigital.demo.apirest.dto;

import java.math.BigDecimal;

public record MovementDTO(String type, BigDecimal quantity) {
}
