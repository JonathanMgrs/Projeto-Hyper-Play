
class CarteiraDigital {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void adicionarSaldo(double valor) {
        saldo += valor;
    }

    public void removerSaldo(double valor) {
        saldo -= valor;
    }
}
