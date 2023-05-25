public class PessoaFisica extends Pessoa {
    private String CPF;

    public PessoaFisica(String nome, String CPF) {
        super(nome);
        setTipo("PessoaFisica");
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
