package br.com.agibank.analisadordedados.web.rest;

import br.com.agibank.analisadordedados.service.ArquivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/arquivos")
@CrossOrigin(origins = "*")
public class ArquivoResource {

    private static final Logger log = LoggerFactory.getLogger(ArquivoResource.class);

    private final ArquivoService arquivoService;

    public ArquivoResource(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    @GetMapping("/processarvendas")
    public ResponseEntity<List<String>> processarVendas() {
        log.info("Requisição para processar arquivos de venda.");
        List<String> retorno = this.arquivoService.processar();
        return ResponseEntity.ok().body(retorno);
    }

}
