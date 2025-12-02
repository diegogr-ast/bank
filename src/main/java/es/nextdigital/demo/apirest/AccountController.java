package es.nextdigital.demo.apirest;

import es.nextdigital.demo.apirest.dto.MovementDTO;
import es.nextdigital.demo.application.account.query.MovementsQuery;
import es.nextdigital.demo.application.account.query.MovementsQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

	// Con mas tiempo aqui aniadiria un cqrs en vez de query handler.
	// En vez de recibir el clientID por header, se recibiria un jwt o similar
	// con la authentication del usuario

	private final MovementsQueryHandler movementsQueryHandler;

	@GetMapping(value = "/accounts/{accountId}/movements", produces = {"application/json"})
	ResponseEntity<List<MovementDTO>> getMovements(
			@RequestHeader(value = "clientId", required = true) final UUID clientId,
			@PathVariable("accountId") final UUID accountId) {
		final var query = new MovementsQuery(accountId, clientId);
		final var movements = this.movementsQueryHandler.ask(query).stream()
				.map(mov -> new MovementDTO(mov.type().name(), mov.quantity())).toList();

		return ResponseEntity.ok(movements);
	}
}
