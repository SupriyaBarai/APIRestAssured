package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataproviders.class)
	
	public void testUserExcel(String userID, String userName, String fname, String lname, String useremail, String pwd, String phone)
	
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setLastName(lname);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response responsecreate=UserEndPoints.CreateUser(userPayload);
		System.out.println("Test"+userPayload.getUsername());
		responsecreate.then().log().all();
		Assert.assertEquals(responsecreate.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	public void testuserExcelDelete(String userName)
	{
		Response responsedelete=UserEndPoints.DeleteUser(userName);
		responsedelete.then().log().all();
		Assert.assertEquals(responsedelete.getStatusCode(), 200);
		
	}

}
