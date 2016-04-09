
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
	public boolean isFree(){
		return !lock;
	}
	public void comprobarCondiciones(){
		if(numJugadas > 2){
			endGame=true;
		}
	}
	
	public synchronized void run(int i){
		
		//comprobarCondiciones();
		if(!endGame){
			lock=true;
			numJugadas++;
			System.out.println("Llevamos "+numJugadas + "Jugadas ");
			jugadas[numJugadas]=i; 
		}
		lock=false;
		
		notify();
		}
	
	

}
