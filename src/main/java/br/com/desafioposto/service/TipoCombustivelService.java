package br.com.desafioposto.service;

import br.com.desafioposto.dao.TipoCombustivelDAO;
import br.com.desafioposto.model.TipoCombustivel;

import java.util.List;

public class TipoCombustivelService {

    private final TipoCombustivelDAO dao = new TipoCombustivelDAO();

    public void criar(TipoCombustivel tipo) {
        if (tipo.getNome() == null || tipo.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do combustível é obrigatório");
        }
        if (tipo.getPrecoLitro() == null || tipo.getPrecoLitro() <= 0) {
            throw new IllegalArgumentException("Preço do litro deve ser maior que zero");
        }

        dao.inserir(tipo);
    }

    public List<TipoCombustivel> listarTodos() {
        return dao.listarTodos();
    }

    public TipoCombustivel buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(TipoCombustivel tipo) {
        if (tipo.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualizar");
        }
        dao.atualizar(tipo);
    }

    public void deletar(Integer id) {
        dao.deletar(id);
    }
}
