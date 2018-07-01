/**
 *		Andrea Tosti Mat n.518111 progetto prima verifica A. A. 2016-17
 */

/**
 * OVERVIEW: Tipo nodo, con i campi essenziali per un utente di Twitter:
 *			 un nickname di tipo String
 *			 il #following (numero di utenti seguiti) di tipo int (n_following)
 *			 il #followers (numero di persone che seguono l'utente) di tipo int (n_followers)
 */

public class Node
{
	private String nickname;
	private int n_following;
	private int n_followers;

	public Node()
	{
		this("Undefined");
	}

	public Node(String nickname)
	{
		this.nickname = nickname;
		n_following = 0;
		n_followers = 0;
	}
	
	public String toString()
	{
		String s;
		s = ("Nickname : [" + this.nickname + "] N. Following : [" + this.n_following + "] N. Followers : [" + this.n_followers + "]");
		return s;
	}

	public void setNickname(String newNickname)
	{
		/**
		 * EFFECTS: modifica il nickname
		 * MODIFIES: this
	 	 */

		this.nickname = newNickname;
	}

	public String getNickname()
	{
		/**
		 * EFFECTS: restituisce il nickname
	 	 */

		return this.nickname;
	}

	public int getFollowing()
	{
		/**
		 * EFFECTS: restituisce il numero di persone seguite dall'utente
	 	 */

		return this.n_following;
	}

	public int getFollowers()
	{
		/**
		 * EFFECTS: restituisce il numero di persone che seguono l'utente 
	 	 */

		return this.n_followers;
	}

	public void incNumberOfFollowers()
	{
		/**
		 * EFFECTS: incrementa di 1 il numero di persone che seguono l'utente
	 	 */

		this.n_followers++;
	}

	public void decNumberOfFollowers()
	{
		/**
		 * EFFECTS: decrementa di 1 il numero di persone che seguono l'utente 
	 	 */

		this.n_followers--;
	}

	public void incNumberOfFollowing()
	{
		/**
		 * EFFECTS: incrementa di 1 il numero di persone seguite dall'utente 
	 	 */

		this.n_following++;
	}

	public void decNumberOfFollowing()
	{
		/**
		 * EFFECTS: decrementa di 1 il numero di persone seguite dall'utente
	 	 */

		this.n_following--;
	}
}