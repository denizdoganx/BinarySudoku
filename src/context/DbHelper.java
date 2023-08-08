package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.HighScore;

public class DbHelper {
	
	
	private static DbHelper dbHelper = null;
	private static final String username = "exampleUsername";
	private static final String password = "examplePassword";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/binarysudoku";
	
	
	private DbHelper() {
		createDatabaseIfNotExists();
		createTableIfNotExists();
	}
	
	
	public static DbHelper getInstance() {
		if(dbHelper == null) {
			dbHelper = new DbHelper();
		}
		return dbHelper;
	}
	
	public boolean addScore(String name, int score) {
		Connection connection = null;
		PreparedStatement statement = null;
		int status = 0;
		try {
			connection = DriverManager.getConnection(dbUrl, username, password);
     
			statement = connection.prepareStatement("insert into scores(name_, score) values(?, ?);");
			statement.setString(1, name);
			statement.setInt(2, score);
			status = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error Code : " + e.getMessage());
		}
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error Code : " + e.getMessage());
			}
		}
		return status == 1;
	}
	
	public ArrayList<HighScore> getHighScores(){
		ArrayList<HighScore> highScores = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(dbUrl, username, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from scores order by score desc limit 5;");
			
			while(resultSet.next()) {
				highScores.add(new HighScore(resultSet.getInt("score"), resultSet.getString("name_")));
			}
			statement.close();
		}
		catch(SQLException e) {
			System.out.println("Error Code : " + e.getMessage());
		}
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error Code : " + e.getMessage());
			}
		}
		return highScores;
	}
	
	private void createTableIfNotExists() {
		
		Connection connection = null;
		Statement statement = null;
		String sql = null;
		try {
			connection = DriverManager.getConnection(dbUrl, username, password);
			sql = "create table if not exists scores("
					+ "    id INT not null auto_increment,"
					+ "    name_ VARCHAR(50),"
					+ "    score INT,"
					+ "    primary key(id)"
					+ ");";	     
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			System.out.println("Error Code : " + e.getMessage());
		}
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error Code : " + e.getMessage());
			}
		}
	}
	
	private void createDatabaseIfNotExists() {
		Connection connection = null;
		Statement statement = null;
		String sql;
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, password);
			sql = "CREATE DATABASE IF NOT EXISTS binarysudoku";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		}
		catch(SQLException e) {
			System.out.println("Error Code : " + e.getMessage());
		}
		finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error Code : " + e.getMessage());
			}
		}
	}
	
	
	

}
