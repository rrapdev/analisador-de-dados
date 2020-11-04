package br.com.agibank.analisadordedados.cucumber.datatable;

public class ResumoDataTable {
    private Integer quantidadeClientes;
    private Integer quantidadeVendedores;
    private Integer idVendaMaisCara;
    private String nomePiorVendedor;

    public Integer getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public void setQuantidadeClientes(Integer quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }

    public Integer getQuantidadeVendedores() {
        return quantidadeVendedores;
    }

    public void setQuantidadeVendedores(Integer quantidadeVendedores) {
        this.quantidadeVendedores = quantidadeVendedores;
    }

    public Integer getIdVendaMaisCara() {
        return idVendaMaisCara;
    }

    public void setIdVendaMaisCara(Integer idVendaMaisCara) {
        this.idVendaMaisCara = idVendaMaisCara;
    }

    public String getNomePiorVendedor() {
        return nomePiorVendedor;
    }

    public void setNomePiorVendedor(String nomePiorVendedor) {
        this.nomePiorVendedor = nomePiorVendedor;
    }
}
