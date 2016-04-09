
public class main {

	public static void main(String[] args) {
		
		Pelota pelota = new Pelota();
		
		Thread players []= new Thread [10];
		for (int i=0;i<10;i++){
			players[i]= new Thread (new Ping (i, pelota));
		}
	
		for (int i=0;i<10;i++){
			players[i].start();
		}
		
		
		/*
		for (int i=0;i<10;i++){
			players[i].interrupt();
		}	
		*/
		
		System.out.println("Padre terminado");
	

	}
}
