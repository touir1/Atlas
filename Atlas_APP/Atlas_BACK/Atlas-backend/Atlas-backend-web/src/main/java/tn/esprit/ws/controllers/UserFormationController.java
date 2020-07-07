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

import tn.esprit.entity.Formation;
import tn.esprit.entity.User;
import tn.esprit.entity.UserFormation;
import tn.esprit.interfaces.IUserFormation;

@Path("userFormation")
public class UserFormationController {
	private final static Logger logger = Logger.getLogger(UserFormationController.class);
	@EJB
	public IUserFormation service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of users", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{iduser}/{idformation}")
	public Response getOne(@PathParam("iduser") long iduser,@PathParam("idformation") long idformation) {
		try {
			UserFormation userForamtion = service.get(iduser,idformation);
			if (userForamtion != null)
				return Response.status(Status.OK).entity(userForamtion).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a user", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(UserFormation entity) {
		try {
			if (service.add(entity))
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a user", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UserFormation entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a user", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("{iduser}/{idformation}")
	public Response delete(@PathParam("iduser") long iduser,@PathParam("idformation") long idformation) {
		try {
			service.remove(new UserFormation(new Formation(idformation), new User(iduser)));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a user", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
