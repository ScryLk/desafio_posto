package br.com.desafioposto.service;

import br.com.desafioposto.dao.BombaDAO;
import br.com.desafioposto.dao.TipoCombustivelDAO;
import br.com.desafioposto.model.Bomba;
import br.com.desafioposto.model.TipoCombustivel;

import java.util.List;

public class BombaService {

    private final BombaDAO bombaDAO = new BombaDAO();
    private final TipoCombustivelDAO combustivelDAO = new TipoCombustivelDAO();

    public void criar(Bomba bomba) {
        if (bomba.getNome() == null || bomba.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da bomba é obrigatório");
        }

        TipoCombustivel tipo = combustivelDAO.buscarPorId(bomba.getTipoCombustivelId());
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de combustível não encontrado");
        }

        bombaDAO.inserir(bomba);
    }

    public List<Bomba> listarTodas() {
        return bombaDAO.listarTodas();
    }

    public Bomba buscarPorId(Integer id) {
        return bombaDAO.buscarPorId(id);
    }

    public void atualizar(Bomba bomba) {
        if (bomba.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualizar");
        }
        bombaDAO.atualizar(bomba);
    }

    public void deletar(Integer id) {
        bombaDAO.deletar(id);
    }
}
