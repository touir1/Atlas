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
import tn.esprit.entity.Rapport;

import tn.esprit.entity.Rubrique;
import tn.esprit.entity.User;
import tn.esprit.interfaces.IRapportService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("rapport")
@Api(value = "RapportRESTService", description = "Rapport service")
public class RapportController {
	private final static Logger logger = Logger.getLogger(ReponseController.class);

	@EJB
	private IRapportService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the rapports")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of rapports", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idUser}/{idRubrique}")
	@ApiOperation(value = "get a rapport by id")
	public Response getOne(@PathParam("idUser") long idUser, @PathParam("idRubrique") long idRubrique) {
		try {
			Rapport reponse = service.get(idUser, idRubrique);
			if (reponse != null)
				return Response.status(Status.OK).entity(reponse).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a rapport", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/RapportsBysemaineAndUser/{semaine}/{mois}/{annee}/{idUser}")
	@ApiOperation(value = "get list rapport by user & semaine ")
	public Response getRapportsBysemaineAndUser(@PathParam("semaine") int semaine, @PathParam("mois") int mois,@PathParam("annee") int annee,@PathParam("idUser") long idUser) {
		try {
			List<Rapport> reponses = service.getRapportByuser(semaine, mois, annee, idUser);
			return Response.status(Status.OK).entity(reponses).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a rapport", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rapportAll/{idUser}/{semaine}")
	@ApiOperation(value = "get list rapport by user & semaine ")
	public Response getAllRapportsByUser(@PathParam("semaine") int semaine,@PathParam("idUser") long idUser) {
		try {
			List<Rapport> reponses = service.getAllRapportByUser(idUser, semaine);
			return Response.status(Status.OK).entity(reponses).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a rapport", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a rapport to the database")
	public Response add(Rapport entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a rapport", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a rapport")
	public Response update(Rapport entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a rapport", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{idUser}/{idRubrique}")
	@ApiOperation(value = "deletes a rapport from the database")
	public Response delete(@PathParam("idUser") long idUser, @PathParam("idRubrique") long idRubrique) {
		try {
			service.remove(new Rapport(new User(idUser), new Rubrique(idRubrique)));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a rapport", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	@GET
	@Secured
	@Path("delete/{idUser}/{idRubrique}/{semaine}/{annee}")
	@ApiOperation(value = "deletes a rapport from the database")
	public Response delete(@PathParam("idUser") long idUser, @PathParam("idRubrique") long idRubrique,
			@PathParam("semaine") int semaine,
			@PathParam("annee") int annee) {
		try {
			Rapport rapport = new Rapport();
			rapport.setUser(new User(idUser));
			rapport.setRubrique(new Rubrique(idRubrique));
			rapport.setAnnee(annee);
			rapport.setSemaine(semaine);
			service.deleteRapport(rapport);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a rapport", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idUser}/{idRubrique}")
	@ApiOperation(value = "Approve rapport")
	public Response validerRapport(@PathParam("idUser") long idUser, @PathParam("idRubrique") long idRubrique) {
		try {
			 service.validerRapport(idUser, idRubrique);
			 return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to approve a rapport", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
