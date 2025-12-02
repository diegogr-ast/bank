package es.nextdigital.demo.infrastructure.repository;

import es.nextdigital.demo.domain.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Con mas tiempo esto seria un repo JPA con sus entidades mapeadas
@Component
public class MockAccountRepository {

	private static final UUID withData = UUID.fromString("756b8db8-e829-431e-b758-5a65e426c2fd");
	private static final Account accountWithData = new Account(new Account.Id(withData),
			new Account.Iban("ES91 2100 0418 4502 0005 1332"), List.of(), new Account.Balance(BigDecimal.ZERO));

	public Optional<Account> findById(@NonNull UUID id) {
		if (id.equals(withData)) {
			return Optional.of(accountWithData);
		}
		return Optional.empty();
	}
}
