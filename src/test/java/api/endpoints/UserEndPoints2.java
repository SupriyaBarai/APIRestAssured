package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;


//Created for perform Create, Read, Update, Delete request of the user and create method request as per swagger doc.
public class UserEndPoints2 {

	
	static ResourceBundle getUrl()
	{
		ResourceBundle resource=ResourceBundle.getBundle("routes");
		return resource;
	}
	
	public static Response CreateUser(User payload) //Create
	{
		
	String post_url=getUrl().getString("posturl");
		
	Response res=	given().header("Content-Type","application/json").accept("application/json").body(payload)
		.when().post(post_url);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response GetUser(String Username) //Read or Retrieve
	{
		String get_url=getUrl().getString("fetchUrl");
	Response res=	given().accept("application/json").pathParam("Username", Username)
		.when().get(get_url);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response UpdateUser(String Username, User payload) //update
	{
		String put_url=getUrl().getString("putUrl");
	Response res=	given().header("Content-Type","application/json").accept("application/json").pathParam("Username", Username).body(payload)
		.when().put(put_url);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response DeleteUser(String Username)//delete
	{
		String delete_url=getUrl().getString("removeUrl");
		Response res=	given().accept("application/json").pathParam("Username", Username)
				.when().delete(delete_url);
		return res; //returning the value is nothing but a get value 
	} 
}
