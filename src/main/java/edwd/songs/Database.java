package edwd.songs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

	private Connection connection;
	private String path;
	private String name;
	private String username;
	private String password;

	public Database(String path, String name, String username, String password) {
		this.name = name.toLowerCase();
		this.path = path;
		this.username = username;
		this.password = password;
	}
	
	public Connection getConnection() {
		return this.connection;
	}

	public boolean connect() {
		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(path + name + "?currentSchema = " + name, username, password);
			System.out.println(name + " connected");
		} catch (Exception e) {
			System.out.println("Could not connect to database: " + path + name);
			System.out.println("Now trying to connect to the default database.");

			try {
				connection = DriverManager.getConnection(path + "postgres", username, password);
				System.out.println("Connected with the default database.");

				try {
					Statement statement = connection.createStatement();
					statement.execute("CREATE DATABASE " + name);

					connection.close();

					connection = DriverManager.getConnection(path + name, username, password);
					System.out.println("Connected with the new database.");

					statement = connection.createStatement();
					statement.execute("CREATE SCHEMA IF NOT EXISTS " + name);

					connection.setSchema(name);
					System.out.println("Database " + name + " created.");
				} catch (Exception e2) {
					System.out.println("Could not connect with new database: \n" + e2);
					return false;
				}
			} catch (Exception e3) {
				System.out.println("Could not connect with default database: \n" + e3);
				return false;
			}
		}

		try {
			Statement statement = connection.createStatement();
			statement.execute("SET search_path to " + name);
		} catch (Exception badSchema) {
			System.out.println("Failed to set schema. Exception:\n" + badSchema);
			return false;
		}

		return true;
	}
}
