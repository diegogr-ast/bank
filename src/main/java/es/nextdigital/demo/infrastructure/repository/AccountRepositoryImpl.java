package es.nextdigital.demo.infrastructure.repository;

import es.nextdigital.demo.domain.Account;
import es.nextdigital.demo.domain.repositoy.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class AccountRepositoryImpl implements AccountRepository {

	private final MockAccountRepository mockAccountRepository;

	@Override
	public Optional<Account> findById(@NonNull final Account.Id id) {
		return this.mockAccountRepository.findById(id.value());
	}
}
