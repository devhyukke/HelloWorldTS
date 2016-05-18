package jp.ne.yukke.wts.hello.work;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class JavaTimeWorker {

    public static void main(final String[] args) {

        System.out.println("Start main.");

        final JavaTimeWorker worker = new JavaTimeWorker();
        worker.execute();

        System.out.println("End main.");
    }

    public JavaTimeWorker(final String... args) {

        // NOP
    }

    public void execute() {

        // System.out.println(LocalDate.now());
        // System.out.println(LocalTime.now());
        // System.out.println(LocalDateTime.now());
        // System.out.println(ZonedDateTime.now());
        // System.out.println(OffsetDateTime.now());

        final LocalDate lastYearsDay = LocalDate.now()
                .plusYears(1);
        lastYearsDay.atStartOfDay();

        final Date today = Calendar.getInstance().getTime();
        today.toInstant();

        LocalDateTime.now().toLocalTime();

        this.doExampleOfDateToJavaTime();
        this.doExampleOfJavaTimeToDate();
        this.doExampleOfLocalDateAndTime();
        this.doExampleOfFormattedString();
        this.doExampleOfJavaTimeAtStartOfDay();
        this.doExampleOfJavaTimeCalculation();
    }

    private void doExampleOfDateToJavaTime() {

        System.out.println("**** java.util.Date -> java.time.LocalDate");

        final Date src = Calendar.getInstance().getTime();

        // java.util.Date -> java.time.LocalDate
        final LocalDate dest1 = LocalDate.from(src.toInstant().atZone(ZoneId.systemDefault()));
        // java.util.Date -> java.time.LocalDateTime
        final LocalDateTime dest2 = LocalDateTime.from(src.toInstant().atZone(
                ZoneId.systemDefault()));
        final LocalDateTime dest3 = LocalDateTime
                .ofInstant(src.toInstant(), ZoneId.systemDefault());

        System.out.println(dest1);
        System.out.println(dest2);
        System.out.println(dest3);
    }

    private void doExampleOfJavaTimeToDate() {

        System.out.println("**** java.time.LocalDate -> java.util.Date");

        final LocalDate src1 = LocalDate.now();
        final LocalDateTime src2 = LocalDateTime.now();

        // java.time.LocalDate -> java.util.Date
        final Date dest1 = Date.from(src1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // java.time.LocalDateTime -> java.util.Date
        final Date dest2 = Date.from(src2.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(dest1);
        System.out.println(dest2);
    }

    private void doExampleOfLocalDateAndTime() {

        System.out.println("**** LocalDate <-> LocalDateTime");

        // java.time.LocalDate -> java.time.LocalDateTime
        final LocalDate src1 = LocalDate.now();
        final LocalDateTime dest1 = src1.atStartOfDay();
        // java.time.LocalDateTime -> java.time.LocalDate
        final LocalDateTime src2 = LocalDateTime.now();
        final LocalDate dest2 = src2.toLocalDate();

        System.out.println(dest1);
        System.out.println(dest2);
    }

    private void doExampleOfFormattedString() {

        System.out.println("**** LocalDate <-> String");

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // -> String
        final LocalDate src1 = LocalDate.now();
        final String dest1 = formatter.format(src1);
        // String ->
        final String src2 = "2016/04/24";
        final LocalDate dest2 = LocalDate.from(formatter.parse(src2));
        final LocalDate dest3 = LocalDate.parse(src2, formatter);

        System.out.println(dest1);
        System.out.println(dest2);
        System.out.println(dest3);
    }

    private void doExampleOfJavaTimeAtStartOfDay() {

        System.out.println("**** to date");

        final LocalDate today = LocalDate.now();
        System.out.println("Today: ".concat(today.toString()));
        System.out.println("Today at start of day: ".concat(today.atStartOfDay().toString()));
    }

    private void doExampleOfJavaTimeCalculation() {

        System.out.println("**** Java time calculation");

        // java.time.LocalDate
        final LocalDate localDate = LocalDate.now();
        final LocalDate plusLocalDate = localDate.plusYears(1L)
                .plusMonths(2L)
                .plusDays(3L);
        final LocalDate minusLocalDate = localDate.plusYears(1L)
                .minusMonths(2L)
                .minusDays(3L);
        final LocalDate withLocalDate = localDate.withYear(2015)
                .withMonth(1)
                .withDayOfMonth(2);
        // java.time.LocalDateTime
        final LocalDateTime localDateTime = LocalDateTime.now();
        final LocalDateTime plusLocalDateTime = localDateTime.plusYears(1L)
                .plusMonths(2L)
                .plusDays(3L)
                .plusHours(4L)
                .plusMinutes(5L)
                .plusSeconds(6L);
        final LocalDateTime minusLocalDateTime = localDateTime.plusYears(1L)
                .minusMonths(2L)
                .minusDays(3L)
                .minusHours(4L)
                .minusMinutes(5L)
                .minusSeconds(6L);
        final LocalDateTime withLocalDateTime = localDateTime.withYear(2015)
                .withMonth(2)
                .withDayOfMonth(3)
                .withHour(4)
                .withMinute(5)
                .withSecond(6);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println("Source LocalDate 1: ".concat(localDate.format(formatter)));
        System.out.println("Plus date: ".concat(plusLocalDate.format(formatter)));
        System.out.println("Minus date: ".concat(minusLocalDate.format(formatter)));
        System.out.println("With date: ".concat(withLocalDate.format(formatter)));
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("Source LocalDateTime: ".concat(localDateTime.format(formatter)));
        System.out.println("Plus date: ".concat(plusLocalDateTime.format(formatter)));
        System.out.println("Minus date: ".concat(minusLocalDateTime.format(formatter)));
        System.out.println("With date: ".concat(withLocalDateTime.format(formatter)));
    }
}
