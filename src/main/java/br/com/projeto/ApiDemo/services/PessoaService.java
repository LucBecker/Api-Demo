package br.com.projeto.ApiDemo.services;

import br.com.projeto.ApiDemo.model.Mensagem;
import br.com.projeto.ApiDemo.model.Pessoa;
import br.com.projeto.ApiDemo.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private PessoaRepository acao;

    // Método para cadastrar pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj){

        if(obj.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preeenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Método para selecionar pessoas pelo codigo
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){

        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Nao foi encontrada nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }
    // Método para editar pessoa
    public ResponseEntity<?> editar(Pessoa obj){

        if(acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (obj.getNome().equals("")) {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    // Método para remover pessoa
    public ResponseEntity<?> remover(int codigo){

        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Pesssoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
