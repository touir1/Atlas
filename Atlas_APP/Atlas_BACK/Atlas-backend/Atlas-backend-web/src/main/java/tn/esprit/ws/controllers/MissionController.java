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
import tn.esprit.entity.Mission;
import tn.esprit.interfaces.IMissionService;

@Path("mission")
@Api(value = "MissionRESTService", description = "Mission service")
public class MissionController {

	private final static Logger logger = Logger.getLogger(MissionController.class);

	@EJB
	private IMissionService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the missions")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of missions", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a mission by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Mission entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a mission", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a mission to the database")
	public Response add(Mission entity) {
		try {
			if (service.add(entity))
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a mission", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a mission")
	public Response update(Mission entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a mission", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "deletes a mission from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Mission(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a mission", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
