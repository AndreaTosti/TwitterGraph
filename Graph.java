/**
 *		Andrea Tosti Mat n.518111 progetto prima verifica A. A. 2016-17
 */

public interface Graph<E>
{
	/**
	 * OVERVIEW: Dato G = ( { nodo_1, nodo_2, ..., nodo_n }, { arco_1, arco_2, ..., arco_n } ),
	 * 			 G e' un grafo orientato : l'insieme degli archi e' composto da archi aventi una direzione
	 *			 G e' un grato non pesato : Sugli archi non troviamo il peso corrispondente
	 */

	void addNode(String s, E node) throws NullPointerException;
	/**
	 * REQUIRES: s! = null && node != null &&    
	 * MODIFIES: this
	 * EFFECTS: aggiunge un nodo di tipo E con un codice identificativo univoco s
	 *			Se s == null oppure node == null lancia una NullPointerException
	 *			Se s esiste gia', non viene fatta alcuna modifica, altrimenti
	 *	        vengono aggiunte tutte le associazioni relative a s.
	 */

	void removeNode(String s) throws NullPointerException;
	/**
	 * REQUIRES: s != null
	 * MODIFIES: this
	 * EFFECTS: rimuove un nodo di tipo E avente codice identificativo univoco s
	 *			Se s == null lancia una NullPointerException;
	 *			Se s non esiste nel grafo, non viene fatta alcuna modifica, altrimenti		
	 *			vengono rimosse tutte le associazioni relative a s.
	 */

	void addEdge(String s1, String s2) throws NullPointerException;
	/**
	 * REQUIRES: s1 != null && s2 != null
	 * MODIFIES: this
	 * EFFECTS: Se s1 == null oppure s2 == null lancia una NullPointerException
	 *			Se s1 non esiste nel grafo, oppure s2 non esiste nel grafo, 
	 *			non viene fatta alcuna modifica, altrimenti viene aggiunto l'arco
	 *			che parte da s1 e va in s2.
	 */		

	void removeEdge(String s1, String s2) throws NullPointerException;
	/**
	 * REQUIRES: s1 != null && s2 != null
	 * MODIFIES: this
	 * EFFECTS: Se s1 == null oppure s2 == null lancia una NullPointerException
	 *			Se s1 non esiste nel grafo, oppure s2 non esiste nel grafo, oppure
	 *			s1 == s2 (un utente non puo' rimuovere se stesso),
	 *		    non viene fatta alcuna modifica, altrimenti viene rimosso l'arco
	 *			che parte da s1 e va in s2
	 */

	int shortestPath(String sorgente, String destinazione) throws NullPointerException;
	/**
	 * REQUIRES: sorgente != null && destinazione != null
	 * EFFECTS: Se sorgente == null oppure destinazione == null lancia una NullPointerException
	 *			Se il grafo non contiene il nodo sorgente o il nodo destinazione, oppure
	 * 			se il cammino da sorgente a destinazione non esiste, oppure 
	 *			se sorgente == destinazione, ritorna -1
	 *			Se il cammino da sorgente a destinazione esiste, ed e' il piu' corto tra i cammini,
	 *			restituisce il numero di nodi intermediari attraversati durante il cammino.
	 */

	int longestPath() throws NullPointerException;
	/**
	 * EFFECTS: se tutti i nodi presenti non hanno archi ne' entranti, ne' uscenti,
	 * 			e quindi non esistono cammini possibili, restituisce -1,
	 *			altrimenti restituisce il massimo di nodi intermediari dei cammini minimi
	 *			tra tutti i cammini minimi possibili tra due nodi qualsiasi del grafo.
	 */

	void printAllFollowing();
	/**
	 * OVERVIEW: stampa tutti i nomi degli utenti e le loro relative liste dei nomi degli utenti seguiti.
	 */
	
	void printAllUsers();
	/**
	 * OVERVIEW: stampa tutti gli id, i nickname, il numero di utenti seguiti, 
	 *  		 il numero di utenti che li seguono, degli utenti del grafo.
	 */

	boolean repOk();
	
}