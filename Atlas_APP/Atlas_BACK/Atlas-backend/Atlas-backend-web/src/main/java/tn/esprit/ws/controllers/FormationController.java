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
import tn.esprit.interfaces.IFormationService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("formation")
@Api(value = "FormationRESTService", description = "Formation service")
public class FormationController {
	private final static Logger logger = Logger.getLogger(FormationController.class);

	@EJB
	private IFormationService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	@ApiOperation(value = "get the list of all the formations")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of formations", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@Secured
	@ApiOperation(value = "get a formation by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Formation entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a formation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	@ApiOperation(value = "adds a formation to the database")
	public Response add(Formation entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a formation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a formation")
	public Response update(Formation entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a formation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes a formation")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Formation(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a formation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
