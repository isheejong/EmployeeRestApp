/* Copyright © 2016 Oracle and/or its affiliates. All rights reserved. */

package com.developer.service;

import java.io.IOException;
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

import com.developer.db.EmployeeDbDeclaration;
import com.developer.entity.Employee;
import com.developer.entity.EmployeeDeclaration;


@Path("/employees")
public class EmployeeController {

    EmployeeDeclaration edao = new EmployeeDbDeclaration();

    // Get all employees
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public Employee getAll() {
    public Employee[] getAll() {
    	//System.out.println(edao.getAllEmployees().toString());
        return edao.getAllEmployees().toArray(new Employee[0]);
    	//Employee em = new Employee(1, "a", "b", "c", "d", "e", "f", "g");
    	//return em;
    }

    // Get an employee
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {

        Employee em = null;
        em = edao.getEmployee(id);
        
        if (em != null) {
            return Response.ok(em).build();
        } else {
            return Response.ok(null).build();
        }
    }

    // Get employees by lastName 
    @GET
    @Path("/lastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLastName(@PathParam("name") String name) {

        List<Employee> matchList = edao.getByLastName(name);

        if (matchList.size() > 0) {
            //return matchList.toArray(new Employee[0]);
            //return Response.ok(matchList).build();
            return Response.ok(matchList.toArray(new Employee[0])).build();
        } else {
            return Response.ok(null).build();
        }
    }

    // Get employee by title
    @GET
    @Path("/title/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitle(@PathParam("name") String name) {

        List<Employee> matchList = edao.getByTitle(name);

        if (matchList.size() > 0) {
            return Response.ok(matchList.toArray(new Employee[0])).build();
        } else {
            return Response.ok(null).build();
        }
    }

    // Get employee by dept
    @GET
    @Path("/department/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDept(@PathParam("name") String name) {

        List<Employee> matchList = edao.getByDepartment(name);

        if (matchList.size() > 0) {
            return Response.ok(matchList.toArray(new Employee[0])).build();
        } else {
            return Response.ok(null).build();
        }
    }
    
    // test
    @POST
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testA(@QueryParam("name") String name) {
    	System.out.println("test");
    	return name;
    }

    // Add an employee
    @POST
    @Path("add")
    @Consumes("application/x-www-form-urlencoded")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces("text/plain")
    public String add(MultivaluedMap<String, String> formParams) throws JsonParseException, JsonMappingException, IOException {
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
    	
        if (edao.add(employee)) {
            return "success";
        } else {
            return "fail";
        }
    }
    
    // Update an employee
    @PUT
    @Path("update/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public String update(@PathParam("id") long id, MultivaluedMap<String, String> formParams) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("### update employee ###");
    	System.out.println("### update employee: " +id +" ###");
    	String bodyContent = formParams.keySet().toString();
    	bodyContent = bodyContent.substring(1, bodyContent.length()-1);
    	System.out.println("update string: "+bodyContent);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Employee employee = mapper.readValue(bodyContent, Employee.class);

        if (edao.update(id, employee)) {
            return "update success";
        } else {
            return "update fail";
        }
    }

    // Delete a employee 
    @DELETE
    @Path("delete/{id}")
    //@Consumes("application/x-www-form-urlencoded")
    //@Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id") long id) {

        boolean result = edao.delete(id);
        System.out.println("### delete employee: " +id +" ###");
        if (result) {
            return "delete success";
        } else {
            return "delete fail";
        }
    }
    /*@DELETE
    @Path("delete")
    @Consumes("application/json")
    public String delete(MultivaluedMap<String, String> formParams) {
    	System.out.println("### delete employee ###");
    	
    	String bodyContent = formParams.keySet().toString();
    	//bodyContent = bodyContent.substring(1, bodyContent.length()-1);
    	System.out.println("delete string: "+bodyContent);
    	return "success";
    	
    }*/
}
