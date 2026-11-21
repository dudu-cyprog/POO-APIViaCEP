package Qualquer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsumirCep {

	public static void main(String[] args) {
		
	//================================================================================================================================
	//                                    Eduardo Henrique Rodrigues Da Silva Andrade 0024896
	//================================================================================================================================
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o CEP: ");
		String cep = sc.nextLine().replaceAll("[^0-9]", ""); // tira traço, ponto e espaços

		if (cep.length() != 8) {
			System.out.println("CEP inválido. O CEP precisa ter 8 números.");
			sc.close();
			return;
		}

		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url)) // informa o endereço da consulta
					.GET()                // pede os dados para o site
					.build();             // finaliza a montagem do pedido
			
			HttpResponse<String> response = client.send(
					request,
					HttpResponse.BodyHandlers.ofString());

			if (response.body().contains("\"erro\"")) {
				System.out.println("CEP não encontrado.");
			} else {
				System.out.println(response.body());
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar o CEP: " + e.getMessage());	
		}

		sc.close();
	}
}