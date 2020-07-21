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
import tn.esprit.entity.Absence;
import tn.esprit.interfaces.IAbsenceService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("absence")
@Api(value = "AbsenceRESTService", description = "Absence service")
public class AbsenceController {

	private final static Logger logger = Logger.getLogger(AbsenceController.class);

	@EJB
	private IAbsenceService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	@ApiOperation(value = "get the list of all the absences")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of absences", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the absences of users under a manager by status")
	@Path("byStatusForManager/{idManager}/{status}")
	public Response getListByStatusForManager(@PathParam("status") String status, @PathParam("idManager")  long idManager) {
		try {
			return Response.status(Status.OK).entity(service.getListByStatusForManager(status, idManager)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of absences", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the absences of users by status")
	@Path("byStatusForUser/{idUser}/{status}")
	public Response getListByStatusForUser(@PathParam("status") String status, @PathParam("idUser")  long idUser) {
		try {
			return Response.status(Status.OK).entity(service.getListByStatusForUser(status, idUser)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of absences", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get the absence by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Absence entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a absence", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds an absence to the database")
	public Response add(Absence entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a absence", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates an absence")
	public Response update(Absence entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a absence", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "removes an absence from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Absence(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a absence", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
