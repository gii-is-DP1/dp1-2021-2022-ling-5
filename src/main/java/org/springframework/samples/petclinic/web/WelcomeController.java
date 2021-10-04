package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Person;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> personas = new ArrayList<Person>();
		Person persona1 = new Person();
		persona1.setFirstName("Manuel");
		Person persona2 = new Person();
		persona2.setFirstName("David");
		Person persona3 = new Person();
		persona3.setFirstName("Marta");
		Person persona4 = new Person();
		persona4.setFirstName("Gregorio");
		Person persona5 = new Person();
		persona5.setFirstName("Irene");
		Person persona6 = new Person();
		persona6.setFirstName("Fernando");
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		personas.add(persona4);
		personas.add(persona5);
		personas.add(persona6);
		model.put("persons", personas);
		model.put("title", "PetClinic-LIng5");
		model.put("group", "LIng5");
	    return "welcome";
	  }
}
