
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(!pelota.isFree())
			{
				 try {
			            this.wait(); 
			         } 
				 catch (InterruptedException e) {}
			}
			if(!pelota.isEndGame()){
			pelota.run(name);
			System.out.println ("El hilo "+name+" está jugando");
			}
		}
	}
}
