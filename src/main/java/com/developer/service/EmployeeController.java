/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.developer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.developer.entity.Employee;
import com.developer.entity.EmployeeDeclaration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Path("/employees")
public class EmployeeController {
	public static final List<Employee> rl = new ArrayList<>();

	public EmployeeController() throws Exception {
		if (rl.isEmpty()) {
			this.readData();
		}
	}

	public void writeData() throws IOException {
		String json = new Gson().toJson(rl);

		FileWriter fileWriter = new FileWriter("employee.json");
		fileWriter.write(json);
		fileWriter.close();
	}

	public void readData() throws Exception {
		//BufferedReader br = new BufferedReader(new FileReader("WEB-INF\\employee.json"));

		//String line = br.readLine();
		String line = "[{\"id\":1,\"firstName\":\"Hugh\",\"lastName\":\"Jast\",\"email\":\"Hugh.Jast@example.com\",\"phone\":\"730-715-4446\",\"birthDate\":\"1970-11-28T08:28:48.078Z\",\"title\":\"National Data Strategist\",\"department\":\"Mobility\"}]";

		//br.close();
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Employee>>(){}.getType();
		Collection<Employee> en = gson.fromJson(line, collectionType);
		for (Employee e: en) {
			//System.out.println(e);
			rl.add(e);
		}
	}

	public boolean add(Employee em) throws Exception {
		rl.add(em);
		//this.writeData();
		return true;
	}

    // Get all employees
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public Employee getAll() {
    public Employee[] getAll() {
        return rl.toArray(new Employee[0]);
    }

    // Add an employee
    @POST
    @Path("add")
    @Consumes("application/x-www-form-urlencoded")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    public String add(MultivaluedMap<String, String> formParams) throws Exception {
    	System.out.println("### add employee ###");

    	//Set<String> bodyContent = formParams.keySet();
    	String bodyContent = formParams.keySet().toString();
    	bodyContent = bodyContent.substring(1, bodyContent.length()-1);
    	
    	//String firstName = String.join(" ",formParams.get("firstName"));
    	System.out.println("string: "+bodyContent);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Employee employee = mapper.readValue(bodyContent, Employee.class);
    	System.out.println("employee firstname: "+ employee.getFirstName());
    	System.out.println("employee lastname: "+ employee.getLastName());
    	
    	/*Iterator<String> iterator = bodyContent.iterator();
    	while (iterator.hasNext()){
    		String value = (String)iterator.next();
    		System.out.println("value = " + value);
    	}*/
    	
        if (this.add(employee)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
