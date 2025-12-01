package es.nextdigital.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Client extends AbstractAggregateRoot {
	@NonNull
	private final Id id;

	@NonNull
	private final Name name;

	@NonNull
	private final Accounts accounts;

	@NonNull
	private final Cards cards;

	public Client(@NonNull final Id id, @NonNull final Name name, @NonNull final Accounts accounts,
			@NonNull final Cards cards) {
		this.id = id;
		this.name = name;
		this.accounts = accounts;
		this.cards = cards;
	}

	public record Id(@NonNull String value) {
	}

	public record Name(@NonNull String value) {
	}

	public record Accounts(@NonNull List<UUID> value) {
	}

	public record Cards(@NonNull List<UUID> value) {
	}
}
