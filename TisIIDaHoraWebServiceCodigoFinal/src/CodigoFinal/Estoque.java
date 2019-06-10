package CodigoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe ir· armazenar uma lista de produtos em estoque.
 * 
 * @author Hugo
 * @version 2016.8.29.10
 *
 */
public class Estoque {
	private static final int MAX_PRODUTOS = 100;
	private Pessoa[] listaDePessoa;
	private int numPessoa;

	public int getNumPessoa() {
		return numPessoa;
	}

	public void adicionar(Pessoa f, File fileFunc, File fileEmpresa, int tipo) throws FileNotFoundException {
		if (tipo == 1) {
			cadastroFunc(fileFunc, f);
		} else
			CadastroEmpresa(fileEmpresa, (Empresa) f);
	}

	public void alterar (File fileFunc, File fileEmpresa, String nome, String cpf, int tipo)throws IOException {
		if (tipo == 1) {
			alterarFuncionario(fileFunc, nome, cpf);
		} else
			alterarEmpresa(fileEmpresa, nome, cpf);
	
	}
	public Pessoa consultar(File fileFunc, File fileEmpresa, String nome, int tipo) throws FileNotFoundException {
		for (int pos = 0; pos < numPessoa; pos++) {
			if (nome.equalsIgnoreCase(listaDePessoa[pos].getNome())) {
				
				if(tipo == 1 ) {
					ConsultaFuncionario(fileFunc,nome);
				}else {
					ConsultaEmpresa(fileEmpresa,nome);
			}
				return listaDePessoa[pos];
		}
		}
			return null; // Produto n„o encontrado.
	}

	public void remover(File fileFunc, File fileEmpresa, String nome, int tipo) throws FileNotFoundException {
		for (int pos = 0; pos < numPessoa; pos++) {
			if (nome.equalsIgnoreCase(listaDePessoa[pos].getNome())) {
				// remove produto
				for (int i = pos + 1; i < numPessoa; i++)
					listaDePessoa[i - 1] = listaDePessoa[i];
				listaDePessoa[numPessoa - 1] = null;
				numPessoa--;
				if (tipo == 1) {
					excluirFuncionario(fileFunc, nome);
				} else
					excluirEmpresa(fileEmpresa, nome);

			}
		}
	}

	public int totalPessoa() {
		int total = 0;
		for (int i = 0; i < numPessoa; i++)
			total += listaDePessoa[i].getQuant();
		return total;
	}

	@Override
	public String toString() {
		StringBuilder valor = new StringBuilder();
		for (int i = 0; i < numPessoa; i++) {
			valor.append(listaDePessoa[i] + "\n");
		}
		return valor.toString();
	}

	public Estoque() {
		listaDePessoa = new Pessoa[MAX_PRODUTOS];
		numPessoa = 0;
	}

	public void cadastroFunc(File fileFunc, Pessoa funcionario) throws FileNotFoundException {

		// FileOutputStream saida = null;
		// OutputStreamWriter gravador = null;
		BufferedWriter buffer_saida = null;

		BufferedReader buffRead = new BufferedReader(new FileReader(fileFunc));

		buffRead.lines().count();

		try {
			// saida = new FileOutputStream(fileFunc, true);
			// gravador = new OutputStreamWriter(saida);
			buffer_saida = new BufferedWriter(new FileWriter(fileFunc, true));
			buffRead.lines().count();

			buffer_saida.write(funcionario.getNome() + ":");
			System.out.println(funcionario.getNome());
			System.out.println(funcionario.getCpf());
			buffer_saida.write(funcionario.getCpf());

			buffer_saida.newLine();

			buffer_saida.flush();
			buffRead.close();
		} catch (IOException e) {
		}
	}

