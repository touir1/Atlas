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
import tn.esprit.entity.Projet;
import tn.esprit.interfaces.IProjetService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("projet")
@Api(value = "ProjetRESTService", description = "Projet service")
public class ProjetController {
	private final static Logger logger = Logger.getLogger(ProjetController.class);

	@EJB
	private IProjetService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the projets")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of projets", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a projet by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Projet entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a projet", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a projet to the database")
	public Response add(Projet entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a projet", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a projet")
	public Response update(Projet entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a projet", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes a projet from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Projet(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a projet", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of project by manager")
	@Path("byManager/{idManager}")
	public Response getListProjectByManager(@PathParam("idManager")  long idUser) {
		try {
			return Response.status(Status.OK).entity(service.getProjectByManager(idUser)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of project by manager", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of members by project")
	@Path("Members/{idProject}")
	public Response getListMembersByProject(@PathParam("idProject")  long idProject) {
		try {
			return Response.status(Status.OK).entity(service.getMembreByProject(idProject)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of members by project", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Affecter user to project")
	@Path("Affecter/{idProject}/{idMembre}")
	public Response AffecterMemeber(@PathParam("idProject")Long idProject,@PathParam("idMembre") Long idMembre) {
		try {
			service.affecterUserToProject(idProject, idMembre);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a projet", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "remove all user to project")
	@Path("removeUsers/{idProject}")
	public Response RemoveAllUserFromProjet(@PathParam("idProject")Long idProject) {
		try {
			service.removeAllUserFromProjet(idProject);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a projet", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "disable project")
	@Path("cloturer/{idProject}")
	public Response CloturerProjet(@PathParam("idProject")Long idProject) {
		try {
			service.CloturerProjet(idProject);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a projet", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of project by user")
	@Path("byUser/{idUser}")
	public Response getListProjectByUser(@PathParam("idUser")  long idUser) {
		try {
			return Response.status(Status.OK).entity(service.getProjetByUser(idUser)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of project by manager", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
