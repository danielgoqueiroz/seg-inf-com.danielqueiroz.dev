package autenticacao;

import java.util.Scanner;

public class MenuSi {
	
Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenu() {
				
		int opcao = 0;
		
		while (opcao != 3){
			
			System.out.println("Gestão de Usuários");
			System.out.println("\nPor gentileza, escolha uma das opções a seguir:");
			System.out.println("1 - Criar conta (usuário novo no sistma)");
			System.out.println("2 - Entrar no Sistema");
			System.out.println("3 - Sair");		
			System.out.print("\nDigite a Opção: ");
			
			opcao = Integer.parseInt(teclado.next());
			
			switch(opcao){
				case 1: {
					Usuario u = new Usuario();
					u.criarConta();
					break;
				}
				
				case 2: {
					Usuario u = new Usuario();
					u.autenticarConta();
					break;
				}
				
				default: {
					System.out.println("\nopção inválida");
					System.out.println("____________________________");
				
				}
			}
		}
		
		System.out.println("Você saiu do sistema!");
	}
}