	public String ConsultaFuncionario(File fileFunc, String nomeFunc) throws FileNotFoundException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFunc));
		String linha = "";

		try {

			while ((linha = bufferedReader.readLine()) != null && !linha.contains(nomeFunc)) {

				if (linha.contains(nomeFunc)) {
					System.out.println(linha);
				} else
					System.out.println("Funcion√°rio n√£o encontrado");
			}
		} catch (IOException e) {
			System.err.println("Erro na abertura do arquivo " + fileFunc);
			return linha;
		}
		return linha;
	}

	public void excluirFuncionario(File fileFunc, String nomeFunc) throws FileNotFoundException {

		try {
			FileReader fileReader = new FileReader(fileFunc);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = bufferedReader.readLine();
			ArrayList<String> salvar = new ArrayList();

			while (linha != null) {
				if (!linha.contains(nomeFunc)) {
					salvar.add(linha);
				}
				linha = bufferedReader.readLine();
			}

			bufferedReader.close();
			fileReader.close();
			FileWriter fileWriter2 = new FileWriter(fileFunc, true);
			fileWriter2.close();

			FileWriter fileWriter = new FileWriter(fileFunc);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < salvar.size(); i++) {
				bufferedWriter.write(salvar.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			fileWriter.close();

		} catch (IOException ex) {

		}
	}

	public void alterarFuncionario(File fileFunc, String nomeFunc, String cpfFuncNovo) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(fileFunc));
		List<String> novasLinhas = new ArrayList<>();
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(nomeFunc)) {
				String[] campos = linha.split(":");
				linha = linha.replace(campos[1], cpfFuncNovo);
			}
			novasLinhas.add(linha);
		}
		reader.close();

		PrintWriter writer = new PrintWriter(new FileWriter(fileFunc));
		for (String novaLinha : novasLinhas) {
			writer.println(novaLinha);
		}
		writer.flush();
		writer.close();

	}

	public void CadastroEmpresa(File fileEmpre, Empresa Empresa) throws FileNotFoundException {

		FileOutputStream saida = null;
		OutputStreamWriter gravador = null;
		BufferedWriter buffer_saida = null;

		BufferedReader buffRead = new BufferedReader(new FileReader(fileEmpre));

		buffRead.lines().count();

		try {

			saida = new FileOutputStream(fileEmpre, true);
			gravador = new OutputStreamWriter(saida);
			buffer_saida = new BufferedWriter(gravador);
			buffRead.lines().count();

			buffer_saida.write(Empresa.getNome());
			buffer_saida.write(Empresa.getCpf());
			buffer_saida.newLine();

			buffer_saida.flush();
			buffRead.close();
		} catch (Exception e) {
		}
	}

	public void excluirEmpresa(File fileEmpresa, String nome) throws FileNotFoundException {

		try {
			FileReader fileReader = new FileReader(fileEmpresa);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = bufferedReader.readLine();
			ArrayList<String> salvar = new ArrayList();

			while (linha != null) {
				if (!linha.contains(nome)) {
					salvar.add(linha);
				}
				linha = bufferedReader.readLine();
			}

			bufferedReader.close();
			fileReader.close();
			FileWriter fileWriter2 = new FileWriter(fileEmpresa, true);
			fileWriter2.close();

			FileWriter fileWriter = new FileWriter(fileEmpresa);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (int i = 0; i < salvar.size(); i++) {
				bufferedWriter.write(salvar.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			fileWriter.close();

		} catch (IOException ex) {

		}
	}

	public void alterarEmpresa(File fileEmpresa, String nome, String cpf) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(fileEmpresa));
		List<String> novasLinhas = new ArrayList<>();
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(nome)) {
				String[] campos = linha.split(":");
				linha = linha.replace(campos[1], cpf);
			}
			novasLinhas.add(linha);
		}
		reader.close();

		PrintWriter writer = new PrintWriter(new FileWriter(fileEmpresa));
		for (String novaLinha : novasLinhas) {
			writer.println(novaLinha);
		}
		writer.flush();
		writer.close();

	}

	public String ConsultaEmpresa(File fileEmpresa, String nome) throws FileNotFoundException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileEmpresa));
		String linha = "";

		try {

			while ((linha = bufferedReader.readLine()) != null && !linha.contains(nome)) {

				if (linha.contains(nome)) {
					System.out.println(linha);
				} else
					System.out.println("Empresa n√£o encontrado");
			}
		} catch (IOException e) {
			System.err.println("Erro na abertura do arquivo " + fileEmpresa);
			return linha;
		}
		return linha;
	}

}


