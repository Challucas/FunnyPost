package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.Modele;
import entite.Post;

public class SampleController implements Initializable{
	@FXML
    private ListView listViewPost;
	
	@FXML
    private Button buttonInit;

	
	Modele modele;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			modele = new Modele();
			initListPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	 public void initListPost() throws SQLException {
	    	ArrayList<Post> lstPost = this.modele.getPostBDD();
	    	this.listViewPost.getItems().clear();
	    	for (int i = 0; i < lstPost.size(); i++) {
	    		Post post = lstPost.get(i);
	    		if (post.getTitle() != null) {
    				this.listViewPost.getItems().add(post.getTitle());
    			}
	    	}
	    }
	 
	 public void onClickButtonInit() throws SQLException, IOException {
		 this.modele.getPostAPI();
		 initListPost();
		 
	 }

}
