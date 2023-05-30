package sr.ice.client;

import Demo.CalcPrx;
import com.zeroc.Ice.*;

import java.io.IOException;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class IceClient {
	public static void main(String[] args) {
		int status = 0;
		Communicator communicator = null;

		try {
			// 1. Inicjalizacja ICE
			communicator = Util.initialize(args);

			// 2. Uzyskanie referencji obiektu na podstawie linii w pliku konfiguracyjnym (wówczas aplikację należy uruchomić z argumentem --Ice.config=config.client)
			//ObjectPrx base1 = communicator.propertyToProxy("Calc1.Proxy");

			// 2. Uzyskanie referencji obiektu - to samo co powyżej, ale mniej ładnie
			ObjectPrx base1 = communicator.stringToProxy("calc/calc11:tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z"); //opcja -z włącza możliwość kompresji wiadomości

			// 3. Rzutowanie, zawężanie (do typu Calc)
			CalcPrx obj1 = CalcPrx.checkedCast(base1);
			if (obj1 == null) throw new Error("Invalid proxy");

			CompletableFuture<Long> cfl = null;
			String line = null;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			float r;
			float[] array = {4f, 6f, 7f};

			// 4. Wywołanie zdalnych operacji i zmiana trybu proxy dla obiektu obj1
			do {
				try {
					System.out.print("==> ");
					line = in.readLine();
					switch (line) {
						case "add":
							r = obj1.add(7.1234321f, 8.235313245f);
							System.out.println("RESULT = " + r);
							break;
						case "avg":
							Optional<Boolean> empty = Optional.empty();

							r = obj1.avg(array, empty);
							System.out.println("RESULT = " + r);
							break;
						case "avg-geo":
							Optional<Boolean> geometric = Optional.of(true);

							r = obj1.avg(array, geometric);
							System.out.println("RESULT = " + r);
							break;
						case "setprec2":
							obj1.setPrecision(2);
							break;
						case "setprec4":
							obj1.setPrecision(4);
							break;


						/* PONIŻEJ USTAWIANIE TRYBU PRACY PROXY */

						case "set-proxy twoway":
							obj1 = obj1.ice_twoway();
							System.out.println("obj1 proxy set to 'twoway' mode");
							break;
						case "set-proxy oneway":
							obj1 = obj1.ice_oneway();
							System.out.println("obj1 proxy set to 'oneway' mode");
							break;
						case "set-proxy datagram":
							obj1 = obj1.ice_datagram();
							System.out.println("obj1 proxy set to 'datagram' mode");
							break;
						case "set-proxy batch oneway":
							obj1 = obj1.ice_batchOneway();
							System.out.println("obj1 proxy set to 'batch oneway' mode");
							break;
						case "set-proxy batch datagram":
							obj1 = obj1.ice_batchDatagram();
							System.out.println("obj1 proxy set to 'batch datagram' mode");
							break;
						case "flush": //sensowne tylko dla operacji wywoływanych w trybie batch
							obj1.ice_flushBatchRequests();
							System.out.println("Flush DONE");
							break;
						case "x":
						case "":
							break;
						default:
							System.out.println("???");
					}
				} catch (IOException | TwowayOnlyException ex) {
					ex.printStackTrace(System.err);
				}
			}
			while (!Objects.equals(line, "x"));


		} catch (LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) { //clean
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}