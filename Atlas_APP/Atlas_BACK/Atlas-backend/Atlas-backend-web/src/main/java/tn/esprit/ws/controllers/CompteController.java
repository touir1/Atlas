package tn.esprit.ws.controllers;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.entity.Compte;
import tn.esprit.interfaces.ICompteService;

@Path("compte")
@Api(value = "CompteRESTService", description = "Compte service")
public class CompteController {
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); 
	private final static Logger logger = Logger.getLogger(CompteController.class);

	@EJB
	private ICompteService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of all the comptes")
	public Response getAll() {
		try {
			return Response.status(Status.OK).entity(service.getAll()).build();
		} catch (Exception e) {
			logger.error("failed while trying to get the list of comptes", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@ApiOperation(value = "get a compte by id")
	public Response getOne(@PathParam("id") long id) {
		try {
			Compte entity = service.get(id);
			if (entity != null)
				return Response.status(Status.OK).entity(entity).build();
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("failed while trying to get a compte", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "adds a compte to the database")
	public Response add(Compte entity) {
		try {
			if (service.add(entity))
				return Response.status(Status.CREATED).build();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error("failed while trying to save a compte", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}:*/

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates a compte")
	public Response update(Compte entity) {
		try {
			service.update(entity);
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to update a compte", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "deletes a compte from the database")
	public Response delete(@PathParam("id") long id) {
		try {
			service.remove(new Compte(id));
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("failed while trying to delete a compte", e);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	 @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	    public Response authenticateUser(@FormParam("username") String username, 
	                                     @FormParam("password") String password, @Context HttpServletRequest req) {
		 try {
			 	HttpSession session = req.getSession();
			 	
	            // Authenticate the user using the credentials provided
	            Compte compte=authenticate(username, password);

	            // Issue a token for the user
	            String token = issueToken(username);
	           compte.setToken(token);
	            session.setAttribute("user", compte);
	          


	            // Return the token on the response
	            return Response.ok(compte).build();

	        } catch (Exception e) {
	            return Response.status(Response.Status.FORBIDDEN).build();
	        }      
	    }

	    private Compte authenticate(String username, String password) throws Exception {
	    	// Authenticate against a database, LDAP, file or whatever
	        // Throw an Exception if the credentials are invalid
	    	Compte cp = new Compte();
	    	cp.setUsername(username);
			return cp;
	    }

	    private String issueToken(String username) {
	    	byte[] randomBytes = new byte[24];
	        secureRandom.nextBytes(randomBytes);
	        return base64Encoder.encodeToString(randomBytes) + Base64.getEncoder().encodeToString(username.getBytes());
	    	
	    }
	

}
