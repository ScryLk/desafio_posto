package br.com.desafioposto.resource;

import br.com.desafioposto.model.TipoCombustivel;
import br.com.desafioposto.service.TipoCombustivelService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/combustiveis")
public class TipoCombustivelResource {

    private final TipoCombustivelService service = new TipoCombustivelService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoCombustivel> listar() {
        return service.listarTodos();
    }
}
