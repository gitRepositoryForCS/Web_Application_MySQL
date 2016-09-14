import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.sql.SQLException;

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

	private String _actor_sql = "SELECT ai.actor_name FROM actor_ids ai, actor_movies am WHERE ai.actor_id = am.actor_id and am.movie_id = ?";
	private PreparedStatement _actor_statement;
	
	private String _fastsearch_sql = "select actor.movie_id, movie_producer.movie_name, movie_producer.year, movie_producer.rating, movie_producer.producer_name, actor.actor_name "
							+"from (select movie.movie_id, movie.movie_name, movie.year, movie.rating, producer.producer_id, producer.producer_name from "
			+"(select movie_id,movie_name, year, rating from movie_info WHERE movie_name like ? ORDER BY movie_id) movie inner join "
			+"(SELECT m.movie_id, y.* from (select movie_id, movie_name, year, rating from movie_info WHERE movie_name like ? ORDER BY movie_id) m, "
			+"producer_movies x, producer_ids y WHERE m.movie_id = x.movie_id and x.producer_id = y.producer_id order by m.movie_id ) producer "
			+"on movie.movie_id =producer.movie_id) movie_producer inner join "
			+"(SELECT m.movie_id, ai.actor_name FROM (select movie_id, movie_name, year, rating from movie_info WHERE movie_name like ? ORDER BY movie_id) "
			+"m, actor_ids ai, actor_movies am WHERE ai.actor_id = am.actor_id and am.movie_id = m.movie_id order by m.movie_id) actor "
			+"on movie_producer.movie_id = actor.movie_id";
	private PreparedStatement _fastsearch_statement;
	
	// uncomment, and edit, after your create your own customer database
	private String _customer_login_sql = "SELECT * FROM customer WHERE login = ? and password = ?";
	private PreparedStatement _customer_login_statement;

	private String _begin_transaction_read_write_sql = "START TRANSACTION";
	private PreparedStatement _begin_transaction_read_write_statement;

	private String _commit_transaction_sql = "COMMIT";
	private PreparedStatement _commit_transaction_statement;

	private String _rollback_transaction_sql = "ROLLBACK";
	private PreparedStatement _rollback_transaction_statement;
	
	private String _maxNo_sql = "select p.maxNo, p.fee from customer c, plan p where c.pid = p.pid and c.cid=?";
	private PreparedStatement _maxNo_statement;
	
	private String _No_used_sql = "select count(*) from rental r where r.cid = ? and r.status = 'open'";
	private PreparedStatement _No_used_statement;
	
	private String _has_rent_sql = "select * from rental r where r.movie_id = ? and r.cid = ? and r.status = 'open'";
	private PreparedStatement _has_rent_statement;
	
	private String _who_has_this_movie_sql = "select r.cid from rental r where movie_id = ? and status = 'open'";
	private PreparedStatement _who_has_this_movie_statement;
	
	private String _update_plan_sql = "UPDATE customer SET pid = ? where cid = ?";
	private PreparedStatement _update_plan_statement;
	
	private String _print_plans_sql = "select * from plan";
	private PreparedStatement _print_plan_statement;
	
	private String _rent_movie_sql = "insert into rental values ( ? , ?, 'open', ?)";
	private PreparedStatement _rent_movie_statement;
	
	private String _time_of_rent_sql = "select max(time) from rental where cid = ?";
	private PreparedStatement _time_of_rent_statement;
	
	private String _check_returned_sql = "select r.status, r.time from rental r where r.cid = ? and r.movie_id = ?";
	private PreparedStatement _check_returned_statement;  //if open,could return; if closed, already returned it.
															//if null, you did not rent that movie.
	
	private String _update_return_status_sql = "update rental set status = 'closed' where cid = ? and movie_id = ?";
	private PreparedStatement _update_return_status_statement;
	
	private String _customer_plan_sql ="select pid from customer where cid=?";
	private PreparedStatement _customer_plan_statement;
	
	private String _customer_name_sql= "select first_name, last_name from customer where cid =?";
	private PreparedStatement _customer_name_statement;
	
	private String _check_movie_id_sql = "select * from movie_info where movie_id=?";
	private PreparedStatement _check_movie_id_statement;
	
	private String _max_time_in_rental_sql = "select max(time) from rental";
	private PreparedStatement _max_time_in_rental_statement;
	
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
		
		_customer_login_statement = _mydb.prepareStatement(_customer_login_sql);
		_begin_transaction_read_write_statement = _mydb.prepareStatement(_begin_transaction_read_write_sql);
		_commit_transaction_statement = _mydb.prepareStatement(_commit_transaction_sql);
		_rollback_transaction_statement = _mydb.prepareStatement(_rollback_transaction_sql);

		/* add here more prepare statements for all the other queries you need */
		/* . . . . . . */
		_actor_statement = _mydb.prepareStatement(_actor_sql);
		_maxNo_statement = _mydb.prepareStatement(_maxNo_sql);
		_No_used_statement = _mydb.prepareStatement(_No_used_sql);
		_has_rent_statement = _mydb.prepareStatement(_has_rent_sql);
		_who_has_this_movie_statement = _mydb.prepareStatement(_who_has_this_movie_sql);
		_update_plan_statement = _mydb.prepareStatement(_update_plan_sql);
		 _print_plan_statement = _mydb.prepareStatement(_print_plans_sql);
		 _rent_movie_statement= _mydb.prepareStatement(_rent_movie_sql);
		 _time_of_rent_statement= _mydb.prepareStatement(_time_of_rent_sql);
		 _check_returned_statement= _mydb.prepareStatement(_check_returned_sql);
		 _update_return_status_statement = _mydb.prepareStatement(_update_return_status_sql);
		 _customer_plan_statement = _mydb.prepareStatement(_customer_plan_sql);
		 _begin_transaction_read_write_statement= _mydb.prepareStatement(_begin_transaction_read_write_sql);
		 _commit_transaction_statement = _mydb.prepareStatement(_commit_transaction_sql);
		 _rollback_transaction_statement = _mydb.prepareStatement(_rollback_transaction_sql);
		 _fastsearch_statement= _mydb.prepareStatement(_fastsearch_sql);
		 _customer_name_statement = _mydb.prepareStatement(_customer_name_sql);
		 _customer_plan_statement = _mydb.prepareStatement(_customer_plan_sql);
		 _check_movie_id_statement = _mydb.prepareStatement(_check_movie_id_sql);
		 _max_time_in_rental_statement = _mydb.prepareStatement(_max_time_in_rental_sql);
	}


    /**********************************************************/
    /* suggested helper functions  */

	public int helper_compute_remaining_rentals(int cid) throws Exception {
		/* how many movies can she/he still rent ? */
		/* you have to compute and return the difference between the customer's plan
		   and the count of outstanding rentals */
		//return (99);
		_maxNo_statement.clearParameters();
		_maxNo_statement.setInt(1, cid);
		ResultSet maxNo_set = _maxNo_statement.executeQuery();
		int maxNo=0;
		while (maxNo_set.next()){
			maxNo = maxNo_set.getInt(1);
		}
		maxNo_set.close();
		
		_No_used_statement.clearParameters();
		_No_used_statement.setInt(1,cid);
		ResultSet No_used_set = _No_used_statement.executeQuery();
		int No_used=0;
		while (No_used_set.next()){
			No_used = No_used_set.getInt(1);
		}
		No_used_set.close();
		return maxNo - No_used;
		
	}
	
	public void helper_check_rent(int cid, String movie_id) throws Exception {
		
		int remain = helper_compute_remaining_rentals(cid);
		boolean has_rent= helper_check_has_rent(cid, movie_id);
		int who_has_this_movie = helper_who_has_this_movie(movie_id);
		
		if(has_rent){
			System.out.println("Availability status: You have rend it"+"\n");
		}
		else{
			
			if( remain >=1 && who_has_this_movie < 0){
				System.out.println("Availability status: Available"+"\n");
			}
			else{
				System.out.println("Availability status: Unavailable"+"\n");
			}
		}
		
	}
	
	public boolean helper_check_has_rent(int cid, String movie_id) throws Exception{
			_has_rent_statement.clearParameters();
			_has_rent_statement.setString(1,movie_id);
			_has_rent_statement.setInt(2, cid);
			boolean has_rent=false;
			ResultSet has_rent_set = _has_rent_statement.executeQuery();
			while (has_rent_set.next()){
				has_rent = true;
			}
			has_rent_set.close();
			return has_rent;
	}

	public String helper_compute_customer_name(int cid) throws Exception {
		/* you find  the name of the current customer */
		_customer_name_statement.clearParameters();
		_customer_name_statement.setInt(1, cid);
		String name="";
		ResultSet customer_name_set = _customer_name_statement.executeQuery();
		while (customer_name_set.next()){
			name += customer_name_set.getString(1) + " "+ customer_name_set.getString(2);
		}
		customer_name_set.close();
		return name;

	}

	public boolean helper_check_plan(int plan_id) throws Exception {
		/* is plan_id a valid plan id?  you have to figure out */
			if(plan_id==1||plan_id==2||plan_id==3||  plan_id ==4)
			{
				return true;
			}
		    return false;
	}

	public boolean helper_check_movie(String movie_id) throws Exception {
		/* is movie_id a valid movie id? you have to figure out  */
		_check_movie_id_statement.clearParameters();
		_check_movie_id_statement.setString(1,movie_id);
		ResultSet check_movie_id_set = _check_movie_id_statement.executeQuery();
		while (check_movie_id_set.next()){
			return true;
		}
		check_movie_id_set.close();
		return false;
	}

	private int helper_who_has_this_movie(String movie_id) throws Exception {
		/* find the customer id (cid) of whoever currently rents the movie movie_id; return -1 if none */
		_who_has_this_movie_statement.clearParameters();
		_who_has_this_movie_statement.setString(1,movie_id);
		ResultSet who_has_this_set = _who_has_this_movie_statement.executeQuery();
		int who_has_this_movie =-1;
		while (who_has_this_set.next()){
			who_has_this_movie= who_has_this_set.getInt(1);
		}
		who_has_this_set.close();
		return who_has_this_movie;
	}

    /**********************************************************/
    /* login transaction: invoked only once, when the app is started  */
	public int transaction_login(String name, String password) throws Exception {
		/* authenticates the user, and returns the user id, or -1 if authentication fails */

		/* Uncomment after you create your own customers database */
		int cid;

		_customer_login_statement.clearParameters();
		_customer_login_statement.setString(1,name);
		_customer_login_statement.setString(2,password);
	    ResultSet cid_set = _customer_login_statement.executeQuery();
	    if (cid_set.next()) cid = cid_set.getInt(1);
		else cid = -1;
		return(cid);
		//return (55); //comment after you create your own customers database
	}

	public void transaction_personal_data(int cid) throws Exception {
		/* println the customer's personal data: name and number of remaining rentals */
		int remain = helper_compute_remaining_rentals(cid);
		String name = helper_compute_customer_name(cid);
		System.out.println("\n Name: "+ name + "       Number of remaining Rentals: " + remain);
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
			System.out.println("Producer name: " + producer_set.getString(2));
		}
		producer_set.close();
		
		/* now you need to retrieve the actors, in the same manner */
		_actor_statement.clearParameters();
		_actor_statement.setString(1,movie_id);
		ResultSet actor_set = _actor_statement.executeQuery();
		while (actor_set.next()){
			System.out.println("Actor name: " + actor_set.getString(1));
		}
		actor_set.close();
			
		/* then you have to find the status: of "AVAILABLE" "YOU HAVE IT", "UNAVAILABLE" */
		
		//int helper_compute_remaining_rentals(int cid)
	    //private String _maxNo_sql = "select p.maxNo from customer c, plan p where c.rental_plan=plan.name";
		//private String _No_used_sql
	//	int remain = helper_compute_remaining_rentals(cid);
		
		helper_check_rent(cid, movie_id);
		
		}
		System.out.println();
	}

	public void transaction_choose_plan(int cid, int pid) throws Exception {
	    /* updates the customer's plan to pid: UPDATE customer SET plid = pid */
	    /* remember to enforce consistency ! */
		try{
		_begin_transaction_read_write_statement.executeUpdate();
		
		_customer_plan_statement.clearParameters();
		_customer_plan_statement.setInt(1, cid);
		ResultSet customer_plan_set = _customer_plan_statement.executeQuery();
		int current_pid= -1;
		while (customer_plan_set.next()){
			if (pid > customer_plan_set.getInt(1)){
				current_pid = pid;
				}
			}

		if (current_pid != -1){
			_update_plan_statement.clearParameters();
			_update_plan_statement.setInt(1, pid);
			_update_plan_statement.setInt(2, cid);
			_update_plan_statement.executeUpdate();
			System.out.println("Succeed.");
		}
		else{
			System.out.println("Failed. It's not a valid plan.");
		}			
		_commit_transaction_statement.executeUpdate();
		
		} catch(SQLException e){
			_rollback_transaction_statement.executeUpdate();
		}
	}

	public void transaction_list_plans() throws Exception {
	    /* println all available plans: SELECT * FROM plan */
		_print_plan_statement.clearParameters();
		ResultSet print_plan_set = _print_plan_statement.executeQuery();
		while (print_plan_set.next()){
			System.out.println("\n" +"id= "+ print_plan_set.getInt(1) + "  name:" + print_plan_set.getString(2) + 
					"   max Number of rent: " + print_plan_set.getInt(3) + "   fee:  $" + print_plan_set.getInt(4));
		}
		print_plan_set.close();
	}

	public void transaction_rent(int cid, String movie_id) throws Exception {
	    /* rend the movie movie_id to the customer cid */
	    /* remember to enforce consistency ! */
		try{
		_begin_transaction_read_write_statement.executeQuery();		

		if(!helper_check_movie(movie_id)){
			System.out.println("This is not a valid movie id.");
			return;
		}
		int remain = helper_compute_remaining_rentals(cid);
		boolean has_rent= helper_check_has_rent(cid, movie_id);
		int who_has_this_movie = helper_who_has_this_movie(movie_id);
		if(has_rent){
			System.out.println("Availability status: You have rend it already."+"\n");
		}
		else{
			
			if( remain >=1 && who_has_this_movie < 0){
				
				_time_of_rent_statement.clearParameters();
				_time_of_rent_statement.setInt(1, cid);
				ResultSet time_of_rent_statement = _time_of_rent_statement.executeQuery();
				int time_of_rent = 0;
				while (time_of_rent_statement.next()) {
					time_of_rent = time_of_rent_statement.getInt(1);
				}
				time_of_rent_statement.close();
				if(time_of_rent == 0){
					_rent_movie_statement.clearParameters();
					_rent_movie_statement.setInt(1, cid);
					_rent_movie_statement.setString(2, movie_id);
					_rent_movie_statement.setInt(3, 1);
					_rent_movie_statement.executeUpdate();
				}
				else{
					_rent_movie_statement.clearParameters();
					_rent_movie_statement.setInt(1, cid);
					_rent_movie_statement.setString(2, movie_id);
					_rent_movie_statement.setInt(3, 1 + time_of_rent);
					_rent_movie_statement.executeUpdate();
				}
				System.out.println("Availability status: Succeed."+"\n");
			}
			else if(remain == 0){
				System.out.println("Availability status: You have reached the maxmum number of movies you can rent."+"\n");
			}
			else{
				System.out.println("Availability status: Unavailable. Someone has rend it."+"\n");
			}
		
			_commit_transaction_statement.executeUpdate();
		}
		} catch(SQLException e){
			_rollback_transaction_statement.executeUpdate();
		}
		/* private String _rent_movie_sql = "insert into rental values ( ? , ?, 'open', ?)";
		   private PreparedStatement _rent_movie_statement;
		   private String _time_of_rent_sql = "select max(time) from rental where cid = ?";
		   private PreparedStatement _time_of_rent_statement;*/
	}

	public void transaction_return(int cid, String movie_id) throws Exception {
	    /* return the movie_id by the customer cid 
	     * 
	     * private String _check_returned_sql = "select r.status from rental r where r.cid = ? and r.movie_id = ?";
	       private PreparedStatement _check_returned_statement;  //if open,could return; if closed, already returned it.
															     //if null, you did not rent that movie.
	     *private String _update_return_status_sql = "update rental set status = 'closed' where cid = ? and movie_id = ?";
			private PreparedStatement _update_return_status_statement;
	     * */
		try{
		_begin_transaction_read_write_statement.executeQuery();		

		if(!helper_check_movie(movie_id)){
			System.out.println("This is not a valid movie id.");
			return;
		}
		int max_time =0;
		_max_time_in_rental_statement.clearParameters();
		ResultSet max_time_in_rental_set = _max_time_in_rental_statement.executeQuery();
		while (max_time_in_rental_set.next()){
			max_time = max_time_in_rental_set.getInt(1);
		}
		max_time_in_rental_set.close();
		_check_returned_statement.clearParameters();
		_check_returned_statement.setInt(1, cid);
		_check_returned_statement.setString(2, movie_id);
		ResultSet check_returned_set = _check_returned_statement.executeQuery();
		boolean b = check_returned_set.next();
		if(!b){
			System.out.println("You did not rent it.");
		}
		while (b) {
			if( check_returned_set.getString(1).equals("open")){
				_update_return_status_statement.clearParameters();
				_update_return_status_statement.setInt(1, cid);
				_update_return_status_statement.setString(2, movie_id);
				_update_return_status_statement.executeUpdate();
				System.out.println("You successfully returned it.");
				b = false;
			}
			else if (check_returned_set.getString(1).equals("closed") && check_returned_set.getInt(2) == max_time){
				System.out.println("You have already returned it before."+"\n");
				b = false;
			}
			check_returned_set.next();
		}
		check_returned_set.close();
		
		_commit_transaction_statement.executeUpdate();
		
		} catch(SQLException e){
			_rollback_transaction_statement.executeUpdate();
		}
		
	}

	public void transaction_fast_search(int cid, String movie_name) throws Exception {
		/* like transaction_search, but uses joins instead of dependent joins
		   Needs to run three SQL queries: (a) movies, (b) movies join producers, (c) movies join actors
		   Answers are sorted by movie_id.  Then merge-joins the three answer sets */
		_fastsearch_statement.clearParameters();
		_fastsearch_statement.setString(1, '%' + movie_name + '%');
		_fastsearch_statement.setString(2, '%' + movie_name + '%');
		_fastsearch_statement.setString(3, '%' + movie_name + '%');
		ResultSet fastsearch_set = _fastsearch_statement.executeQuery();
		String previous_id = "";
		String previous_producer_name="";
		String previous_actor_name = "";
		String producer="";
		String actor="";
		while (fastsearch_set.next()) {
			String current_id =fastsearch_set.getString(1);
			String current_producer_name =fastsearch_set.getString(5);
			String current_actor_name =fastsearch_set.getString(6);
			if(!current_id.equals(previous_id)){
				if(producer!=""){
			    	System.out.println(producer);
			    	producer = "";
			    }
			    if(actor!=""){
			    	System.out.println(actor);
			    	actor="";
			    }
				System.out.println("ID: " + current_id + "  NAME: " + fastsearch_set.getString(2) + "  YEAR: "+ fastsearch_set.getString(3) +"  RATING: " + fastsearch_set.getString(4) );
				previous_id = current_id;
			}
			if(!current_producer_name.equals(previous_producer_name)){
				if(!producer.contains(current_producer_name)){
				producer += "Producer name: " + current_producer_name +"\n"; //System.out.println("Producer name: " + current_producer_name);	
				previous_producer_name = current_producer_name; 
				}
			}
			if(!current_actor_name.equals(previous_actor_name)){
				if(!actor.contains(current_actor_name)){
				actor += "Actor name: " + current_actor_name+"\n"; //System.out.println("Actor name: " + current_actor_name);	
				previous_actor_name = current_actor_name;
				}
			}
			}
		if(producer!=""){
	    	System.out.println(producer);
	    }
	    if(actor!=""){
	    	System.out.println(actor);
	    }
					/*+ " NAME: "
					+ fastsearch_set.getString(2) + " YEAR: "
					+ fastsearch_set.getString(3) + " RATING: "
					+ fastsearch_set.getString(4) + "\n"
					+ "Producer name: " + fastsearch_set.getString(5) + "\n"
					+ "Actor name: " + fastsearch_set.getString(6) + "\n" );*/
		fastsearch_set.close();
}
}

