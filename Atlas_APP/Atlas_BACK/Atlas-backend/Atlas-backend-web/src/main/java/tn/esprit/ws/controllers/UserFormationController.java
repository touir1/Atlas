package tn.esprit.ws.controllers;

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
import tn.esprit.entity.Formation;
import tn.esprit.entity.User;
import tn.esprit.entity.UserFormation;
import tn.esprit.interfaces.IUserFormation;

@Path("userFormation")
@Api(value = "UserFormationRESTService", description = "UserFormation service")
public class UserFormationController {
	private final static Logger logger = Logger.getLogger(UserFormationController.class);

	@EJB
	private IUserFormation service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the userFormations")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of userFormation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{iduser}/{idformation}")
	@ApiOperation(value = "gets a userFormation by id")
	public Response getOne(@PathParam("iduser") long iduser, @PathParam("idformation") long idformation) {
		try {
			UserFormation userForamtion = service.get(iduser, idformation);
			if (userForamtion != null)
				return Response.status(Status.OK).entity(userForamtion).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a userFormation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a userFormation to the database")
	public Response add(UserFormation entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a userFormation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a userFormation")
	public Response update(UserFormation entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a userFormation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("{iduser}/{idformation}")
	@ApiOperation(value = "deletes a userFormation from the database")
	public Response delete(@PathParam("iduser") long iduser, @PathParam("idformation") long idformation) {
		try {
			service.remove(new UserFormation(new Formation(idformation), new User(iduser)));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a userFormation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
