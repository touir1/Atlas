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
import tn.esprit.entity.Choix;
import tn.esprit.interfaces.IChoixService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("choix")
@Api(value = "ChoixRESTService", description = "Choix service")
public class ChoixController {

	private final static Logger logger = Logger.getLogger(ChoixController.class);

	@EJB
	private IChoixService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the choix")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of choix", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a choix by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Choix entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a choix", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a choix to the database")
	public Response add(Choix entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a choix", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a choix")
	public Response update(Choix entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a choix", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes a choix from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Choix(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a choix", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of choix by question")
	@Path("Question/{idQuestion}")
	public Response getChoixByQuestion(@PathParam("idQuestion")  long idQuestion) {
		try {
			return Response.status(Status.OK).entity(service.getChoixByQuestion(idQuestion)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of choice by question", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
