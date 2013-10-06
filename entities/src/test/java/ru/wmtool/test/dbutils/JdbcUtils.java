package ru.wmtool.test.dbutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Класс для доступа к базе данных через jdbc, используя ini=файл.
 * 
 * @author null (xrw.null@gmail.com)
 * 
 */
public class JdbcUtils {
	private Connection connection;
	private Properties jdbcProps;

	public JdbcUtils() throws Throwable {
		init();
		Class.forName(jdbcProps.getProperty("driver"));
		try {
			connection = DriverManager.getConnection(
					jdbcProps.getProperty("url"), jdbcProps);
		} catch (SQLException e) {
			throw new Exception("Ошибка при создании jdbc соединения."
					+ e.getStackTrace());
		}
	}

	/**
	 * Ищем jdbc файл со свойствами соединения с базой данных
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void init() throws FileNotFoundException, IOException {
		jdbcProps = new Properties();
		jdbcProps.load(new FileInputStream(
				"src\\test\\resources\\META-INF\\jdbc.properties"));
	}

	/**
	 * Выполнить sql запрос
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public ResultSet selectQuery(String sqlQuery) throws Exception {
		Statement statement;

		statement = connection.createStatement();
		return statement.executeQuery(sqlQuery);
	}
}
