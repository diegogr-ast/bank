package es.nextdigital.demo.application.account.query;

import org.springframework.lang.NonNull;

import java.util.UUID;

public record MovementsQuery(@NonNull UUID accountId, @NonNull UUID clientId) {
}
