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
import tn.esprit.entity.Frais;
import tn.esprit.interfaces.IFraisService;

@Path("frais")
@Api(value = "FraisRESTService", description = "Frais service")
public class FraisController {

	private final static Logger logger = Logger.getLogger(FraisController.class);

	@EJB
	private IFraisService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the frais")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of fraiss", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a frais by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Frais entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a frais", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a frais to the database")
	public Response add(Frais entity) {
		try {
			if (service.add(entity))
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a frais", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a frais")
	public Response update(Frais entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a frais", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "deletes a frais from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Frais(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a frais", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
