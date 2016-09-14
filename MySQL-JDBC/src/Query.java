import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.FileInputStream;

/**
 * Runs queries against a back-end database
 */
public class Query {
	private static Properties configProps = new Properties();

	private static String MySqlServerDriver;
	private static String MySqlServerUrl;
    private static String MySqlServerUser;
	private static String MySqlServerPassword;

	// DB Connection
	private Connection _mydb;

	// Canned queries

	private String _search_sql = "SELECT * FROM movie_info WHERE movie_name like ? ORDER BY movie_id";
	private PreparedStatement _search_statement;

	private String _producer_id_sql = "SELECT y.* "
					 + "FROM producer_movies x, producer_ids y "
					 + "WHERE x.movie_id = ? and x.producer_id = y.producer_id";
	private PreparedStatement _producer_id_statement;

	/* uncomment, and edit, after your create your own customer database
	private String _customer_login_sql = "SELECT * FROM customer WHERE login = ? and password = ?";
	private PreparedStatement _customer_login_statement;

	private String _begin_transaction_read_write_sql = "START TRANSACTION";
	private PreparedStatement _begin_transaction_read_write_statement;

	private String _commit_transaction_sql = "COMMIT";
	private PreparedStatement _commit_transaction_statement;

	private String _rollback_transaction_sql = "ROLLBACK";
	private PreparedStatement _rollback_transaction_statement;
	 */

	public Query() {
	}

    /**********************************************************/
    /* Connection to MySQL database */

	public void openConnection() throws Exception {
		configProps.load(new FileInputStream("dbconn.config"));
		MySqlServerDriver    = configProps.getProperty("MySqlServerDriver");
		MySqlServerUrl 	   = configProps.getProperty("MySqlServerUrl");
		MySqlServerUser 	   = configProps.getProperty("MySqlServerUser");
		MySqlServerPassword  = configProps.getProperty("MySqlServerPassword");


		/* load jdbc drivers */
		Class.forName(MySqlServerDriver).newInstance();

		/* open a connection to your mySQL database that contains movie and the customer databases */
		_mydb = DriverManager.getConnection(MySqlServerUrl, // database
				MySqlServerUser, // user
				MySqlServerPassword); // password

	
	}

	public void closeConnection() throws Exception {
		_mydb.close();
	}

    /**********************************************************/
    /* prepare all the SQL statements in this method.
      "preparing" a statement is almost like compiling it.  Note
       that the parameters (with ?) are still not filled in */

	public void prepareStatements() throws Exception {

		_search_statement = _mydb.prepareStatement(_search_sql);
		_producer_id_statement = _mydb.prepareStatement(_producer_id_sql);

		/* uncomment after you create your customers database */
		/*
		_customer_login_statement = _mydb.prepareStatement(_customer_login_sql);
		_begin_transaction_read_write_statement = _mydb.prepareStatement(_begin_transaction_read_write_sql);
		_commit_transaction_statement = _mydb.prepareStatement(_commit_transaction_sql);
		_rollback_transaction_statement = _mydb.prepareStatement(_rollback_transaction_sql);
		 */

		/* add here more prepare statements for all the other queries you need */
		/* . . . . . . */
	}


    /**********************************************************/
    /* suggested helper functions  */

	public int helper_compute_remaining_rentals(int cid) throws Exception {
		/* how many movies can she/he still rent ? */
		/* you have to compute and return the difference between the customer's plan
		   and the count of outstanding rentals */
		return (99);
	}

	public String helper_compute_customer_name(int cid) throws Exception {
		/* you find  the name of the current customer */
		return ("Joe Name");

	}

	public boolean helper_check_plan(int plan_id) throws Exception {
		/* is plan_id a valid plan id?  you have to figure out */
		return true;
	}

	public boolean helper_check_movie(String movie_id) throws Exception {
		/* is movie_id a valid movie id? you have to figure out  */
		return true;
	}

	private int helper_who_has_this_movie(String movie_id) throws Exception {
		/* find the customer id (cid) of whoever currently rents the movie movie_id; return -1 if none */
		return (77);
	}

    /**********************************************************/
    /* login transaction: invoked only once, when the app is started  */
	public int transaction_login(String name, String password) throws Exception {
		/* authenticates the user, and returns the user id, or -1 if authentication fails */

		/* Uncomment after you create your own customers database */
		/*
		int cid;

		_customer_login_statement.clearParameters();
		_customer_login_statement.setString(1,name);
		_customer_login_statement.setString(2,password);
	    ResultSet cid_set = _customer_login_statement.executeQuery();
	    if (cid_set.next()) cid = cid_set.getInt(1);
		else cid = -1;
		return(cid);
		 */
		return (55); //comment after you create your own customers database
	}

	public void transaction_personal_data(int cid) throws Exception {
		/* println the customer's personal data: name and number of remaining rentals */
	}


    /**********************************************************/
    /* main functions in this project: */

	public void transaction_search(int cid, String movie_name)
			throws Exception {
		/* searches for movies with matching names: SELECT * FROM movie WHERE movie_name LIKE name */
		/* prints the movies, producers, actors, and the availability status:
		   AVAILABLE, or UNAVAILABLE, or YOU CURRENTLY RENT IT */

		/* set the first (and single) '?' parameter */
		_search_statement.clearParameters();
		_search_statement.setString(1, '%' + movie_name + '%');

		ResultSet movie_set = _search_statement.executeQuery();
		while (movie_set.next()) {
			String movie_id = movie_set.getString(1);
			System.out.println("ID: " + movie_id + " NAME: "
					+ movie_set.getString(2) + " YEAR: "
					+ movie_set.getString(3) + " RATING: "
					+ movie_set.getString(4));
			/* do a dependent join with producer */
			_producer_id_statement.clearParameters();
			_producer_id_statement.setString(1, movie_id);
			ResultSet producer_set = _producer_id_statement.executeQuery();
			while (producer_set.next()) {
				System.out.println("\t\tProducer name: " + producer_set.getString(2));
			}
			producer_set.close();
			/* now you need to retrieve the actors, in the same manner */
			/* then you have to find the status: of "AVAILABLE" "YOU HAVE IT", "UNAVAILABLE" */
		}
		System.out.println();
	}

	public void transaction_choose_plan(int cid, int pid) throws Exception {
	    /* updates the customer's plan to pid: UPDATE customer SET plid = pid */
	    /* remember to enforce consistency ! */
	}

	public void transaction_list_plans() throws Exception {
	    /* println all available plans: SELECT * FROM plan */
	}

	public void transaction_rent(int cid, String movie_id) throws Exception {
	    /* rend the movie movie_id to the customer cid */
	    /* remember to enforce consistency ! */
	}

	public void transaction_return(int cid, String movie_id) throws Exception {
	    /* return the movie_id by the customer cid */
	}

	public void transaction_fast_search(int cid, String movie_name)
			throws Exception {
		/* like transaction_search, but uses joins instead of dependent joins
		   Needs to run three SQL queries: (a) movies, (b) movies join producers, (c) movies join actors
		   Answers are sorted by movie_id.
		   Then merge-joins the three answer sets */
	}

}
