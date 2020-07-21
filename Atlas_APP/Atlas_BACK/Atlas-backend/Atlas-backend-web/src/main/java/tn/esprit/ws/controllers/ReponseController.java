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
import tn.esprit.entity.Evaluation;
import tn.esprit.entity.Question;
import tn.esprit.entity.Reponse;
import tn.esprit.interfaces.IReponseService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("reponse")
@Api(value = "ReponseRESTService", description = "Reponse service")
public class ReponseController {
	private final static Logger logger = Logger.getLogger(ReponseController.class);

	@EJB
	private IReponseService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the reponses")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of reponses", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idEvaluation}/{idQuestion}")
	@ApiOperation(value = "gets a reponse by id")
	public Response getOne(@PathParam("idEvaluation") long idEvaluation, @PathParam("idQuestion") long idQuestion) {
		try {
			Reponse reponse = service.get(idEvaluation, idQuestion);
			if (reponse != null)
				return Response.status(Status.OK).entity(reponse).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a reponse", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a reponse to the database")
	public Response add(Reponse entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a reponse", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a reponse")
	public Response update(Reponse entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a reponse", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{idEvaluation}/{idQuestion}")
	@ApiOperation(value = "deletes a reponse from the database")
	public Response delete(@PathParam("idEvaluation") long idEvaluation, @PathParam("idQuestion") long idQuestion) {
		try {
			service.remove(new Reponse(new Evaluation(idEvaluation), new Question(idQuestion)));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a reponse", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
