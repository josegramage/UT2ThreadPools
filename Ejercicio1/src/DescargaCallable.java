import java.util.Random;
import java.util.concurrent.Callable;

public class DescargaCallable implements Callable<String> {
	String archivo;
	int tiempoDescarga;
	
	public DescargaCallable(String archivo, int tiempoDescarga) {
		super();
		this.archivo = archivo;
		this.tiempoDescarga = tiempoDescarga;
	}
	
	@Override
	public String call() throws Exception {
		
		int id=0;
		// TODO: esperar

        Thread.sleep(tiempoDescarga);
        
      //pasamos el tiempo a segundos
      	int tiempo_segundos=tiempoDescarga/1000;
     
		// TODO: devolver mensaje "Archivo X descargado en T segundos"
		return "Archivo "+archivo+" descargado en "+tiempo_segundos+" segundos";
	}
	
}
