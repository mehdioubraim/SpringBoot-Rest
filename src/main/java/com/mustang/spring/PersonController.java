package com.mustang.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	@Autowired
	private Person person;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String check() {
		return "OK";
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public Person getPerson(@RequestParam(name="name", required=false, defaultValue="Unknown") String name ) {
		person.setName(name);
		return person;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, consumes = "application/json")
	public Person update(@RequestBody Person p) {
		person.setName(p.getName());
		return person;
	}
	
	
}
