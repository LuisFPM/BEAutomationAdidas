package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;

import data.Category;
import data.Pet;
import data.Tag;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Steps {

	String baseURI = "https://petstore.swagger.io";
	String pathFindByStatus = "/v2/pet/findByStatus?status=";
	String pathPostPet = "/v2/pet";
	String pathDeletePet = "/v2/pet";
	String pathFindPetID = "/v2/pet";
	String pathUpdateExistingPet = "/v2/pet";
	Pet pet;
	Response response;
	private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(Steps.class);
	
	@Given("The PetStore server is available")
	public void the_pet_store_server_is_available() {
		LOG.info("----------------");
		LOG.info("Starting Scenario");
		LOG.info("Setting base URI");
		RestAssured.baseURI = baseURI;

	}

	@Given("There is a pet data set {string} {string} {string} {string} {string} {string} {string} {string}{string}")
	public void there_is_a_pet_data_set(String id, String categoryID, String categoryName, String name, String photoUrl1, String photoUrl2,
			String tagID, String tagName, String petStatus) {

		LOG.info("Constructing pet");
		Category category = new Category(new Integer(categoryID),categoryName);
		Tag tag = new Tag(new Integer(tagID),tagName);
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		tagList.add(tag);
		ArrayList<String> photoUrls = new ArrayList<String>();
		photoUrls.add(photoUrl1);
		photoUrls.add(photoUrl2);
		
		pet = new Pet(new Integer(id),category, name, photoUrls, tagList, petStatus);
	}

	@When("A Get request is made to find pets with status {string}")
	public void a_get_request_is_made_to_find_pets_with_status(String string) {
		LOG.info("Setting base path");
		RestAssured.basePath = pathFindByStatus;
		
		LOG.info("Recovering pets with status "+string);
		response = given().accept(ContentType.JSON).when().get(string);

	}

	@Then("A correct code response is received with a list of the available pets")
	public void a_correct_code_response_is_received_with_a_list_of_the_available_pets() {
		assertEquals(200, response.getStatusCode());
		LOG.info("Response code 200");
		// response =
		// given().accept(ContentType.JSON).when().get("sold").then().log().all().extract().response();

	}

	@When("A POST request is made to addPet")
	public void a_post_request_is_made_to_add_pet() {
		LOG.info("Setting base path");
		RestAssured.basePath = pathPostPet;
		LOG.info("Posting new pet");
		response = given().accept(ContentType.JSON).contentType(ContentType.JSON).when().body(pet).post();

	}

	@Then("A correct code response is received")
	public void a_correct_code_response_is_received() {
		assertEquals(200, response.getStatusCode());
		LOG.info("Response code 200");
	}

	@Then("The new pet is saved in the list")
	public void the_new_pet_is_saved_in_the_list() {
		RestAssured.basePath = pathFindPetID;
		
		LOG.info("Checking out existence of the pet");
		Response checkingOperation = given().accept(ContentType.JSON).when().get("/" + pet.getId());
		
		assertEquals(200, checkingOperation.getStatusCode());
		LOG.info("Pet succesfully found");
	}

	@When("A PUT request is made to updatePet")
	public void a_put_request_is_made_to_update_pet() {
		LOG.info("Setting base path");
		RestAssured.basePath = pathUpdateExistingPet;
		
		pet.setStatus("sold");
		
		LOG.info("Updating pet info");
		response = given().contentType(ContentType.JSON).when().body(pet).put();		
	}

	@Then("The pet status is updated")
	public void the_pet_status_is_updated() {
		
		RestAssured.basePath = pathFindPetID;
		
		LOG.info("Trying to recover updated pet");
		response = given().accept(ContentType.JSON).when().get("/" + pet.getId());
		
		assertEquals(200, response.getStatusCode());
		LOG.info("Asserting pet status updated");
		
		assertTrue(response.getBody().asPrettyString().contains("\"status\": \"sold\""));
		LOG.info("Pet status updated asserted");
	}

	@When("A delete request is made to deletePet")
	public void a_delete_request_is_made_to_delete_pet() {
		LOG.info("Setting base path");
		RestAssured.basePath = pathDeletePet;
		
		LOG.info("Deleting pet");
		response = given().accept(ContentType.JSON).contentType(ContentType.JSON).when().delete("/" + pet.getId());
		assertEquals(200, response.getStatusCode());
		
		}

	@Then("The pet is removed from the list")
	public void the_pet_is_removed_from_the_list() {
		RestAssured.basePath = pathFindPetID;
		
		response = given().accept(ContentType.JSON).when().get("/" + pet.getId());
		LOG.info("Trying to find pet recently deleted");
		
		assertNotEquals(200, response.getStatusCode());
		LOG.info("Pet succesfully not found");
	}

}
