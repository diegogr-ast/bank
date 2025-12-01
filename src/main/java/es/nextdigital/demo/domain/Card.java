package es.nextdigital.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Card extends AbstractAggregateRoot {
	@NonNull
	private final Id id;

	@NonNull
	private final Number number;

	@NonNull
	private final Account account;

	@NonNull
	private final Type type;

	@NonNull
	private final Limit limit;

	@NonNull
	private final IsActive isActive;

	public Card(@NonNull final Id id, @NonNull final Number number, @NonNull final Account account,
			@NonNull final Type type, @NonNull final Limit limit, @NonNull final IsActive isActive) {
		this.id = id;
		this.number = number;
		this.account = account;
		this.type = type;
		this.limit = limit;
		this.isActive = isActive;
	}

	public record Id(@NonNull String value) {
	}

	public record Number(@NonNull String value) {
	}

	public record Account(@NonNull UUID value) {

	}
	public record Type(@NonNull CardType value) {
		public enum CardType {
			CREDIT, DEBIT
		}
	}

	public record Limit(@NonNull BigDecimal value) {
	}

	public record IsActive(boolean value) {
	}
}
