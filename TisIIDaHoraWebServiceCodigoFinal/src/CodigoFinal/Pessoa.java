package CodigoFinal;


/**
 * Classe Produto
 * 
 * @author Hugo de Paula
 * @version 2016.9.05.15
 *
 */
public abstract class Pessoa {
	private String nome;
	private String cpf;
	private int quant;
	private int id;
//	private LocalDateTime dataFabricacao;

	private static int cont = 0;
	private static int instancias = 0;

	public static int getCont() {
		return cont;
	}

	public static int getInstancias() {
		return instancias;
	}

	public int getId() {
		return id;
	}

	public boolean emEstoque() {
		return (quant > 0);
	}

	public abstract boolean emValidade();

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public int getQuant() {
		return quant;
	}

	
	public void setNome(String n) {
		if (n.length() >= 3)
			nome = n;
	}

	public void setCpf(String c) {
		if (c.length() > 0)
			cpf = c;
	}

	public void setQuant(int q) {
		if (q >= 0)
			quant = q;
	}


	public Pessoa(String n, String c, int q) {
		setNome(n);
		setCpf(c);
		setQuant(q);
		

		id = ++cont;
		instancias++;
	}

	public Pessoa() {
		String nomeFuncTeste = "Joao";
		String cpfFuncTeste = "23456878998";
		nome = nomeFuncTeste;
		cpf = cpfFuncTeste;
		quant = 1;

		id = ++cont;
		instancias++;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa ser
	 * exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Funcionario " + id + " - " + nome + "   CPF " + cpf + "   Quant.: " + quant;
	}

	/**
	 * É executado quando um objeto está sendo removido da memória.
	 */

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Finalizando um produto....");
		instancias--;
	}

}

