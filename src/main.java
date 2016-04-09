/*
 * javygr@gmail.com
 * davidfjusto@gmail.com
*/

/**
 * \file
 * \brief Practica4: ping pong
 */


public class main {

	public static void main(String[] args) {
		int jugadores = 1;
		//int jugadores =10;
		//int jugadores =3000;

		Pelota pelota = new Pelota();

		Thread players[] = new Thread[jugadores];
		for (int i = 0; i < jugadores; i++){
			players[i]= new Thread (new Ping (i, pelota));
		}

		long start = System.currentTimeMillis();

		for (int i = 0; i < jugadores; i++){
			players[i].start();
		}

		for (int i = 0; i < jugadores; i++) {
			try {
				players[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		for (int i = 0; i < jugadores; i++){
			players[i].interrupt();
		}

		long end = System.currentTimeMillis() - start;
		System.out.println("Padre terminado");
		System.out.println("Tiempo de ejecución:" + end + "ms");
	

	}
}

/**

 \mainpage Exercise Documentation

 \author Javier Gándara Rodríguez
 \author David Fernández Justo
 \version Practica4: Ping pong

 \section SEC_OBS Contenido
 - Actividad 4.2. Implementar un juego de pingpong con hilos sincronizados



 \page PAGE_DOCU_1 Documentacion 4

 \section SEC_OBS Observaciones y Cuestiones

 2. Usa—parecido al juego real—un objeto que haga de pelota, de forma que cada jugador sepa
 cuando tiene que jugar, que será cuando la pelota esté en su posesión.
 Averigua para ello cómo se puede usar un objeto en Java para sincronizar varios hilos.\n

 Utilizando un objeto con un método sincronizado que les dé paso o no, podemos sincronizarlos para que solo uno acceda de cada vez. Utilizando el notify() desperatamos a los hilos que dejamos en espera con wait() para que vayan accediendo de forma ordenada. Si no todos dependieran del objeto pelota pero necesitaran una serie de acciones terminadas, podemos ponerlos en espera hasta que se ralicenlas acciones y lanzar un NotifyAll () para que prosigan cuando el trabajo esté realizado.

 3. Usa el hilo principal con el papel de árbitro, es decir, es el hilo que inicia y termina el partido.\n

 Según nuestra implementación, los hilos entran en bucle de juego mientras no es necesario acabar el juego, así que el árbitro (main) inicia el juego, pero no es necesario que lo acabe, ya que cuando hay que acabar el juego los hilos terminan de forma natural. De todas formas, el hilo padre trata de acabar con ellos cuando se acaba el partido.

 4. Implementa diferentes criterios de parada, por ejemplo: número de jugadas, tiempo de jugadas,
 interrupción. . . El programa debe terminar todos los hilos participantes de forma explícita. Como siempre, el hilo principal termina el programa con el último mensaje.\n

 Se calcula la parada en el método comprobarCondiciones(), tiene orden de finalizar ante un número de jugadas, pero podríamos añadir ahí los que nos parecieran apropiados.

 5. Utiliza en tu solución los métodos wait(), notify() y notifyAll() de Java. ¿Observas
 diferencias entre notify() y notifyAll()?\n

 Notify () levanta un solo hilo, el primero que fué puesto a  dormir, luego el siguiente y así consecutivamente. Hemos realizado pruebas con NotifyAll () y levanta todos los hilos en espera. Podríamos hecerlo en lugar del notify () en nuestro caso.

 6. Queremos que el árbitro decida quien será el hilo que comience a jugar.\n

 En el bucle de inicialización de los hilos, solo tendríamos que lanzar un hilo antes del bucle y realizar un if() para que no tratara de inicializarlo de nuevo en el bucle.


 7. Argumenta de forma semi-formal que tu programa es correcto y garantiza la semántica del
 juego. ¿Cómo puedes comprobar automáticamente que la salida del programa es la deseada?\n

 Podemos ver los hilos activos para comprobar que los jugadores se han cerrado correctamente y comprobar en pelota los atributos que controlan el fin del juego, para comprobar que se ha acabado por un buen motivo.

 8. Mide el tiempo de ejecución—con un criterio de terminación razonable—de tu partido variando
 el número de jugadoras de unas pocas a unas miles. ¿Qué resultado esperas para un número constante de pasar-pelota independientemente del número de jugadores? ¿Qué dicen tus mediciones?
 ¿Cuál es el método más eficiente?\n

 Esperamos un número creciente de tiempo por la gestión de tantos hilos de forma simultánea, con notify() no debería haber diferencia temporal,  pero con notifyAll() debemos esperar un tiempo de ejecución\n

 Los resultados son con 3000 jugadores a 51 jugadas 413ms y con 10 a 51 jugadas 1203 ms. Como el programa crea un cuello de botella entre los hilos, la cantidad de hilos provoca que se reduzca el tiempo del proceso, pero con NotifyAll() creemos que debería  provocar que tarde más por los tiempos de activación de los hilos. La conclusión es que muchos hilos contribuyen a que la aplicación corra más rápido.

 9. Si se interrumpe un hilo que participa en el juego ¿qué pasa con los demás? ¿Puedes sugerir un
 remedio?\n

 El juego se congelaría, pues no llegaría a la parte del notify() y no se despertarían más hilos. La solución creemos que estaría en utilizar la excepción que lanzaría un hilo interrumpido para hacer el notify.

 10. ¿Funciona tu programa concurrente con una sola jugadora también? Si no lo hace ¿qué deberías
 cambiar?\n

 Sí que funciona, pues si no se encuentra la pelota cogida por otro hilo, la vuelve a coger, sin ponerse en espera. De este modo un solo jugador jugaría inninterrumpidamente hasta que se acabara el juego sin invocar a wait() ni una sola vez, por lo que no es un problema que nadie llame a notify();





 */
