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
import tn.esprit.interfaces.IEvaluationService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("evaluation")
@Api(value = "EvaluationRESTService", description = "Evaluation service")
public class EvaluationController {

	private final static Logger logger = Logger.getLogger(EvaluationController.class);

	@EJB
	private IEvaluationService service;

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the evaluations")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of evaluations", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get an evaluation by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Evaluation entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a evaluation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds an evaluation to the database")
	public Response add(Evaluation entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a evaluation", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates an evaluation")
	public Response update(Evaluation entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a evaluation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes an evaluation from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Evaluation(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a evaluation", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Affecter user to eval")
	@Path("Affecter/{idEvaluation}/{idUser}")
	public Response AffecterUserToEvaluation(@PathParam("idEvaluation")Long idEvaluation,@PathParam("idUser") Long idUser) {
		try {
			service.affecterUserToEvalution(idEvaluation, idUser);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a eval", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of members by évaluation")
	@Path("Members/{idEvaluation}")
	public Response getListMembersByEval(@PathParam("idEvaluation")  long idEvaluation) {
		try {
			return Response.status(Status.OK).entity(service.getMembreByEval(idEvaluation)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of members by eval", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of sujets by évaluation")
	@Path("Sujets/{idEvaluation}")
	public Response getListSujetsByEval(@PathParam("idEvaluation")  long idEvaluation) {
		try {
			return Response.status(Status.OK).entity(service.getSujetsByEval(idEvaluation)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of sujets by eval", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of evaluation by user")
	@Path("Members/{idUser}")
	public Response getListEvalByUser(@PathParam("idUser")  long idUser) {
		try {
			return Response.status(Status.OK).entity(service.getEvalByUser(idUser)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of members by eval", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	
}