package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.response.Response;


//Created for perform Create, Read, Update, Delete request of the user and create method request as per swagger doc.
public class UserEndPoints {

	public static Response CreateUser(User payload) //Create
	{
	Response res=	given().header("Content-Type","application/json").accept("application/json").body(payload)
		.when().post(Routes.createUrl);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response GetUser(String Username) //Read or Retrieve
	{
	Response res=	given().accept("application/json").pathParam("Username", Username)
		.when().get(Routes.getUrl);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response UpdateUser(String Username, User payload) //update
	{
	Response res=	given().header("Content-Type","application/json").accept("application/json").pathParam("Username", Username).body(payload)
		.when().put(Routes.updateUrl);
		return res; //returning the value is nothing but a get value 
	}
	
	public static Response DeleteUser(String Username)//delete
	{
		Response res=	given().accept("application/json").pathParam("Username", Username)
				.when().delete(Routes.deleteUrl);
		return res; //returning the value is nothing but a get value 
	} 
}
