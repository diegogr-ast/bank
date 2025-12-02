package es.nextdigital.demo.infrastructure.repository;

import es.nextdigital.demo.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class AccountRepositoryTest {

	@Mock
	private MockAccountRepository mockAccountRepository;

	@InjectMocks
	private AccountRepositoryImpl sut;

	@Test
	void findById_return_empty() {
		final var id = UUID.randomUUID();

		when(this.mockAccountRepository.findById(id)).thenReturn(Optional.empty());

		final var result = this.sut.findById(new Account.Id(id));

		assertThat(result).isEmpty();
	}

	@Test
	void findById_return_data() {
		final var id = UUID.randomUUID();
		final var account = new Account(new Account.Id(id), new Account.Iban("ES91 2100 0418 4502 0005 1332"),
				List.of(), new Account.Balance(BigDecimal.ZERO));
		when(this.mockAccountRepository.findById(id)).thenReturn(Optional.of(account));

		final var result = this.sut.findById(new Account.Id(id));

		assertThat(result).isPresent();
	}

}
