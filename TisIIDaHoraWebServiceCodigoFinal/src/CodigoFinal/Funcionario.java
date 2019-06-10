package CodigoFinal;


class Funcionario extends Pessoa {
	private int horasTrabalhadas;
	private int horasExtras;
	private int dia;

	public int getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(int horasTrabalhadas) {

		if (horasTrabalhadas > 0)
			this.horasTrabalhadas = horasTrabalhadas;
	}

	public Funcionario() {
		super();

	}

	public Funcionario(String nome, String cpf, int q) {
		super(nome, cpf, q);
		setHorasTrabalhadas(q);
	}

	@Override
	public String toString() {
		return super.toString() + "   Folgas: " + dia;
	}

	@Override
	public boolean emValidade() {
		if (getHorasExtras() > getHorasTrabalhadas()) {
			setDia(1);
			return true;
		} else
			return false;
	}

	/**
	 * @return the horasExtras
	 */
	private int getHorasExtras() {
		return horasExtras;
	}

	/**
	 * @param horasExtras the horasExtras to set
	 */
	private void setHorasExtras(int horasExtras) {
		horasExtras = 24;
		this.horasExtras = horasExtras;
	}

	/**
	 * @return the dia
	 */
	@SuppressWarnings("unused")
	private int getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	private void setDia(int dia) {
		this.dia = dia;
	}

}

