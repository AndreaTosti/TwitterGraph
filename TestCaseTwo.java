/**
 *		Andrea Tosti Mat n.518111 progetto prima verifica A. A. 2016-17
 */

import java.util.*;

public class TestCaseTwo
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

		/* aggiungiamo gli altri 3 nodi */

		Node raffaele = new Node("Raffaele");
		Node carlo = new Node("Carlo");
		Node augusto = new Node("Augusto");
		grafo.addNode("raffaele", raffaele);
		grafo.addNode("carlo", carlo);
		grafo.addNode("augusto", augusto);

		/* Follow tra Mario e Raffaele */
		grafo.addEdge("mario", "raffaele");
		/* Follow da Carlo a Raffaele */
		grafo.addEdge("carlo", "raffaele");
		/* Follow da Mario a Augusto */
		grafo.addEdge("mario", "augusto");
		/* Follow da Augusto a Carlo */
		grafo.addEdge("augusto", "carlo");

		/* Rimuovo Giorgio dal grafo */
		grafo.removeNode("giorgio");

		/* Rimuovo il follow da Andrea ad Anna */
		grafo.removeEdge("andrea", "anna");

		/* Rimuovo il follow da Mario a Raffaele */
		grafo.removeEdge("mario", "raffaele");

		grafo.printAllUsers();
		grafo.printAllFollowing();

		String utente1 = "andrea";
		String utente2 = "raffaele";

		System.out.println("Il numero di intermediari del cammino piu' corto tra " + 
			utente1 + " e " + utente2 + " e' : " + grafo.shortestPath(utente1, utente2));

		System.out.println("Il numero di intermediari del massimo di tutti i possibili" +
			" cammini minimi del grafo (Diametro) e' : " + grafo.longestPath());

		if(!grafo.repOk()) System.out.println("Invariante violato");
	
	}
}