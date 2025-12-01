package es.nextdigital.demo.domain.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class IbanValidatorTest {

	@ParameterizedTest
	@ValueSource(strings = {"ES12 3456 78", // short
			"ES00 2100 0418 4502 0005", // invalid control
			"ZZ91 2100 0418 4502 0005", // invalid country
			"ESAA 2100 0418 4502 0005 1332", // invalid characters
			"ES91 2100 0418 4502 0005 9999" // check digits incorrect
	})
	void invalid_iban_should_return_false() {
		final var result = IbanValidator.isValidIban("ES12 3456 78");
		assertThat(result).isFalse();
	}

	@ParameterizedTest
	@ValueSource(strings = {"ES91 2100 0418 4502 0005 1332", "DE89 3704 0044 0532 0130 00",
			"FR76 3000 6000 0112 3456 7890 189", "GB29 NWBK 6016 1331 9268 19", "IT60 X054 2811 1010 0000 0123 456"})
	void valid_iban_should_return_true(String validIban) {
		final boolean result = IbanValidator.isValidIban(validIban);

		assertThat(result).as("El IBAN %s debería ser válido", validIban).isTrue();
	}
}
