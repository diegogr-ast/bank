package es.nextdigital.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Atm extends AbstractAggregateRoot {
	@NonNull
	private final Id id;

	@NonNull
	private final Bank bank;

	public Atm(@NonNull final Id id, @NonNull final Bank bank) {
		this.id = id;
		this.bank = bank;
	}

	public record Id(@NonNull UUID id) {

	}

	public record Bank(@NonNull UUID id) {

	}
}
