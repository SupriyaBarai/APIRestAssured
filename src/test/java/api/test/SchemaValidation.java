package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class SchemaValidation {

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
		Response responsecreate=UserEndPoints.CreateUser(userpayload);
		responsecreate.then().log().all();
		System.out.println("Test"+userpayload.getUsername());
		logger.info("***********TC01 User Created..************");
		Assert.assertEquals(responsecreate.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testcaseofGetuserbyName()
	{
		logger.info("***********TC02 User Data fetch************");
		Response responseget=UserEndPoints.GetUser(this.userpayload.getUsername());
		responseget.then().log().all().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema.json"));
		logger.info("***********TC02 User Data fetched************");
		Assert.assertEquals(responseget.getStatusCode(), 200);
		
	}
}
