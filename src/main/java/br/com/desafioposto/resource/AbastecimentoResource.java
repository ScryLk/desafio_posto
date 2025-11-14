package br.com.desafioposto.resource;

import br.com.desafioposto.model.Abastecimento;
import br.com.desafioposto.service.AbastecimentoService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/abastecimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AbastecimentoResource {

    private final AbastecimentoService service = new AbastecimentoService();

    @POST
    public Response registrar(Abastecimento ab) {
        try {
            service.registrar(ab);
            return Response.status(Response.Status.CREATED).entity(ab).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Abastecimento> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        Abastecimento ab = service.buscarPorId(id);

        if (ab == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(ab).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Integer id, Abastecimento ab) {
        try {
            ab.setId(id);
            service.atualizar(ab);
            return Response.ok(ab).build();
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
