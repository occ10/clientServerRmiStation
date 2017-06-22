
import java.lang.reflect.Method;
import java.io.*;
import java.net.*;

import java.rmi.*;

public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String host;
        String port1, port2;
        Controller cr = new Controller();
        int i = 0;
        FileReader fr = null;

        try {

            if (args.length < 3) {
                System.out.println("Debe indicar el puerto de escucha y la direccion del servidor");
                System.out.println("$./Servidor puerto_servidor");
                System.exit(1);
            }
            host = args[0];
            port1 = args[1];
            port2 = args[2];

            ServerSocket skServidor = new ServerSocket(Integer.parseInt(port1));
            System.out.println("Escucho el puerto " + port1);

            /*
             * Mantenemos la comunicacion con el cliente
             */
            for (;;) {
                /*
                 * Se espera un cliente que quiera conectarse
                 */
                Socket skCliente = skServidor.accept();

                Thread t = new HiloController(skCliente, host, port1, port2);
                t.start();
            }
            //cr.pedirOperacion(host, port1, port2);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return;

    }

}



