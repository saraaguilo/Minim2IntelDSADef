package edu.upc.dsa.services;


import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.naming.InsufficientResourcesException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {
    final static Logger logger = Logger.getLogger(GameService.class);
    private GameManager manager;
    private IUserDAO usermanager;
    private IInventoryDAO inventorymanager;


    public GameService() throws EmailAlreadyInUseException {
        this.manager = GameManagerImpl.getInstance();
        this.usermanager = UserDAOImpl.getInstance();
        this.inventorymanager = InventoryDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "User registration", notes = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered", response = User.class),
            @ApiResponse(code = 404, message = "This email address is already in use"),
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) throws EmailAlreadyInUseException {
        try{
            this.manager.register(new User(user.getIdUser(),user.getName(), user.getSurname(), user.getEmail(), user.getPassword()));
            return Response.status(201).entity(user).build();
        } catch (EmailAlreadyInUseException e){
            e.printStackTrace();
            return Response.status(404).entity(user).build();

        }

    }
    @POST
    @ApiOperation(value = "User login", notes = "Authenticate an existing user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully authenticated", response = idUser.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 401, message = "Incorrect password"),
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException {
        try {
            String id = this.manager.login(credentials);
            idUser idUser = new idUser(id);
            return Response.status(201).entity(idUser).build();
        } catch (UserNotRegisteredException e) {
            return Response.status(404).build();
        } catch (IncorrectPasswordException e) {
            return Response.status(401).build();
        }}

    @GET
    @ApiOperation(value = "View the items from the shop", notes = "View items")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop() {

        List<Item> items = this.manager.Shop();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @PUT
    @ApiOperation(value = "Buy an item from the shop", notes = "Buy items")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 409, message = "Item already purchased"),
            @ApiResponse(code = 401, message = "Item does not exist"),
            @ApiResponse(code = 403, message = "Insufficient money")
    })
    @Path("/buyItems/{idItem}/{name}/{idUser}")
    public Response buyItems(@PathParam("idItem")String idItem,@PathParam("name") String name,@PathParam("idUser") String idUser) {
        try{
            this.usermanager.buyItem(idItem,name,idUser);
            return Response.status(201).build();
        }
        catch (InsufficientMoneyException e){
            return Response.status(403).build();
        }
        catch (NonExistentItemException e) {
            return Response.status(401).build();
        }
        catch (SQLException e) {
            return Response.status(409).build();
        }
    }
    @GET
    @ApiOperation(value = "View items in the user's Inventory", notes = "View Inventory")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 401, message = "User has an empty inventory"),
    })
    @Path("/inventory/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory(@PathParam("idUser") String idUser) {

      try{
          this.inventorymanager.getInventory(idUser);
          return Response.status(201).build();
      }catch (SQLException e){
          return Response.status(401).build();
      }

    }
    @PUT
    @ApiOperation(value = "update a User", notes = "Updating a User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 401, message = "User does not exist")
    })
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UpdateInfo info) {
        try{
            this.usermanager.updateUser(info);
            return Response.status(201).build();
        }
        catch (SQLException e) {
            return Response.status(401).build();
        }
    }

}