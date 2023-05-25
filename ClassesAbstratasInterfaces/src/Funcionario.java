public abstract class Funcionario {
    private String nome;
    private Double salario;

    public Funcionario(String nome, Double bonificacao) {
        this.nome = nome;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract Double getBonificacao();

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
