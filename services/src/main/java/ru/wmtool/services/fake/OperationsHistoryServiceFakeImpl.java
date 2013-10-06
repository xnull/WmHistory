package ru.wmtool.services.fake;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Фейковая реализация OperationsHistoryService.<br/>
 * <i>!!!Импользуется только во время разработки!!!</i>
 * 
 * @author Evgin
 * 
 */
public class OperationsHistoryServiceFakeImpl implements
		OperationsHistoryService {

	private static List<WmOperation> operations = new ArrayList<WmOperation>();
	// конструктор
	static {
		operations = new ArrayList<WmOperation>();
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int id = random.nextInt(10000);
			id = id >>> 1;

			String[] purse = { "Z", "R", "U", "E", "B", "Y" };
			int moneyType = random.nextInt(6);

			long purseidsrc = (random.nextLong() >>> 1);
			purseidsrc = purseidsrc >> 24;

			String pursesrc = purse[moneyType] + String.valueOf(purseidsrc);

			long purseiddest = (random.nextLong() >>> 1);
			purseiddest = purseiddest >> 24;
			String pursedest = purse[moneyType] + purseiddest;

			float t2 = (float) (Math.random() * random.nextInt(10000));
			double amount = new BigDecimal(t2)
					.setScale(2, RoundingMode.HALF_UP).doubleValue();

			double comiss = amount / 7;

			int opertype = (random.nextInt() * 100 + random.nextInt()) >>> 1;

			int period = random.nextInt(255);

			long wminvid = (random.nextLong() >>> 1);
			wminvid = wminvid >> 24;

			int orderid = random.nextInt();
			orderid = orderid >>> 1;

			String[] str = { "Бонусы", "Возврат", "Подаяние", "Премия",
					"Оплата", "Взятка", "Зарплата", "Отчисления" };

			String desc = str[random.nextInt(8)];

			String[] years = { "1998", "1999", "2000", "2001", "2002", "2003",
					"2004", "2005", "2006", "2007", "2008", "2009", "2010",
					"2011" };
			int year = random.nextInt(14);
			// String datecrt = years[year] + "-" + random.nextInt(12) + "-"
			// + random.nextInt(31) + " " + random.nextInt(24) + ":"
			// + random.nextInt(59);

			Calendar datecrtCal = Calendar.getInstance();
			datecrtCal.set(Integer.parseInt(years[year]), random.nextInt(12),
					random.nextInt(31), random.nextInt(24), random.nextInt(59));

			// String dateupd = years[year] + "-" + random.nextInt(12) + "-"
			// + random.nextInt(31) + " " + random.nextInt(24) + ":"
			// + random.nextInt(59);

			Calendar dateupdCal = Calendar.getInstance();
			dateupdCal.set(Integer.parseInt(years[year]), random.nextInt(12),
					random.nextInt(31), random.nextInt(24), random.nextInt(59));

			long temp = (random.nextLong() >>> 1);
			long corrwm = temp >> 24;

			double rest = amount - comiss;

			operations.add(new WmOperation(id, pursesrc, pursedest, amount,
					comiss, opertype, period, wminvid, orderid, desc,
					datecrtCal, dateupdCal, corrwm, rest));
		}
	}

	@Override
	public List<WmOperation> getAllOperationForCurrentUser(Integer wmidNumber,
			String purseNumber) {
		return operations;
	}

	@Override
	public List<WmOperation> getAllOperationsForUser(String userName,
			Integer wmidNumber, String purseNumber) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, int beginIndex, int countOperations) {

		if (beginIndex < 0 || beginIndex > operations.size()
				&& countOperations < 0
				|| countOperations > operations.size() - beginIndex) {
			throw new IllegalArgumentException(
					String.format(
							"getting args: begingIndex=%s; countOperations=%s. args must be >=0",
							beginIndex, countOperations));
		}

		return operations.subList(beginIndex, beginIndex + countOperations);

	}

	@Override
	public int getAllOperationsCountForCurrentUser() {
		return operations.size();
	}

	@Override
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, Calendar beginDate, Calendar endDate) {
		return getOperationsForCurrentUser(null, null, 20, 25);
	}

}
