package CodigoFinal;



/**
 * Classe BemDuravel
 * 
 * @author Hugo de Paula
 * @version 2016.9.05.15
 *
 */
public class Empresa extends Pessoa {
	private int qntFunc;

	public int getQntFunc() {
		return qntFunc;
	}

	public void setQntFunc(int qntFunc) {
		if (qntFunc > 0)
			this.qntFunc = qntFunc;
	}

	public Empresa() {
		super();
		// o valor default da quantidade de funcionarios é de 6.
		qntFunc = 6;
	}

	public Empresa(String nome, String cpf, int q) {
		super(nome, cpf, q);
		setQntFunc(q);
	}
	
	/**
	 * Método sobreposto da classe Object.
	 * É executado quando um objeto precisa ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return super.toString() + "   Garantia: " + qntFunc;
	}


	@Override
	public boolean emValidade() {
		// TODO Auto-generated method stub
		return false;
	}


}

