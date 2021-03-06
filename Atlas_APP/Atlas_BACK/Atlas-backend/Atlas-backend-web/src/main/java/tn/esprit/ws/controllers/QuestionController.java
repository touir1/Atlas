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
import tn.esprit.entity.Question;
import tn.esprit.interfaces.IQuestionService;
import tn.esprit.ws.AtlasWSActivator.Secured;

@Path("question")
@Api(value = "QuestionRESTService", description = "Question service")
public class QuestionController {
private final static Logger logger = Logger.getLogger(QuestionController.class);
	
	@EJB
	private IQuestionService service;
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the questions")
	public Response getAll(){
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get the list of questions",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a question by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Question entity = service.get(id);
			if(entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to get a question",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a question to the database")
	public Response add(Question entity) {
		try {
			entity = service.add(entity);
			if(entity != null)
				return Response.status(Status.CREATED).entity(entity).build();
			return Response.status(Status.BAD_REQUEST).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to save a question",e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a question")
	public Response update(Question entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to update a question",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@Secured
	@Path("{id}")
	@ApiOperation(value = "deletes a question from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Question(id));
			return Response.status(Status.ACCEPTED).build();
		}
		catch(Exception e) {
			logger.error("failed while trying to delete a question",e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	//@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of question by sujet")
	@Path("bySujet/{idSujet}")
	public Response getListQuestionBySujet(@PathParam("idSujet")  long idSujet) {
		try {
			return Response.status(Status.OK).entity(service.getQuestionBySujet(idSujet)).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of question by sujet", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
