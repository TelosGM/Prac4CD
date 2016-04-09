
public class Pelota{
	private int numJugadas;
	private int [] jugadas;
	private boolean lock=false;
	private boolean endGame= false;
	public Pelota (){
		numJugadas=0;
		jugadas = new int [100];
		
	}
	public boolean isEndGame(){
		return endGame;
	}

	public synchronized boolean isFree() {
		return !lock;
	}
	public void comprobarCondiciones(){
		/* Aqui se introdicen todas las diferentes formas en las que se desea detener el juego
		por el momento solo se detiene ante un numero de jugadas, pero podría recorrerse el array jugadas
		e ir contando cuántas veces aparece un jugador para detener la partida cuando un jugador juegue x
		veces.

		 */
		if (numJugadas > 50) {
			endGame=true;
		}
	}

	public void run(int i) {

		comprobarCondiciones();
		synchronized (this) {
			if (!endGame) {
				lock = true;
				numJugadas++;
				System.out.println("Llevamos " + numJugadas + "Jugadas ");
				jugadas[numJugadas] = i;
			}


		lock=false;
		
		notify();
		}
	}


}
