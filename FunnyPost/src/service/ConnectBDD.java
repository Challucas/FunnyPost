package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entite.Post;

public class ConnectBDD {

	Connection connect;
	ArrayList<Post> listPost = new ArrayList<>();
	
	public ConnectBDD() throws Exception {
		this.connect = getConnection();
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/funnypost";
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username, password);
			System.out.println("Connected");
;			return conn;
		} catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	public ArrayList<Post> getPostBDD() throws SQLException {
		Statement st = this.connect.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM POST");
		while (rs.next()) {
			Post post = new Post(rs.getInt("id"), rs.getString("title"), rs.getString("body"));
			this.listPost.add(post);
		}
		return this.listPost;

	}
	
	public void addPostAPIToBDD(Post post) throws SQLException{
		PreparedStatement updateQuery = this.connect.prepareStatement("INSERT INTO post VALUES(?,?,?)");
		updateQuery.setInt(1, post.getId());
		updateQuery.setString(2, post.getTitle());
		updateQuery.setString(3, post.getBody());
		updateQuery.executeUpdate();
	}
}
