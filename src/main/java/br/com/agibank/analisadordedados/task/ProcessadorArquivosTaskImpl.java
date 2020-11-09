package br.com.agibank.analisadordedados.task;

import br.com.agibank.analisadordedados.service.ProcessadorArquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessadorArquivosTaskImpl implements ProcessadorArquivosTask {

    private ProcessadorArquivos processadorArquivos;

    @Autowired
    public ProcessadorArquivosTaskImpl(ProcessadorArquivos processadorArquivos) {
        this.processadorArquivos = processadorArquivos;
    }

    private static final Logger log = LoggerFactory.getLogger(ProcessadorArquivosTaskImpl.class);

    @Scheduled(fixedDelay = 10000)
    public void processarNovosArquivosDeVendas() {
        log.info("Monitorando diretório de entrada de arquivos.");
        this.processadorArquivos.processar();
    }
}