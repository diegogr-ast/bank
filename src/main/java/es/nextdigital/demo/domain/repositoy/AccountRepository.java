package es.nextdigital.demo.domain.repositoy;

import es.nextdigital.demo.domain.Account;

import java.util.Optional;

public interface AccountRepository {
	Optional<Account> findById(Account.Id id);
}
