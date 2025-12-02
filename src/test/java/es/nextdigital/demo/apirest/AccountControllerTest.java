package es.nextdigital.demo.apirest;

import es.nextdigital.demo.application.account.query.MovementsQuery;
import es.nextdigital.demo.application.account.query.MovementsQueryHandler;
import es.nextdigital.demo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovementsQueryHandler movementsQueryHandler;

	@Test
	void getMovements_shouldReturnEmptyListOfMovements() throws Exception {
		final var accountId = UUID.randomUUID();
		final var clientId = UUID.randomUUID();
		final var url = String.format("/api/accounts/%s/movements", accountId);
		final var query = new MovementsQuery(accountId, clientId);

		when(this.movementsQueryHandler.ask(query)).thenReturn(List.of());

		this.mockMvc.perform(get(url).header("clientId", clientId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));
	}

	@Test
	void getMovements_shouldReturnListOfMovements() throws Exception {
		final var accountId = UUID.randomUUID();
		final var clientId = UUID.randomUUID();
		final var url = String.format("/api/accounts/%s/movements", accountId);
		final var query = new MovementsQuery(accountId, clientId);
		final var movement = new Account.Movement(UUID.randomUUID(), Account.Movement.Type.FEE, LocalDateTime.now(),
				BigDecimal.TEN);

		when(this.movementsQueryHandler.ask(query)).thenReturn(List.of(movement));

		this.mockMvc.perform(get(url).header("clientId", clientId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1)).andExpect(jsonPath("$[0].type").value("FEE"))
				.andExpect(jsonPath("$[0].quantity").value(BigDecimal.TEN));
	}
}
