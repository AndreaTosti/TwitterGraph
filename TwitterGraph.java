/**
 *		Andrea Tosti Mat n.518111 progetto prima verifica A. A. 2016-17
 */

import java.util.*;

public class TwitterGraph<E> implements Graph<E>
{
	/**
	 * OVERVIEW : Ad ogni utente e' associato un nodo:
	 *	  e    { <"id_0", NodeOfUser_0 >, <"id_1", NodeOfUser_1>, <"id_2", NodeOfUser_2>, ..., <"id_n-1", NodeOfUser_n-1> }
	 * 	AF(c)  id_i e' un codice identificativo univoco per ciascun utente i.
	 *		   id_i ha associato il rispettivo nodo_i nel grafo
	 *		   id_i = prima componente di usersMap (di tipo String)
	 *		   NodeOfUser_i = seconda componente di usersMap (di tipo Node nel nostro caso)
	 *
	 *		   Un utente segue zero o piu' persone, pertanto ad ogni utente e' associata una lista delle persone seguite.
	 *         { <"id_0", FollowingListOfUser_0>, <"id_1", FollowingListOfUser_1>, ..., <"id_n-1", FollowingListOfUser_n-1> }
	 *		   id_i e' il solito codice identificativo univoco per ciascun utente.
	 *		   id_i ha associato la rispettiva lista degli utenti che segue
	 *		   id_i = prima componente di followingMap (di tipo String)
	 *		   FollowingListOfUser_i = seconda componente di followingMap (di tipo ArrayList di String)
	 *
	 * Rep I : usersMap != null && followingMap != null 
	 *		   usersMap.size() == followingMap.size()
	 *		   forall i,j . 0 <= i < j < usersMap.size() => (id_i != id_j) (Map ci garantisce che non avremo mai chiavi uguali)
	 * 		   forall i . 0 <= i < usersMap.size() => (id_i != null)
	 */

	private TreeMap<String, E> usersMap;
	private TreeMap<String, ArrayList<String>> followingMap;

	public TwitterGraph()
	{
		usersMap = new TreeMap<String, E>();
		followingMap = new TreeMap<String, ArrayList<String>>();
	}

	public void addNode(String s, E nodo) throws NullPointerException
	{
		/**
		 * OVERVIEW: Aggiunta di un nuovo utente su Twitter.
		 * REQUIRES: s! = null && node != null &&    
		 * MODIFIES: this
		 * EFFECTS: aggiunge un nodo di tipo E (l'utente) con un codice identificativo univoco s
		 *			Se s == null oppure node == null lancia una NullPointerException
		 *			Se s esiste gia', non viene fatta alcuna modifica, altrimenti
		 *	        vengono aggiunte una associazione <s, nodo> in usersMap 
		 *			e una associazione <s, following(s)> in followingMap
		 *			con la lista degli utenti seguiti (following) vuota.
		 */

		if(s == null || nodo == null) throw new NullPointerException();
		if(usersMap.containsKey(s))
		{
			//Se l'utente esiste gia', avvisa e non fare altro.
			System.out.println("L'id " + s + " e' gia' utilizzato, scegliere un altro nome");
			return;
		}
		usersMap.put(s, nodo);
		followingMap.put(s, new ArrayList<String>());
	}

	public void removeNode(String s) throws NullPointerException
	{
		/**
	 	 * OVERVIEW: Rimozione di un utente da Twitter.
		 * REQUIRES: s != null
		 * MODIFIES: this
		 * EFFECTS: rimuove un nodo di tipo E avente codice identificativo univoco s
		 *			Se s == null lancia una NullPointerException;
		 *			Se s non esiste nel grafo, non viene fatta alcuna modifica, altrimenti		
		 *			vengono rimosse le associazioni <s, nodo> in usersMap
		 *			e <s, following(s)> in followingMap.
		 *			Nello specifico, itero su tutta la followingMap, e faccio due cose distinte a seconda del caso:
		 *			se trovo l'id da eliminare, scorro tutta la sua lista (di persone seguite) e 
		 *				decremento il #followers ad ogni persona seguita;
		 *			se invece trovo un id diverso, guardo la sua lista (di persone seguite), e se questa contiene
		 *				la stringa da eliminare, decremento il #following e elimino l'id dalla loro lista.
		 *			Infine rimuovo entrambe le associazioni <s, nodo> e <s, following(s)>	  
		 */

		if(s == null) throw new NullPointerException();
		if(usersMap.containsKey(s))
		{
			try
			{				
	  			for(Iterator<Map.Entry<String, ArrayList<String>>> it_foll = followingMap.entrySet().iterator(); it_foll.hasNext(); )
	  			{
	  				Map.Entry<String, ArrayList<String>> entryFoll = it_foll.next();	  			
    				ListIterator it = (entryFoll.getValue()).listIterator();
					if(entryFoll.getKey().equals(s)) //ID da eliminare
					{
						while(it.hasNext())
						{
							String str = (String)it.next();
							((Node)usersMap.get(str)).decNumberOfFollowers();
						}
					}
					else //ID da non eliminare
					{
						while(it.hasNext())
						{
							String str = (String)it.next();
							if(str.equals(s))
							{
								it.remove();
								((Node)usersMap.get(entryFoll.getKey())).decNumberOfFollowing();
								break;
							}
						}
					}
    	  		}
      		}catch(NullPointerException e)
      		{
      			throw new NullPointerException("NullPointerException in removeNode");
      		}
		//Ora posso rimuovere l'utente
		usersMap.remove(s);
		followingMap.remove(s);
		}
		else
		{
			System.out.println("L'id " + s + " non esiste");
		}
	}

