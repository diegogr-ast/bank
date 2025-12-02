package es.nextdigital.demo.application.account.query;

import es.nextdigital.demo.domain.Account;
import es.nextdigital.demo.domain.repositoy.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class MovementsQueryHandlerTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private MovementsQueryHandler sut;

	@Test
	void ask_should_return_empty_when_account_not_exists() {
		final var accountId = UUID.randomUUID();
		final var clientId = UUID.randomUUID();
		final var query = new MovementsQuery(accountId, clientId);

		when(this.accountRepository.findById(new Account.Id(accountId))).thenReturn(Optional.empty());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			this.sut.ask(query);
		});
	}

	@Test
	void ask_should_return_empty_when_account_exists_but_there_are_no_movements() {
		final var accountId = UUID.randomUUID();
		final var clientId = UUID.randomUUID();
		final var query = new MovementsQuery(accountId, clientId);

		final var account = new Account(new Account.Id(accountId), new Account.Iban("ES91 2100 0418 4502 0005 1332"),
				List.of(), new Account.Balance(BigDecimal.ZERO));
		when(this.accountRepository.findById(new Account.Id(accountId))).thenReturn(Optional.of(account));
		final var result = this.sut.ask(query);

		assertThat(result).isEmpty();
	}

	@Test
	void ask_should_return_data_when_account_exists_but_there_are_movements() {
		final var accountId = UUID.randomUUID();
		final var clientId = UUID.randomUUID();
		final var query = new MovementsQuery(accountId, clientId);

		final var movement = new Account.Movement(UUID.randomUUID(), Account.Movement.Type.FEE, LocalDateTime.now(),
				BigDecimal.TEN);
		final var account = new Account(new Account.Id(accountId), new Account.Iban("ES91 2100 0418 4502 0005 1332"),
				List.of(movement), new Account.Balance(BigDecimal.ZERO));
		when(this.accountRepository.findById(new Account.Id(accountId))).thenReturn(Optional.of(account));
		final var result = this.sut.ask(query);

		assertThat(result).hasSize(1);
	}
}
