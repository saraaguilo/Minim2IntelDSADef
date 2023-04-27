package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() {
        this.manager = GameManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "User registration", notes = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered"),
            @ApiResponse(code = 404, message = "This email address is already in use"),
            @ApiResponse(code = 500, message = "Empty credentials")
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(User user) {
        if (user.getName().equals("") || user.getSurname().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
            return Response.status(500).entity(user).build();
        try {
            this.manager.Register(user);
            return Response.status(201).entity(user).build();
        } catch (EmailAlreadyInUseException e) {
            return Response.status(404).entity(user).build();
        }
    }
    @POST
    @ApiOperation(value = "User login", notes = "log in using credentials")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Incorrect credentials")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(Credentials credentials) throws IncorrectPasswordException, UserNotRegisteredException {
        User user = this.manager.Login(credentials.getEmail(), credentials.getPassword());
        if (user!= null) {
            return Response.status(201).entity(user).build();
        }
        else {
            return Response.status(404).entity(user).build();
        }
    }

}