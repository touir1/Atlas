package tn.esprit.ws.controllers;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import tn.esprit.entity.User;
import tn.esprit.interfaces.IUserService;
import tn.esprit.services.UserService;

@Path("user")
public class UserController {
	
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@EJB
	private IUserService userService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		try {
			return Response.status(Status.OK).entity(userService.getAll()).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get the list of users",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getOne(@PathParam("id") long id) {
		try {
			User user = userService.get(id);
			if(user != null)
				return Response.status(Status.OK).entity(user).build();
			return Response.status(Status.NOT_FOUND).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get a user",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(User user) {
		try {
			if(userService.add(user))
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to save a user",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User user) {
		try {
			userService.update(user);
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to update a user",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		try {
			userService.remove(new User(id));
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to delete a user",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
