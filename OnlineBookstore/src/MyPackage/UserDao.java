package MyPackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserDao {

	public void saveUser(Users user){

		Connection conn = ConnectDB.getConnection();

		String sql = "insert into users(uid,password,fullname,email) values(?,?,?,?)";		
		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
		
			ps.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		ConnectDB.closeConnection(conn);
		//closeDB(conn);
	}
	
	public Users login(String uid, String password){
		Users user= null;
		Connection conn = ConnectDB.getConnection();
		String sql = "select * from users where uid = ? and password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				user = new Users();
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setFullname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setState(true);
			}
			rs.close();	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ConnectDB.closeConnection(conn);
		HashMap<String, Integer> h = getCartList(uid);
		for(String k : h.keySet())
		{
			Integer i = h.get(k);
		    user.addBook(k, i);
		}
		return user;
	}
	
	
	
	public HashMap<String, Integer> getCartList(String uid){
		Connection conn = ConnectDB.getConnection();
		String list = "select * from cart where uid = ?";
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		try{
		PreparedStatement ps3 = conn.prepareStatement(list);
		ps3.setString(1, uid.trim());
		ResultSet rs3 = ps3.executeQuery();
		
		while(rs3.next()){
			h.put(rs3.getString(2), rs3.getInt(3));	
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		ConnectDB.closeConnection(conn);
		return h;
	
		}

	public boolean userIsExist(String uid){

		Connection conn = ConnectDB.getConnection();
		String sql = "select * from users where uid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				return true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectDB.closeConnection(conn);
		//closeDB(conn);
		return false; 
	}	
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	public List<Books> queryBook(String topic, String isbn, String bookname, String lowprice, String highprice, String orderby) {	
		
		List<Books> books = null;
		String sql;
		Connection conn = ConnectDB.getConnection();
		String od;
		
		if(orderby=="lowtohigh"){
			od =" order by price desc";
		}
		else{
			od=" order by price";
		}
		
		if(isNumeric(lowprice) && isNumeric(highprice))
				{
			double lowp =Double.parseDouble(lowprice);
			double highp =Double.parseDouble(highprice);
				sql="select * from books where ISBN like '%" + isbn + "%' and bookname like " + "'%" + bookname + 
						"%' and topic like " + "'%" + topic + "%' and price between "+ lowp + " and "+highp + od;
			
		}
		else if(isNumeric(lowprice) && !isNumeric(highprice)){
			double lowp =Double.parseDouble(lowprice);
			sql="select * from books where ISBN like '%" + isbn + "%' and bookname like " + "'%" + bookname + 
					"%' and topic like " + "'%" + topic + "%' and price > "+ lowp + od;
		}
		else if(!isNumeric(lowprice) && isNumeric(highprice)){
			double highp =Double.parseDouble(highprice);
			sql="select * from books where ISBN like '%" + isbn + "%' and bookname like " + "'%" + bookname + 
					"%' and topic like " + "'%" + topic + "%' and price < "+ highp + od;
		}
		else{
			sql="select * from books where ISBN like '%" + isbn + "%' and bookname like " + "'%" + bookname 
					+ "%' and topic like " + "'%" + topic + "%'" +od;
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			books = new ArrayList<Books>();
			while(rs.next()){
				Books book = new Books();
				book.setISBN(rs.getString(1));
				book.setBookname(rs.getString(2));
				book.setAuthors(rs.getString(3));
				book.setTopic(rs.getString(4));
				book.setPublishername(rs.getString(5));
				book.setImage(rs.getString(6));
				book.setBooknumber(rs.getInt(7));
				book.setPrice(rs.getDouble(8));
				books.add(book);
			}
			rs.close();	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectDB.closeConnection(conn);
		return books;
	}
	
	public Books queryBookname(String bookname) {			
		Books book = null;
		Connection conn = ConnectDB.getConnection();
		String queryTopic = "select * from books where bookname = ?";
		try {
			book = new Books();
			PreparedStatement ps = conn.prepareStatement(queryTopic);
			ps.setString(1, bookname.trim());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){		
				book.setISBN(rs.getString(1));
				book.setBookname(rs.getString(2));
				book.setAuthors(rs.getString(3));
				book.setTopic(rs.getString(4));
				book.setPublishername(rs.getString(5));
				book.setImage(rs.getString(6));
				//book.setNeworold(rs.getBoolean(7));
				book.setBooknumber(rs.getInt(7));
				book.setPrice(rs.getDouble(8));
			}
			rs.close();	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectDB.closeConnection(conn);
		return book;
	}
	
	public String editCart(String uid, String ISBN, Integer number){
		Connection conn = ConnectDB.getConnection();
		String message;
		
		if(number ==0){
			String delete = "delete from cart where uid = ? and ISBN = ?";
			try{
				PreparedStatement ps0 = conn.prepareStatement(delete);
				ps0.setString(1, uid.trim());
				ps0.setString(2, ISBN.trim());
				ps0.executeUpdate();
				ps0.close();
				message = "delete succeed";
			}
			catch (Exception e){
				e.printStackTrace();
				message = e.toString();
			}
		}
		else{
			String update = "update cart set number = " + number;
			update = update.trim() + " where ISBN = ? and uid = ?";
			
			try{
				PreparedStatement ps = conn.prepareStatement(update);
				//ps.setInt(1, number);
				ps.setString(1, ISBN.trim());
				ps.setString(2, uid.trim());
				ps.executeUpdate();
				ps.close();
				message = "update succeed";
			}
			catch (Exception e){
				e.printStackTrace();
				message = e.toString();
			}
		}	
		return message;
	}
	
	public HashMap<String,Integer> addToCart(String uid,String ISBN){
		Connection conn = ConnectDB.getConnection();
		
		String hasBook = "select * from cart where ISBN = ? and uid = ?";
		try{
			PreparedStatement ps = conn.prepareStatement(hasBook);
			ps.setString(1, ISBN.trim());
			ps.setString(2, uid.trim());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int newnumber = rs.getInt(3) +1;
				String update1 = "update cart set number = " + newnumber;
				update1 = update1.trim() + " where ISBN = ? and uid = ?"; // =" + newnumber + " where ISBN = '" + ISBN.trim();
				System.out.println(update1);
				//update = update.trim() + "'";
				PreparedStatement ps1 = conn.prepareStatement(update1);
				ps1.setString(1,ISBN.trim());
				ps1.setString(2, uid.trim());
				ps1.executeUpdate();
				ps1.close();
			}
			else{

				String update = "insert into cart(uid,ISBN,number) values(?,?,?)";
				PreparedStatement ps2 = conn.prepareStatement(update);
				ps2.setString(1, uid.trim());
				ps2.setString(2, ISBN.trim());
				ps2.setInt(3, 1);
				ps2.executeUpdate();
				ps2.close();
			}
			rs.close();	
			ps.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return getCartList(uid);
	}
	public HashMap<String, List<Integer>> queryAdmin(){
		
		Connection conn = ConnectDB.getConnection();
		HashMap<String, List<Integer>> d = new HashMap<String, List<Integer>>();
		String topicAndTotalNumber = "select topic, count(ISBN) from books where topic in (select topic from categories) group by topic";
		String topicAndNumberInCart = " select a.topic, count(a.ISBN) from cart, (select ISBN, books.topic "
				+ "from books, categories where books.topic = categories.topic) a where cart.ISBN = a.ISBN group by a.topic";
		String numberOfUsers ="select count(uid) from users";

		try{
			PreparedStatement ps = conn.prepareStatement(topicAndTotalNumber);
			ResultSet rs = ps.executeQuery();
			List<Integer> l = new ArrayList<Integer>();
			while(rs.next()){
				List<Integer> lis =new ArrayList<Integer>();
				lis.add(rs.getInt(2));
				d.put(rs.getString(1), lis);
			}
			PreparedStatement ps1 = conn.prepareStatement(topicAndNumberInCart);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				List<Integer> li =new ArrayList<Integer>();
				li = d.get(rs1.getString(1));
				li.add(rs1.getInt(2));
				d.put(rs1.getString(1), li);
			}
			
			PreparedStatement ps2 = conn.prepareStatement(numberOfUsers);
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()){
				List<Integer> li =new ArrayList<Integer>();
				li.add(rs2.getInt(1));
				d.put("users", li);
			}
			
		}
			catch (Exception e){
				e.printStackTrace();
			}	
		return d;
		
	}
	
	public String insertBook(Books book){
		
		Connection conn = ConnectDB.getConnection();
		String d;
		String insertbook = "INSERT INTO `books`(`ISBN`, `bookname`, `authors`, `topic`, `publishername`, `image`, `booknumber`, `price`)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		
		try{
			PreparedStatement ps = conn.prepareStatement(insertbook);
			ps.setString(1, book.getISBN().trim());
			ps.setString(2, book.getBookname().trim());
			ps.setString(3, book.getAuthors().trim());
			ps.setString(4, book.getTopic().trim());
			ps.setString(5, book.getPublishername().trim());
			ps.setString(6, book.getImage().trim());
			//ps.setBoolean(7, book.getNeworold());
			ps.setInt(7, book.getBooknumber());
			ps.setDouble(8, book.getPrice());
			ps.executeUpdate();
			d="succeed";
		}
			catch (Exception e){
				e.printStackTrace();
				d = e.getMessage();
			}	
		return d;	
	}
	
	public String deleteBook(String deleteISBN){
		Connection conn = ConnectDB.getConnection();
		String d;
		String deletebook = "delete from books where ISBN=?";
		try{
			PreparedStatement ps = conn.prepareStatement(deletebook);
			ps.setString(1, deleteISBN.trim());

			ps.executeUpdate();
			d="succeed";
		}
			catch (Exception e){
				e.printStackTrace();
				d = e.getMessage();
			}	
		return d;
	}
	
	public String editBook(Books book){
		Connection conn = ConnectDB.getConnection();
		String d;
		String updatebook = "UPDATE `books` SET `ISBN`= ?,`bookname`=?,`authors`=?,"
				+ "`topic`= ?,`publishername`=?,`image`=?,`booknumber`=?,`price`=?";
		try{
			PreparedStatement ps = conn.prepareStatement(updatebook);
			ps.setString(1, book.getISBN().trim());
			ps.setString(2, book.getBookname().trim());
			ps.setString(3, book.getAuthors().trim());
			ps.setString(4, book.getTopic().trim());
			ps.setString(5, book.getPublishername().trim());
			ps.setString(6, book.getImage().trim());
			ps.setInt(7, book.getBooknumber());
			ps.setDouble(8, book.getPrice());

			ps.executeUpdate();
			d="succeed";
		}
			catch (Exception e){
				e.printStackTrace();
				d = e.getMessage();
			}	
		return d;
		
	}
	
	public String checkout(String uid, String[] newchecked){
		Connection conn = ConnectDB.getConnection();
		String d =null;
		Boolean b = false;
		String checknumber = "select books.ISBN from books,cart where cart.uid = ? and cart.ISBN = books.ISBN "
				+ "and books.ISBN = ? and books.booknumber >= cart.number";
		for(int i = 0; i < newchecked.length; i++){
		try{
			PreparedStatement ps = conn.prepareStatement(checknumber);
			ps.setString(1, uid.trim());
			ps.setString(2, newchecked[i].trim());
			ResultSet rs2 = ps.executeQuery();
			if(!rs2.next()){
				b = true;
				d= "can not check out. Some book's number in the cart is larger than that in the DB.";
			}	
		}
		catch (Exception e){
			e.printStackTrace();
			d = e.getMessage();
		}
		}
		if(!b)
		{
			String update = "delete from cart where uid=? and ISBN = ?";
			for(int i = 0; i < newchecked.length; i++){
				try{
					PreparedStatement ps = conn.prepareStatement(update);
					ps.setString(1, uid.trim());
					ps.setString(2, newchecked[i].trim());
					ps.executeUpdate();
				}
				catch (Exception e){
					e.printStackTrace();
					d = e.getMessage();
				}
			}
			if(d==null){ d="succeed"; }
			
		}
		
		return d;
	}
	
	
}
