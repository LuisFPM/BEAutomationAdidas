Feature: Pet Shop Managing

	Scenario Outline: Visualize Available Pet List
		Given The PetStore server is available
		And There is a pet data set "<id>" "<categoryID>" "<categoryName>" "<name>" "<photoUrl1>" "<photoUrl2>" "<tagID>" "<tagName>""<petStatus>"
		When A Get request is made to find pets with status "<Status>"
		Then A correct code response is received with a list of the available pets 
			Examples:
				|Status		|id|categoryID|categoryName|name|photoUrl1|photoUrl2|tagID|tagName|petStatus|
				|available|246897531|1|category1|pet1LFPM|url1|url2|1|tag1|avaiable|	

	Scenario Outline: Add new Pet to store
		Given The PetStore server is available
		And There is a pet data set "<id>" "<categoryID>" "<categoryName>" "<name>" "<photoUrl1>" "<photoUrl2>" "<tagID>" "<tagName>""<petStatus>"
		When A POST request is made to addPet
		Then A correct code response is received
		And The new pet is saved in the list
		Examples:
				|id|categoryID|categoryName|name|photoUrl1|photoUrl2|tagID|tagName|petStatus|
				|246897531|1|category1|pet1LFPM|url1|url2|1|tag1|avaiable|	

	Scenario Outline: Update pet to sold
		Given The PetStore server is available
		And There is a pet data set "<id>" "<categoryID>" "<categoryName>" "<name>" "<photoUrl1>" "<photoUrl2>" "<tagID>" "<tagName>""<petStatus>"
		When A PUT request is made to updatePet
		Then A correct code response is received
 		And The pet status is updated
			Examples:
				|id|categoryID|categoryName|name|photoUrl1|photoUrl2|tagID|tagName|petStatus|
				|246897531|1|category1|pet1LFPM|url1|url2|1|tag1|avaiable|	

	Scenario Outline: Remove new Pet to store
		Given The PetStore server is available
		And There is a pet data set "<id>" "<categoryID>" "<categoryName>" "<name>" "<photoUrl1>" "<photoUrl2>" "<tagID>" "<tagName>""<petStatus>"
		When A delete request is made to deletePet
		Then A correct code response is received
		And The pet is removed from the list
			Examples:
				|id|categoryID|categoryName|name|photoUrl1|photoUrl2|tagID|tagName|petStatus|
				|246897531|1|category1|pet1LFPM|url1|url2|1|tag1|avaiable|	