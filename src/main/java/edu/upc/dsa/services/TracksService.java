package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/tracks", description = "Endpoint to Track Service")
@Path("/tracks")
public class TracksService {

    private GameManager gm;

    public TracksService() {
        this.gm = GameManagerImpl.getInstance();
        }


    }