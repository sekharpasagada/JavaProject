package eg.com.tm.java8.features.datetime.format;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;

public class DateTimeExercises {

	public static void main(String[] args) {
		DateTimeExercises dateTimeExercises = new DateTimeExercises();
		dateTimeExercises.displayNumberOfDaysForGivenYear(2023);

		dateTimeExercises.listOfAllMondaysForGivenMonth(1);

		dateTimeExercises.displayNumberOfDaysInMonthForYear(2022);

		dateTimeExercises.listOfAllMondays(1);
		
		dateTimeExercises.verifyDayAndDate(2022, 5, 13);
	}

	public void displayNumberOfDaysForGivenYear(int year) {
		LocalDate localDate = LocalDate.of(year, 1, 1);
		for (int i = 1; i <= 12; i++) {
			localDate = localDate.withMonth(i);
			System.out.println(localDate.getMonth() + " " + localDate.lengthOfMonth());
		}

	}

	public void displayNumberOfDaysInMonthForYear(int year) {

		Year y = Year.of(year);
		for (int i = 1; i <= 12; i++) {
			System.out.println(y.atMonth(i).getMonth() + " " + y.atMonth(i).lengthOfMonth());
			;
		}
	}

	public void listOfAllMondays(int month) {
		Month m = Month.of(month);

		YearMonth yearMonth = YearMonth.of(Year.now().getValue(), m);

		LocalDate localDate = yearMonth.atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

		while (localDate.getMonthValue() == month) {

			System.out.println(localDate);

			localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}

	}

	public void listOfAllMondaysForGivenMonth(int month) {
		LocalDate localDate = LocalDate.of(2022, month, 1);
		int numberOfWeeks = localDate.lengthOfMonth() / 7;
		while (localDate.getDayOfWeek() != DayOfWeek.MONDAY) {
			localDate = localDate.plusDays(1);
		}
		System.out.println(localDate);
		for (int i = 1; i <= numberOfWeeks; i++) {
			localDate = localDate.plusWeeks(1);

			if (localDate.getMonthValue() != month)
				return;

			System.out.println(localDate);
		}

	}
	
	public void verifyDayAndDate(int year,int month,int date)
	{
		LocalDate localDate = LocalDate.of(year, month, date);
		System.out.println(localDate.query(new FridayThirteenthQuery()));
	}
	
	
}


class FridayThirteenthQuery implements TemporalQuery<Boolean>
{

	@Override
	public Boolean queryFrom(TemporalAccessor temporal) {
		
		return (temporal.get(ChronoField.DAY_OF_MONTH) == 13 && temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.FRIDAY.getValue());
	}
	
}