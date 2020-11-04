package br.com.agibank.analisadordedados.builder;

import br.com.agibank.analisadordedados.domain.Cliente;

public class ClienteBuilder {

    private Cliente cliente = new Cliente();

    public static ClienteBuilder umCliente() {
        return new ClienteBuilder();
    }

    public static ClienteBuilder umClienteValido() {
        return umCliente().comCnpj("2345675434544345").comNome("Jose da Silva").comAreaNegocio("Rural");
    }

    public ClienteBuilder comNome(String nome) {
        cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comCnpj(String cnpj) {
        cliente.setCnpj(cnpj);
        return this;
    }

    public ClienteBuilder comAreaNegocio(String areaNegocio) {
        cliente.setAreaNegocio(areaNegocio);
        return this;
    }

    public Cliente build() {
        return cliente;
    }
}
