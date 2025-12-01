package es.nextdigital.demo.domain.validator;

import java.math.BigInteger;

public class IbanValidator {
	private IbanValidator() {
	}

	public static boolean isValidIban(String iban) {

		final String cleanIban = iban.replaceAll("\\s+", "").toUpperCase();

		if (cleanIban.length() < 15 || cleanIban.length() > 34) {
			return false;
		}

		final String rearranged = cleanIban.substring(4) + cleanIban.substring(0, 4);

		final StringBuilder numericIban = new StringBuilder();
		for (final char c : rearranged.toCharArray()) {
			if (Character.isDigit(c)) {
				numericIban.append(c);
			} else if (Character.isLetter(c)) {
				numericIban.append(c - 'A' + 10);
			} else {
				return false;
			}
		}

		final BigInteger ibanNumber = new BigInteger(numericIban.toString());
		return ibanNumber.mod(BigInteger.valueOf(97)).intValue() == 1;
	}
}
