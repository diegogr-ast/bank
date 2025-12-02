package es.nextdigital.demo.infrastructure.repository;

import es.nextdigital.demo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

// En un proyecto real se levantaria un contenedor de base de datos con TestContainer y docker-compose
// y se realizarian consultas reales contra base datos. La base de datos se popularia con datos solo para testing.
@SpringBootTest
class AccountRepositoryIT {

	@Autowired
	private AccountRepositoryImpl sut;

	@Test
	void findById_return_empty() {
		final var id = UUID.randomUUID();

		final var result = this.sut.findById(new Account.Id(id));

		assertThat(result).isEmpty();
	}

	@Test
	void findById_return_data() {
		final var id = UUID.fromString("756b8db8-e829-431e-b758-5a65e426c2fd");

		final var result = this.sut.findById(new Account.Id(id));

		assertThat(result).isPresent();
	}
}
