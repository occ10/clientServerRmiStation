
import java.lang.Exception;
import java.io.*;
import java.net.*;

public class MyHTTPServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
		/*
         * Descriptores de socket servidor y de socket con el cliente
         */
        int resultado = 0,resultado2=0;
        String Cadena = "", fich = "defect.html";
        String host = "";
        String puerto1 = "";
        String puerto2 = "";
        String p_host = "localhost";
     

        String cadena1 = "HTTP/1.1 200 OK\nDate: Sat, 15 Jan 2000 14:37:12 GMT\nServer : Microsoft-IIS/2.0\nContent-Type : text/HTML\nContent-Length : 1245\nLast-Modified : Fri, 14 Jan 2000 08:25:13 GMT\n";
        String body1 = "  <!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Title of the document</title></head><body> no se permiten mas conexiones</body></html>";

        try {

            if (args.length < 3) {
                System.out.println("Debe indicar el puerto de escucha del servidor y del controlador");
                System.out.println("$./Servidor puerto_servidor");
                System.exit(1);
            }
            host = args[0];
            puerto1 = args[1];
            puerto2 = args[2];
            resultado2 = Integer.parseInt(args[3]);
            ServerSocket skServidor = new ServerSocket(Integer.parseInt(puerto1));
            System.out.println("Escucho el puerto " + puerto1);

            /*
             * Mantenemos la comunicacion con el cliente
             */
           
            for (;;) {
                /*
                 * Se espera un cliente que quiera conectarse
                 */
               
                Socket skCliente = skServidor.accept();

                
                if(resultado<resultado2){
              Thread t = new HiloMyHTTPServer(skCliente, host, puerto2);
                t.start();
                

                 
             System.out.println("resultado:"+resultado);
                
              }else{
                     HiloMyHTTPServer t = new HiloMyHTTPServer();
                       
                      t.leeSocket(skCliente, Cadena);
                      t.escribeSocket(skCliente, cadena1);
                      t.escribeSocket(skCliente, body1);
                      t.leeSocket(skCliente, Cadena);
                      skCliente.close();

              }

                 resultado++;

            }
          
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

}
