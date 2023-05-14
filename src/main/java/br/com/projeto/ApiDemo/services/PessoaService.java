package br.com.projeto.ApiDemo.services;

import br.com.projeto.ApiDemo.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private Mensagem mensagem;
}
