package api.test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Usertest2 {
	
	Faker faker; //created object for Faker
	User userpayload; //created object for User pojo class
	public Logger logger;//Invoke Logger
	
@BeforeClass
	public  void setup()
	{
	
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.number().hashCode());
		userpayload.setUsername(faker.name().fullName());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());//call object and invoke same class
		
	}
	
	@Test(priority=1)
	public void testcaseofCreateuser()
	{
		logger.info("***********TC01 User Create************");
		Response responsecreate=UserEndPoints2.CreateUser(userpayload);
		responsecreate.then().log().all();
		System.out.println("Test"+userpayload.getUsername());
		logger.info("***********TC01 User Created..************");
		Assert.assertEquals(responsecreate.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testcaseofGetuserbyName()
	{
		logger.info("***********TC02 User Data fetch************");
		Response responseget=UserEndPoints2.GetUser(this.userpayload.getUsername());
		responseget.then().log().all().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema.json"));
		logger.info("***********TC02 User Data fetched************");
		Assert.assertEquals(responseget.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testcaseofUpdateuserbyName()
	{
		logger.info("***********TC03 Update user************");
		//Update the payload
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());

		logger.info("***********TC03 Updated user************");
		Response responseupdate=UserEndPoints2.UpdateUser(this.userpayload.getUsername(), userpayload);
		responseupdate.then().log().all();
		Assert.assertEquals(responseupdate.getStatusCode(), 200);
		
	}
	
	
	
	@Test(priority=4)
	public void testcaseofDeleteuserbyName()
	{
		logger.info("***********TC04 Delete user************");
		Response responsedelete=UserEndPoints2.DeleteUser(this.userpayload.getUsername());
		responsedelete.then().log().all();
		logger.info("***********TC04 Deleted user************");
		Assert.assertEquals(responsedelete.getStatusCode(), 200);
		
	}

}
