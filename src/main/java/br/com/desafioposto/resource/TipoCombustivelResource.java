package br.com.desafioposto.resource;

import br.com.desafioposto.model.TipoCombustivel;
import br.com.desafioposto.service.TipoCombustivelService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/combustiveis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoCombustivelResource {

    private final TipoCombustivelService service = new TipoCombustivelService();

    @POST
    public Response criar(TipoCombustivel tipo) {
        try {
            service.criar(tipo);
            return Response.status(Response.Status.CREATED).entity(tipo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<TipoCombustivel> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        TipoCombustivel tipo = service.buscarPorId(id);

        if (tipo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(tipo).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Integer id, TipoCombustivel tipo) {
        try {
            tipo.setId(id);
            service.atualizar(tipo);
            return Response.ok(tipo).build();
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
