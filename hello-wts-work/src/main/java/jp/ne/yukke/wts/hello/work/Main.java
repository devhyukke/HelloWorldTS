package jp.ne.yukke.wts.hello.work;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		// System.out.println(LocalDate.now());
		// System.out.println(LocalTime.now());
		// System.out.println(LocalDateTime.now());
		// System.out.println(ZonedDateTime.now());
		// System.out.println(OffsetDateTime.now());

		LocalDate lastYearsDay = LocalDate.now()
				.plusYears(1);

		Date today = Calendar.getInstance().getTime();

		exampleFromDate();
		exampleToDate();
		exampleLocalDateAndTime();
		exampleToString();
	}

	private static void exampleFromDate() {

		System.out.println("Start.");

		Date src = Calendar.getInstance().getTime();

		// java.util.Date -> java.time.LocalDate
		LocalDate dest1 = LocalDate.from(src.toInstant().atZone(ZoneId.systemDefault()));
		// java.util.Date -> java.time.LocalDateTime
		LocalDateTime dest2 = LocalDateTime.from(src.toInstant().atZone(ZoneId.systemDefault()));
		LocalDateTime dest3 = LocalDateTime.ofInstant(src.toInstant(), ZoneId.systemDefault());

		System.out.println(dest1);
		System.out.println(dest2);
		System.out.println(dest3);

		System.out.println("End.");
	}

	private static void exampleToDate() {

		System.out.println("Start.");

		LocalDate src1 = LocalDate.now();
		LocalDateTime src2 = LocalDateTime.now();

		// java.time.LocalDate -> java.util.Date
		Date dest1 = Date.from(src1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		// java.time.LocalDateTime -> java.util.Date
		Date dest2 = Date.from(src2.atZone(ZoneId.systemDefault()).toInstant());

		System.out.println(dest1);
		System.out.println(dest2);

		System.out.println("End.");
	}

	private static void exampleLocalDateAndTime() {

		System.out.println("Start.");

		// java.time.LocalDate -> java.time.LocalDateTime
		LocalDate src1 = LocalDate.now();
		LocalDateTime dest1 = src1.atStartOfDay();
		// java.time.LocalDateTime -> java.time.LocalDate
		LocalDateTime src2 = LocalDateTime.now();
		LocalDate dest2 = src2.toLocalDate();

		System.out.println(dest1);
		System.out.println(dest2);

		System.out.println("End.");
	}

	private static void exampleToString() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// -> String
		LocalDate src1 = LocalDate.now();
		String dest1 = formatter.format(src1);
		// String ->
		String src2 = "2016/04/24";
		LocalDate dest2 = LocalDate.from(formatter.parse(src2));
		LocalDate dest3 = LocalDate.parse(src2, formatter);

		System.out.println(dest1);
		System.out.println(dest2);
		System.out.println(dest3);
	}
}