	public void addEdge(String s1, String s2) throws NullPointerException
	{
		/**
	     * OVERVIEW: l'utente s1 inizia a seguire l'utente s2 (follow).
		 * REQUIRES: s1 != null && s2 != null
		 * MODIFIES: this
		 * EFFECTS: Se s1 == null oppure s2 == null lancia una NullPointerException
		 *			Se s1 non esiste nel grafo, oppure s2 non esiste nel grafo, oppure
		 *			s1 == s2 (una persona non puo' seguire se stessa), 
		 *			oppure s1 gia' segue s2, non viene fatta alcuna modifica,
		 *		    altrimenti viene registrato il follow:
		 *			alla lista dei seguiti di s1 (followingMap.get(s1)) viene aggiunto s2,
		 *			incrementato il #Following di s1 e incrementato il #Followers di s2
		 */		

		if(s1 == null || s2 == null) throw new NullPointerException();
		if(!usersMap.containsKey(s1))
		{
			System.out.println("L'id " + s1 + " non esiste nel grafo");
			return;
		}
		if(!usersMap.containsKey(s2))
		{
			System.out.println("L'id " + s2 + " non esiste nel grafo");
			return;
		}
		if(s1.equals(s2)) 
		{
			System.out.println(s1 + " non puo' seguire se stessa");
			return;
		}

		ArrayList<String> array = followingMap.get(s1);
		if(!array.contains(s2)) //Se s1 non segue gia' s2
		{
			array.add(s2);
			E utente = usersMap.get(s1);
			E utente2 = usersMap.get(s2);
			((Node)utente).incNumberOfFollowing();
			((Node)utente2).incNumberOfFollowers();		
		}
		else
		{
			//non fare nulla, gia' c'e' il follow.
			return;
		}	
	}

	public void removeEdge(String s1, String s2) throws NullPointerException
	{
		/**
		 * OVERVIEW: l'utente s1 smette di seguire l'utente s2 (unfollow).
		 * REQUIRES: s1 != null && s2 != null
		 * MODIFIES: this
		 * EFFECTS: Se s1 == null oppure s2 == null lancia una NullPointerException
		 *			Se s1 non esiste nel grafo, oppure s2 non esiste nel grafo, oppure
		 *			s1 == s2 (una persona non puo' rimuovere se stessa),
		 *		    oppure s1 non segue s2, non viene fatta alcuna modifica,
		 *			altrimenti viene rimosso il follow:
		 *			dalla lista dei seguiti di s1 (followingMap.get(s1)) viene tolto s2,
		 *			decrementato il #Following di s1 e decrementato il #Followers di s2
		 *
		 */
		if(s1 == null || s2 == null) throw new NullPointerException();
		if(!usersMap.containsKey(s1))
		{
			System.out.println("L'id " + s1 + " non esiste nel grafo");
			return;
		}
		if(!usersMap.containsKey(s2))
		{
			System.out.println("L'id " + s2 + " non esiste nel grafo");
			return;
		}
		if(s1.equals(s2)) 
		{
			System.out.println(s1 + " non puo' rimuovere se stessa");
			return;
		}

		ArrayList<String> array = followingMap.get(s1);
		if(array.contains(s2)) //Se s1 segue s2
		{
			array.remove(s2);
			E utente = usersMap.get(s1);
			E utente2 = usersMap.get(s2);
			((Node)utente).decNumberOfFollowing();
			((Node)utente2).decNumberOfFollowers();	
		}
		else
		{
			//non fare nulla, non c'e' il follow.
		}
	}

