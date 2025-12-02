package es.nextdigital.demo.domain;

import es.nextdigital.demo.domain.validator.IbanValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Account extends AbstractAggregateRoot {
	@NonNull
	private final Id id;

	@NonNull
	private final Iban iban;

	@NonNull
	private final List<Movement> movements;

	@NonNull
	private final Balance balance;

	public Account(final @NonNull Id id, final @NonNull Iban iban, final @NonNull List<Movement> movements,
			final @NonNull Balance balance) {
		this.id = id;
		this.iban = iban;
		this.movements = movements;
		this.balance = balance;
	}

	public record Id(@NonNull UUID value) {
	}

	public record Iban(String value) {
		public boolean isValid() {
			return IbanValidator.isValidIban(this.value);
		}
	}

	public record Movement(@NonNull UUID id, @NonNull Type type, @NonNull LocalDateTime date,
			@NonNull BigDecimal quantity) {
		public enum Type {
			IN, OUT, FEE
		}
	}

	public record Balance(@NonNull BigDecimal value) {
	}
}