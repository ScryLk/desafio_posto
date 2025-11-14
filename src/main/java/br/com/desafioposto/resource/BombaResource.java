package br.com.desafioposto.resource;

import br.com.desafioposto.model.Bomba;
import br.com.desafioposto.service.BombaService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/bombas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BombaResource {

    private final BombaService service = new BombaService();

    @POST
    public Response criar(Bomba bomba) {
        try {
            service.criar(bomba);
            return Response.status(Response.Status.CREATED).entity(bomba).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Bomba> listar() {
        return service.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        Bomba bomba = service.buscarPorId(id);

        if (bomba == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(bomba).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Integer id, Bomba bomba) {
        try {
            bomba.setId(id);
            service.atualizar(bomba);
            return Response.ok(bomba).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
