package tn.esprit.ws.controllers;

import java.util.List;

import javax.ejb.EJB;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IUserService;

@Path("user")
@Api(value = "UserRESTService", description = "User service")
public class UserController {
	
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@EJB
	private IUserService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the users")
	public Response getAll(){
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get the list of users",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a user by it's id")
	public Response getOne(@PathParam("id") long id) {
		try {
			User user = service.get(id);
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
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "add a user to the database")
	public Response add(User entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
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
	@ApiOperation(value = "updates a user")
	public Response update(User entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to update a user",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@Path("{id}")
	@ApiOperation(value = "deletes a user from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new User(id));
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to delete a user",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("getUsersByManager/{idManager}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get users by their manager")
	public Response getUsersByManager(@PathParam("idManager") long idManager) {
		try {
			List<User> users = service.getUsersByManager(idManager);
			return Response.status(Status.OK).entity(users).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get list of users by manager",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
