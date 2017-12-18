package edwd.songs;

public class Main {

	private static final String PATH = "jdbc:postgresql://localhost/";
	private static final String NAME = "songsDB";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";

	public static void main(String[] args) {
		Database db = new Database(PATH, NAME, USERNAME, PASSWORD);
		if (db.connect()) {
			System.out.println("Yay! Success.");
			
			TableCreator tableCreator = new TableCreator();
			tableCreator.create(db.getConnection());
		} else {
			System.out.println("Boo! Failure.");
		}
	}
}
