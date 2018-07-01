/**
 *		Andrea Tosti Mat n.518111 progetto prima verifica A. A. 2016-17
 */

import java.util.*;

public class TestCaseOne
{
	public static void main(String[] args)
	{
		/* Inizializzazione grafo */
	 	TwitterGraph<Node> grafo = new TwitterGraph<Node>();
		
		/* Inizializzazione nodi */	 	
	 	Node andrea = new Node("Andrea");
	 	Node giorgio = new Node("Giorgio");
	 	Node anna = new Node("Anna");
	 	Node fausto = new Node("Fausto");
	 	Node matteo = new Node("Matteo");
	 	Node luigi = new Node("Luigi");
	 	Node mario = new Node("Mario");

		/* Aggiunta nodi al grafo */
	 	grafo.addNode("andrea", andrea);
	 	grafo.addNode("giorgio", giorgio);
	 	grafo.addNode("anna", anna);
	 	grafo.addNode("fausto", fausto);
	 	grafo.addNode("matteo", matteo);
	 	grafo.addNode("luigi", luigi);
	 	grafo.addNode("mario", mario);

	 	/* Following tra nodi */
	 	grafo.addEdge("andrea", "giorgio");
	 	grafo.addEdge("andrea", "anna");
	 	grafo.addEdge("andrea", "matteo");
	 	grafo.addEdge("giorgio", "andrea");
	 	grafo.addEdge("giorgio", "fausto");
	 	grafo.addEdge("anna", "andrea");
	 	grafo.addEdge("anna", "giorgio");
	 	grafo.addEdge("fausto", "andrea");
	 	grafo.addEdge("fausto", "giorgio");
	 	grafo.addEdge("fausto", "mario");
	 	grafo.addEdge("matteo", "luigi");
	 	grafo.addEdge("luigi", "mario");
	 	grafo.addEdge("mario", "luigi");

		grafo.printAllUsers();
		grafo.printAllFollowing();

		String utente1 = "fausto";
		String utente2 = "matteo";

		System.out.println("Il numero di intermediari del cammino piu' corto tra " + 
			utente1 + " e " + utente2 + " e' : " + grafo.shortestPath(utente1, utente2));

		System.out.println("Il numero di intermediari del massimo di tutti i possibili" +
			" cammini minimi del grafo (Diametro) e' : " + grafo.longestPath());

		if(!grafo.repOk()) System.out.println("Invariante violato");
	
	}
}