	public int shortestPath(String sorgente, String destinazione) throws NullPointerException
	{
		/**
		 * OVERVIEW: Numero di nodi intermediari presenti tra la sorgente e la destinazione
		 * REQUIRES: sorgente != null && destinazione != null
		 * EFFECTS: Se sorgente == null oppure destinazione == null lancia una NullPointerException
		 *			Se il grafo non contiene il nodo sorgente o il nodo destinazione, oppure
		 * 			se il cammino da sorgente a destinazione non esiste, oppure 
		 *			se sorgente == destinazione, ritorna -1
		 *			Se il cammino da sorgente a destinazione esiste, ed e' il piu' corto tra i cammini,
		 *			restituisce il numero di nodi intermediari attraversati durante il cammino.
		 *			In dettaglio, esploro tutto il grafo per livelli, si ha una mappatura
		 * 			tra il livello corrente e la coda associata al livello stesso.
		 */

		if(sorgente == null || destinazione == null) throw new NullPointerException();
		if(!usersMap.containsKey(sorgente) || !usersMap.containsKey(destinazione)) return -1;
		if(sorgente.equals(destinazione)) return -1;

		HashSet<String> visited = new HashSet<String>();
		visited.add(sorgente);
		int livello = 0;
		HashMap<Integer, ArrayDeque<String>> levelMap = new HashMap<Integer, ArrayDeque<String>>();
		ArrayDeque<String> queue = new ArrayDeque<String>();
		queue.add(sorgente);
		levelMap.put(new Integer(livello), queue);

		while(!(levelMap.isEmpty()))	
		{
			if(levelMap.get(livello) == null) break;; //Mi fermo se ho finito di esplorare

			while(!levelMap.get(livello).isEmpty()) //Continuo finche' non finisco la coda del livello corrente.
			{
				String corr = levelMap.get(livello).remove();
				ArrayList<String> adj = followingMap.get(corr);
				Iterator<String> it_adj = adj.iterator();
				while(it_adj.hasNext())
				{
					String str = (String)it_adj.next();
					if(str.equals(destinazione))
					{
						return livello;
					}
					if(!visited.contains(str))
					{
						visited.add(str);
						if(levelMap.get(livello + 1) == null) //la map.get ritorna null se non esiste una mappatura per un nuovo livello
						{
							ArrayDeque<String> newDeque = new ArrayDeque<String>();
							newDeque.add(str);
							levelMap.put(new Integer(livello + 1), newDeque);
						}
						else
						{
							(levelMap.get(livello + 1)).add(str);
						}
					}
				}
			}
			livello++;
		}
		return -1;
	}


	public int longestPath() throws NullPointerException
	{
		/**
		 * OVERVIEW: Diametro del grafo : numero massimo dei possibili cammini minimi tra tutte 
		 *  		 le combinazioni possibili di due nodi diversi
		 * EFFECTS: se tutti i nodi presenti non hanno archi ne' entranti, ne' uscenti,
		 * 			e quindi non esistono cammini possibili, restituisce -1,
		 *			altrimenti restituisce il massimo tra tutti i cammini minimi possibili
		 *			tra due nodi qualsiasi del grafo.
		 */
		int max = -1;
		int temp = -1;
		Set<String> names = usersMap.keySet();
		for(String s : names)
		{
			for(String s2 : names)
			{
				temp = shortestPath(s, s2);
				if(temp > max)
				{
					max = temp;
				}
			}
		}
		return max;		
	}

	public void printAllFollowing()
	{
		/**
	 	 * OVERVIEW: stampa tutti i nomi degli utenti e le loro relative liste dei nomi delle persone seguite.
		 */
		Set<String> a = usersMap.keySet();
		if(a != null)
		{
			for(String nome : a)
			{
				ArrayList<String> array = followingMap.get(nome);
				if(array != null)
				{
					System.out.print(((Node)usersMap.get(nome)).getNickname() + " segue { ");
					for(String s : array)
					{
						System.out.print(((Node)usersMap.get(s)).getNickname() + " ");
					}
					System.out.println("}");
				}
			}
		}
	}

	public void printAllUsers()
	{
		/**
		 * OVERVIEW: stampa tutti gli id, i nickname, il numero di persone seguite, 
		 *  		 il numero di persone che li seguono, degli utenti del grafo.
		 */
		for(String i: usersMap.keySet())
		{
			System.out.println("ID: [" + i + "] " + usersMap.get(i).toString());
		}
	}
	
	public boolean repOk()
	{
		if(usersMap == null || followingMap == null) return false;
		if(usersMap.size() != followingMap.size()) return false;
		for(String s : usersMap.keySet())
		{
			if(s == null) return false;
		}
		return true;
	}
}