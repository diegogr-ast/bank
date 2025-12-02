package es.nextdigital.demo.application.account.query;

import es.nextdigital.demo.domain.Account;
import es.nextdigital.demo.domain.Account.Movement;
import es.nextdigital.demo.domain.repositoy.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MovementsQueryHandler {

	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	public List<Movement> ask(@NonNull final MovementsQuery query) {
		// con mas tiempo se deberia comprobar que el usuario tiene la cuenta indicada
		// asociada

		return this.accountRepository.findById(new Account.Id(query.accountId())).map(Account::getMovements)
				.orElseThrow(() -> new IllegalArgumentException("Account not found"));
	}
}
