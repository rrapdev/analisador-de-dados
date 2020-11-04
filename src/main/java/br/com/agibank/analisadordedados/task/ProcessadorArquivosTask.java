package br.com.agibank.analisadordedados.task;

import br.com.agibank.analisadordedados.service.ArquivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessadorArquivosTask {

    @Autowired
    private ArquivoService arquivoService;

    private static final Logger log = LoggerFactory.getLogger(ProcessadorArquivosTask.class);

    @Scheduled(fixedDelay = 1000)
    public void processarNovosArquivosDeVendas() {
        log.info("Monitorando diretório de entrada de arquivos.");
        arquivoService.processar();
    }
}
