package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Client;
import model.dto.UpdatedClientResponseDto;
import service.ClientService;
import util.Validation;

@Singleton
@Path("/clients")
public class ClientController {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Inject
    private Validation validation;
    @Inject
    private ClientService clientService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("fio") String fio,
                           @QueryParam("mainPhoneNumber") String mainPhoneNumber) {
        if (!validation.isValidFio(fio) || !validation.isValidPhoneNumber(mainPhoneNumber)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid type of fio or phone number!").build();
        }
        Client user = new Client(LocalDateTime.now().format(FORMATTER), null);
        clientService.create(user);
        return Response.status(Response.Status.OK)
                .entity("Client with id: " + user.getId() + " was created!").build();
    }

    @PATCH
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UpdatedClientResponseDto updatedClientResponseDto) {
        if (!validation.isValidFio(updatedClientResponseDto.getFio())
                || !validation.isValidPassportNumber(updatedClientResponseDto.getPassportNumber())
                || !validation.isValidBirthDate(updatedClientResponseDto.getBirthDate())
                || !validation.isValidPhoneNumber(updatedClientResponseDto.getSecondNumber())
                || clientService.find(updatedClientResponseDto.getClientId()).isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        clientService.update(updatedClientResponseDto);
        return Response.status(Response.Status.OK)
                .entity(updatedClientResponseDto).build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long userId) {
        if (clientService.find(userId).isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Client with id " + userId + " not found!").build();
        }
        return Response.status(Response.Status.OK)
                .entity(clientService.find(userId).toString()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long userId) {
        return !clientService.delete(userId)
                ? Response.status(Response.Status.BAD_REQUEST).build()
                : Response.status(Response.Status.OK).build();
    }
}
