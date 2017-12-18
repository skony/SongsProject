package edwd.songs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

	private static final String SCRIPT = "CREATE TABLE IF NOT EXISTS PLAYBACK(" + "ID SERIAL PRIMARY KEY	NOT NULL,"
			+ "SONG_ID INT 		NOT NULL," + "ARTIST_ID INT	NOT NULL," + "DATE_ID INT		NOT NULL,"
			+ "USER_ID INT		NOT NULL" + ");" 
			
			+ "CREATE TABLE IF NOT EXISTS SONG(" + "ID INT			NOT NULL,"
			+ "TITLE CHAR(50)	NOT NULL" + ");"

			+ "CREATE TABLE IF NOT EXISTS ARTIST(" + "ID INT			NOT NULL," + "NAME CHAR(50)	NOT NULL" + ");"

			+ "CREATE TABLE IF NOT EXISTS DATE(" + "ID INT			NOT NULL," + "DATE TIMESTAMP	NOT NULL" + ");"

			+ "CREATE TABLE IF NOT EXISTS USERS(" + "ID INT			NOT NULL" + ");";

	public void create(Connection connection) {
		try {
			Statement statement = connection.createStatement();
			statement.executeQuery(SCRIPT);
			System.out.println("Tables created!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
