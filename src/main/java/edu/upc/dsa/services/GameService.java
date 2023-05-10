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

    public GameService() throws EmailAlreadyInUseException {
        this.manager = GameManagerImpl.getInstance();
        if (manager.getUsers().size()==0){
            manager.register(new User("Toni","Boté","toni@upc.edu","12345"));
            manager.register(new User("Agustín","Tapia","agus@upc.edu","20236"));
        }
    }

    @POST
    @ApiOperation(value = "User registration", notes = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered", response = User.class),
            @ApiResponse(code = 404, message = "This email address is already in use"),
            @ApiResponse(code = 500, message = "Empty credentials")
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(User user) throws EmailAlreadyInUseException {
        /**if (user.getName().equals("") || user.getSurname().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
            return Response.status(500).entity(user).build();**/
        try{
            this.manager.register(new User(user.getName(), user.getSurname(), user.getEmail(), user.getPassword()));
            return Response.status(201).entity(user).build();
        } catch (EmailAlreadyInUseException e){
            return Response.status(404).entity(user).build();
        }

    }
    @POST
    @Path("/login")
    @ApiOperation(value = "User login", notes = "Authenticate an existing user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully authenticated", response = Credentials.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 401, message = "Incorrect password")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(Credentials credentials) {
        try {
            User user = this.manager.login(credentials);
            return Response.status(201).entity(user).build();
        } catch (UserNotRegisteredException e) {
            return Response.status(404).build();
        } catch (IncorrectPasswordException e) {
            return Response.status(401).build();
        }
    }

}