package CodigoFinal;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class EstoqueService {
	private Estoque estoque;

	// @SuppressWarnings("unused")

	File fileFunc = new File("Funcionario.txt");
	File fileEmpresa = new File("Empresta.txt");

	public String adicionarPessoa(Request request) throws FileNotFoundException {
		String nome;
		String cpf;
		int qtd, tipo;
		Pessoa f = null;
		Query query = request.getQuery();
		nome = query.get("nome");
		cpf = query.get("cpf");
		qtd = query.getInteger("qtd");
		tipo = query.getInteger("tipo");

		switch (tipo) {
		case 1:
			f = new Funcionario(nome, cpf, qtd);
			break;
		case 2:
			f = new Empresa(nome, cpf, qtd);
			break;
		}

		if (f != null) {
			estoque.adicionar(f, fileFunc, fileEmpresa, tipo);
		}

		return f.toString();

	}

	public String consultarPessoa(Request request) throws FileNotFoundException {
		int tipo;
		Query query = request.getQuery();
		String nome = query.get("nome");
		tipo = query.getInteger("tipo");
		switch (tipo) {
		case 1:
			tipo = 1;
			break;
		case 2:
			tipo = 2;
			break;
		}
		Pessoa f = estoque.consultar(fileFunc, fileEmpresa, nome, tipo);
		return f.toString();

	}

	public String removerPessoa(Request request) throws FileNotFoundException {
		int num = estoque.getNumPessoa();
		int tipo;
		Query query = request.getQuery();
		String nome = query.get("nome");
		tipo = query.getInteger("tipo");
		switch (tipo) {
		case 1:
			tipo = 1;
			break;
		case 2:
			tipo = 2;
			break;
		}
		estoque.remover(fileFunc, fileEmpresa, nome, tipo);
		if (num < estoque.getNumPessoa())
			return "Removido";
		else
			return null;

	}
	
	public String alterarPessoa(Request request) throws IOException {
		int num = estoque.getNumPessoa();
		int tipo;
		Query query = request.getQuery();
		String nome = query.get("nome");
		String cpf = query.get("cpf");
		tipo = query.getInteger("tipo");
		switch (tipo) {
		case 1:
			tipo = 1;
			break;
		case 2:
			tipo = 2;
			break;
		}
		estoque.alterar(fileFunc, fileEmpresa, nome, cpf, tipo);
			return "Alterado";

	}

	public String totalPessoa(Request request) {
		return Integer.toString(estoque.totalPessoa());

	}

	public EstoqueService() {
		estoque = new Estoque();
	}

}

