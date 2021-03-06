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
import tn.esprit.entity.Rubrique;
import tn.esprit.interfaces.IRubriqueService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("rubrique")
@Api(value = "RubriqueRESTService", description = "Rubrique service")
public class RubriqueController {

	private final static Logger logger = Logger.getLogger(RubriqueController.class);

	@EJB
	private IRubriqueService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the rubriques")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of rubriques", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "gets a rubrique by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Rubrique entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a rubrique", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a rubrique to the database")
	public Response add(Rubrique entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a rubrique", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a rubrique")
	public Response update(Rubrique entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a rubrique", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes a rubrique from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Rubrique(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a rubrique", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of rubriques by projet")
	@Path("byProjet/{idProjet}")
	public Response getRubriqueByProjet(@PathParam("idProjet") long idProjet) {
		try {
			return Response.status(Status.OK).entity(service.getRubriqueByProjet(idProjet)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of rubriques", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
