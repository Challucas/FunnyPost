package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import entite.Post;

public class WebService {
	
	Post post;
	ArrayList<Post> listPost = new ArrayList<>();
	
	public WebService() {
		
	}
	
	public ArrayList<Post> getPostAPI() throws SQLException, IOException {
		try {

		      URL url = new URL("https://jsonplaceholder.typicode.com/posts");
		      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		      connection.setConnectTimeout(200000);
		      connection.setReadTimeout(200000);
		      connection.setUseCaches(true);
		      connection.setRequestMethod("GET");

		      // Set Headers
		      connection.setRequestProperty("Accept", "application/xml");
		      connection.setRequestProperty("Content-Type", "application/xml");

		      int responseCode = connection.getResponseCode();
		      if(responseCode == 400) {
		          System.out.println("Client Error !!");
		      } else if (responseCode == 500) {
		          System.out.println("Server Error !!");

		      } 
		      else if (responseCode == 200) {
		    	  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		          StringBuilder responseBody = new StringBuilder();
		          String inputLine;
		          while ((inputLine = in.readLine()) != null) {
		              responseBody.append(inputLine).append("\n");
		          }
		          in.close();
		          
		          return convertJsonToObject(responseBody);
		      }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Post> convertJsonToObject(StringBuilder responseBody){
		JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(responseBody.toString());

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                this.post = new Post(Integer.parseInt(jsonObject.get("id").toString()),jsonObject.get("title").toString(), jsonObject.get("body").toString());
                listPost.add(this.post);
            }
            return listPost;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
	}
	
	
	
}
