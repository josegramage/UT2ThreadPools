import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoDescarga {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Completa una lista de al menos 10 URLS de descarga
		String urlsArchivos[] = {
				"http://sunsite.rediris.es/mirror/ubuntu-releases/16.10/ubuntu-16.10-desktop-amd64.iso",
				"http://sunsite.rediris.es/mirror/FreeBSD/ISO-IMAGES-amd64/8.2/FreeBSD-8.2-RELEASE-amd64-dvd1.iso.xz",
				"http://sunsite.rediris.es/mirror/archlinux/iso/2016.10.01/archlinux-2016.10.01-dual.iso",
				"http://sunsite.rediris.es/mirror/Lliurex/releases/14.06_64bits/releases/lliurex-escriptori-amd64_1406_20140826.iso",
				"http://sunsite.rediris.es/mirror/CentOS/7.2.1511/isos/x86_64/CentOS-7-x86_64-Everything-1511.iso",
				"http://sunsite.rediris.es/mirror/opera/desktop/15.0.1147.130/win/Opera_15.0.1147.130_Autoupdate.exe",
				"http://sunsite.rediris.es/mirror/gimp/v2.3/gimp-2.3.0.tar.bz2",
				"http://sunsite.rediris.es/mirror/cherokee/distribution/vanilla/vanilla-2.0.17.9_15.cpk",
				"http://sunsite.rediris.es/mirror/DragonFlyBSD/iso-images/dfly-i386-3.0.3_REL.img.bz2",
				"http://sunsite.rediris.es/mirror/samba/samba-3.2.15.tar.gz" };

		// TODO: crear el ExecuteService. El thread pool debe ser fijo (2
		// threads).
		// TODO: crear el CompletionService
		// TODO: iterar y añadir los callables parametrizados con urlsArchivos
		// y un tiempo variable de 10 a 20 segundos.

		// TODO: imprimir los resultados a medida que los recibimos.
		// TODO: Imprimir el estado del thread pool después de recibir un
		// archivo (toString)
		// TODO: parar el thread pool de forma ordenada.

		// se crea el ExecutorService con 2 threads
		ExecutorService threadPool = Executors.newFixedThreadPool(2);

		//se crea el CompletionService
		CompletionService<String> pool = new ExecutorCompletionService<String>(threadPool);

		//random para el tiempo de descarga
		Random rnd = new Random();
		
		
		for (int i = 0; i < urlsArchivos.length; i++) {
			String urls = urlsArchivos[i];
			
			//tiempo de descarga entre 10 y 20 segundos
			int tiempo = (int) (rnd.nextDouble() * 10000 + 11000);
			
			//a�adimos las urls y el tiempo de descarga
			pool.submit(new DescargaCallable(urls,tiempo));
			
		}

		//imprimimos los resultados
		for (int i = 0; i < urlsArchivos.length; i++) {
			String result = pool.take().get();
			System.out.println(result);
			System.out.println("Estado del thread pool: ");
			System.out.println(threadPool.toString());
			System.out.println("--- --- ---");
			
		}

		System.out.println("Se han descargado todos los archivos");
		//se para el thread pool
		threadPool.shutdown();

	}

}
