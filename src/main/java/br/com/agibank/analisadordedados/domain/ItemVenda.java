package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemVenda {

    private Integer id;
    private Integer quantidade;
    private BigDecimal preco;

    public ItemVenda(String[] dados) {
        this.id = Integer.valueOf(dados[0]);
        this.quantidade = Integer.valueOf(dados[1]);
        this.preco = new BigDecimal(dados[2]);
    }

    public BigDecimal calcularTotal() {
        return this.preco.multiply(new BigDecimal(this.quantidade));
    }
}
