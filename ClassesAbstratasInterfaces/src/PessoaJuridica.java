public class PessoaJuridica extends Pessoa {
    private String CNPJ;

    public PessoaJuridica(String nome, String CNPJ) {
        super(nome);
        setTipo("PessoaJuridica!");
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}
