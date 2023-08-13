package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.CreatorProperty;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio repositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    public Iterable<ProdutoModelo> listar() {
        return repositorio.findAll();
    }

    public ResponseEntity<?> cadastrarOuAlterar(ProdutoModelo produtoModelo, String acao) {
        if (produtoModelo.getNome().equals("")) {
            respostaModelo.setMensagem("Nome obrigatório");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else if (produtoModelo.getMarca().equals("")) {
            respostaModelo.setMensagem("Marca obrigatória");
            return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.BAD_REQUEST);
        } else {
            ProdutoModelo saved = repositorio.save(produtoModelo);
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<ProdutoModelo>(saved, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<ProdutoModelo>(saved, HttpStatus.OK);
            }

        }
    }

}
