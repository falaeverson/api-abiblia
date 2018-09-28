package br.com.abiblia.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;

import br.com.twsoftware.alfred.object.Objeto;

@Ignore
public class EntityGenericUtil {

	public static Integer getInteger() {

		return getInteger(0, 999999);
	}

	public static Integer getInteger(Integer start, Integer end) {

		return RandomUtils.nextInt(start, end);
	}

	public static Long getLong() {

		return RandomUtils.nextLong(0, 999999);
	}

	public static Long getLong(int length) {

		long value = RandomUtils.nextLong(1111111111111111111L, 9219999999999999999L);

		return Long.parseLong(String.valueOf(value).substring(0, length));
	}

	public static Float getFloat() {

		return RandomUtils.nextFloat(0F, 999999F);
	}

	public static String getString() {

		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getString(Integer size) {

		return RandomStringUtils.randomAlphanumeric(size);

	}

	public static BigInteger getBigInteger() {

		return new BigInteger(getInteger().toString());
	}

	public static Date getDate() {

		return new Date();
	}

	public static LocalDateTime getDateTime() {

		return LocalDateTime.now();
	}

	public static BigDecimal getBigDecimal() {

		return new BigDecimal(String.valueOf(RandomUtils.nextDouble(0, 999999)));
	}

	/**
	 * Método gerador de BigDecimal de acordo com precisão e escala. Se a precisão
	 * não for informada, a escala é ignorada e é retornado um BigDecimal qualquer
	 * Se a precisão for informada, mas a escala não for um valor menor que a
	 * precisão, o valor da escala será a precisão menos uma unidade.
	 * 
	 * @param precision Quantidade de algarismos do número
	 * @param scale     Quantidade de algarismos após separador decimal do número
	 * @return O número BigDecimal gerado
	 */
	public static BigDecimal getBigDecimal(Integer precision, Integer scale) {

		if (Objeto.isBlank(precision) || precision < 1) {
			return getBigDecimal();
		}

		if (Objeto.notBlank(scale) && scale >= precision) {
			scale = precision - 1;
		}

		String randomNumeric = RandomStringUtils.randomNumeric(precision);
		return new BigDecimal(randomNumeric).divide(BigDecimal.TEN.pow(scale));

	}

	public static LocalDate getLocalDate() {

		return LocalDate.now();
	}

	public static LocalDateTime getLocalDateTime() {

		return LocalDateTime.now();
	}

	public static Boolean getBoolean() {
		return new Random().nextBoolean();
	}

	public static <T> T getEnum(Class<T> clazz) {

		int length = clazz.getEnumConstants().length;

		int nextInt = new Random().nextInt(length);

		return clazz.getEnumConstants()[nextInt];
	}

	public static Object getByType(Class<?> type) {

		if (type == Integer.class || type == int.class) {

			return getInteger();

		} else if (type == BigDecimal.class) {

			return getBigDecimal();

		} else if (type == BigInteger.class) {

			return getBigInteger();

		} else if (type == Date.class) {

			return getDate();

		} else if (type == LocalDateTime.class) {

			return getDateTime();

		} else if (type == LocalDate.class) {

			return getDateTime().toLocalDate();

		} else if (type == LocalTime.class) {

			return getDateTime().toLocalTime();

		} else if (type == Long.class) {

			return getLong();

		} else if (type == String.class) {

			return getString();

		}

		return null;

	}

}
