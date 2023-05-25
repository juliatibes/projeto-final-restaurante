public class Diretor extends Gerente{
    public Diretor(String nome, Double bonificacao) {
        super(nome, bonificacao);
    }

    @Override
    public Double getBonificacao() {
        return getSalario() * 1.20; //nao tem necessidade de definir pois nao extend de uma classe abstrata
    }
}
