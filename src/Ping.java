/*
 * javygr@gmail.com
 * davidfjusto@gmail.com
*/

/**
 * \file
 * \brief Practica4: ping pong
 */

public class Ping implements Runnable {
	private int name;
	private Pelota pelota;
	
	public Ping (int nm, Pelota p){
		name=nm;
		pelota=p;
	}
	@Override
	public void run() {
		while(!pelota.isEndGame())
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			if (!pelota.isFree())
			{
				 try {
			            this.wait(); 
			         } 
				 catch (InterruptedException e) {}
			} else {

				if (!pelota.isEndGame()) {
					pelota.run(name);
					System.out.println("El hilo " + name + " est� jugando");
				}
			}
		}
	}
}
