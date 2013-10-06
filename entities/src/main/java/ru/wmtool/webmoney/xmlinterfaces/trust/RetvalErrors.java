package ru.wmtool.webmoney.xmlinterfaces.trust;

import java.util.HashMap;

/**
 * Коды ошибок для интерфейса управления по доверия.
 * Коды ошибок содержатся в интерфейсе Х2 http://wiki.webmoney.ru/wiki/show/Интерфейс+X2
 * @author null
 *
 */
public class RetvalErrors {
	
	private static HashMap<Integer, String> errorCodes = new HashMap<Integer, String>();
	
	static{
		errorCodes.put(-100, "общая ошибка при разборе команды. неверный формат команды");
		errorCodes.put(-110, "запросы отсылаются не с того IP адреса, который указан при регистрации данного интерфейса в Технической поддержке");
		errorCodes.put(-1,   "неверное значение поля w3s.request/reqn");
		errorCodes.put(-2,   "неверное значение поля w3s.request/sign");
		errorCodes.put(-3,   "неверное значение поля w3s.request/trans/tranid");
		errorCodes.put(-4,   "неверное значение поля w3s.request/trans/pursesrc");
		errorCodes.put(-5,   "неверное значение поля w3s.request/trans/pursedest");
		errorCodes.put(-6,   "неверное значение поля w3s.request/trans/amount");
		errorCodes.put(-7, "неверное значение поля w3s.request/trans/desc");
		errorCodes.put(-8, "слишком длинное поле w3s.request/trans/pcode");
		errorCodes.put(-9, "поле w3s.request/trans/pcode не должно быть пустым если w3s.request/trans/period > 0");
		errorCodes.put(-10, "поле w3s.request/trans/pcode должно быть пустым если w3s.request/trans/period = 0");
		errorCodes.put(-11, "неверное значение поля w3s.request/trans/wminvid");
		errorCodes.put(-12, "идентификатор переданный в поле w3s.request/wmid не зарегистрирован");
		errorCodes.put(-14, "проверка подписи не прошла");
		errorCodes.put(-15, "неверное значение поля w3s.request/wmid");
		errorCodes.put(102, "не выполнено условие постоянного увеличения значения параметра w3s.request/reqn");
		errorCodes.put(103, "транзакция с таким значением поля w3s.request/trans/tranid уже выполнялась");
		errorCodes.put(110, "нет доступа к интерфейсу");
		errorCodes.put(111, "попытка перевода с кошелька не принадлежащего WMID, которым подписывается запрос; при этом доверие не установлено.");
		errorCodes.put(4, "внутренняя ошибка при создании транзакции");
		errorCodes.put(15, "внутренняя ошибка при создании транзакции");
		errorCodes.put(19, "внутренняя ошибка при создании транзакции");
		errorCodes.put(23, "внутренняя ошибка при создании транзакции");
		errorCodes.put(5, "идентификатор отправителя не найден");
		errorCodes.put(6, "корреспондент не найден");
		errorCodes.put(7, "кошелек получателя не найден");
		errorCodes.put(11, "кошелек отправителя не найден");
		errorCodes.put(13, "сумма транзакции должна быть больше нуля");
		errorCodes.put(17, "недостаточно денег в кошельке для выполнения операции");
		errorCodes.put(21, "счет, по которому совершается оплата не найден");
		errorCodes.put(22, "по указанному счету оплата с протекцией не возможна");
		errorCodes.put(25, "время действия оплачиваемого счета закончилось");
		errorCodes.put(26, "в операции должны участвовать разные кошельки");
		errorCodes.put(29, "типы кошельков отличаются");
		errorCodes.put(30, "кошелек не поддерживает прямой перевод");
		errorCodes.put(35, "плательщик не авторизован корреспондентом для выполнения данной операции");
		errorCodes.put(58, "превышен лимит средств на кошельках получателя");		
	}
	
	public static String getStatus(Integer errNumber){
		return errorCodes.get(errNumber);
	}

}
