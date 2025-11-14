package br.com.desafioposto.resource;

import br.com.desafioposto.model.TipoCombustivel;
import br.com.desafioposto.service.TipoCombustivelService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/combustiveis")
public class TipoCombustivelResource {

    private final TipoCombustivelService service = new TipoCombustivelService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoCombustivel> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        TipoCombustivel combustivel = service.buscarPorId(id);
        if (combustivel != null) {
            return Response.ok(combustivel).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(TipoCombustivel combustivel) {
        service.criar(combustivel);
        return Response.status(Response.Status.CREATED).entity(combustivel).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, TipoCombustivel combustivel) {
        combustivel.setId(id);
        service.atualizar(combustivel);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
