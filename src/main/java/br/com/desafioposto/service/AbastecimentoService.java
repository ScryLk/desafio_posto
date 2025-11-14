package br.com.desafioposto.service;

import br.com.desafioposto.dao.AbastecimentoDAO;
import br.com.desafioposto.dao.BombaDAO;
import br.com.desafioposto.dao.TipoCombustivelDAO;
import br.com.desafioposto.model.Abastecimento;
import br.com.desafioposto.model.Bomba;
import br.com.desafioposto.model.TipoCombustivel;

import java.time.LocalDateTime;
import java.util.List;

public class AbastecimentoService {

    private final AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO();
    private final BombaDAO bombaDAO = new BombaDAO();
    private final TipoCombustivelDAO combustivelDAO = new TipoCombustivelDAO();

    public void registrar(Abastecimento ab) {

        // valida bomba
        Bomba bomba = bombaDAO.buscarPorId(ab.getBombaId());
        if (bomba == null) {
            throw new IllegalArgumentException("Bomba não encontrada");
        }

        // pega o combustível da bomba
        TipoCombustivel tipo = combustivelDAO.buscarPorId(bomba.getTipoCombustivelId());
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de combustível da bomba não encontrado");
        }

        // se o user não enviar o preço, assume o preço do tipo
        if (ab.getPrecoLitroPraticado() == null) {
            ab.setPrecoLitroPraticado(tipo.getPrecoLitro());
        }

        // calcula valor total
        ab.setValorTotal(ab.getPrecoLitroPraticado() * ab.getLitros());

        // define data se não enviada
        if (ab.getDataAbastecimento() == null) {
            ab.setDataAbastecimento(LocalDateTime.now());
        }

        abastecimentoDAO.inserir(ab);
    }

    public List<Abastecimento> listarTodos() {
        return abastecimentoDAO.listarTodos();
    }

    public Abastecimento buscarPorId(Integer id) {
        return abastecimentoDAO.buscarPorId(id);
    }

    public void atualizar(Abastecimento ab) {
        if (ab.getId() == null) {
            throw new IllegalArgumentException("ID é obrigatório para atualizar");
        }

        // recalcula valor_total
        ab.setValorTotal(ab.getLitros() * ab.getPrecoLitroPraticado());

        abastecimentoDAO.atualizar(ab);
    }

    public void deletar(Integer id) {
        abastecimentoDAO.deletar(id);
    }
}
