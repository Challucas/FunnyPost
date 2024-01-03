package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import entite.Post;
import service.ConnectBDD;
import service.WebService;

public class Modele {
	ConnectBDD connect;
	WebService webservice;
	
	ArrayList<Post> listPost = new ArrayList<>();
	
	public Modele() throws Exception {
		connect = new ConnectBDD();
		webservice = new WebService();

	}
	
	public ArrayList<Post> getPostBDD() throws SQLException {
		return this.connect.getPostBDD();
	}
	
	public void getPostAPI() throws SQLException, IOException {
		this.listPost = this.webservice.getPostAPI();
		for(Post post : listPost) {
			this.connect.addPostAPIToBDD(post);
		}
		
	}
}
