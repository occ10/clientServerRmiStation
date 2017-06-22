
import java.lang.Exception;
import java.io.*;
import java.net.*;
import java.util.*;

public class HiloMyHTTPServer extends Thread {

    private Socket skCliente;
    private String host;
    private String port;

    public HiloMyHTTPServer(Socket p_cliente, String p_host, String p_port) {
        this.skCliente = p_cliente;
        this.host = p_host;
        this.port = p_port;
    }
    public HiloMyHTTPServer(){

    }


    /*
     * Lee datos del socket. Supone que se le pasa un buffer con hueco 
     *	suficiente para los datos. Devuelve el numero de bytes leidos o
     * 0 si se cierra fichero o -1 si hay error.
     */

public void leerFichero(Socket skCliente,String fich){

String cadena="";
FileReader fr=null;
                       try{
                        fr = new FileReader(fich);
                        BufferedReader entrada = new BufferedReader(fr);

                       //this.escribeSocket(skCliente, cadena);
                        cadena = entrada.readLine();

                        while (cadena != null) {
                            this.escribeSocket(skCliente, cadena);
                            cadena = entrada.readLine();
//System.out.println(cadena);

                        }  
                       } catch (FileNotFoundException e) {

                        System.out.println(e.getMessage());
                    } catch(IOException e){

                       System.out.println(e.getMessage());
                       }finally {
                        try {
                            if (fr != null) {
                                fr.close();
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }

}
    public void run() {
       
        int resultado = 0;
        String Cadena = "", fich = "error.html";
        FileReader fr = null;
        String cadena = "HTTP/1.1 200 OK\nDate: Sat, 15 Jan 2000 14:37:12 GMT\nServer : Microsoft-IIS/2.0\nContent-Type: text/html; charset=utf-8\n";
        String body = "  <!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Title of the document</title></head><body> 405 Method Not Allowed</body></html>";

        try {
              //Thread.sleep(7500);
            Cadena = this.leeSocket(skCliente, Cadena);
            System.out.print("CADAA:"+Cadena);
if(!Cadena.equals("")){
            StringTokenizer st = new StringTokenizer(Cadena);
            String val1=st.nextToken();
            String val2=st.nextToken();
            String val3=st.nextToken();
           // System.out.println(Cadena);

            if (val1.equalsIgnoreCase("GET")) {
//System.out.print("GET");
                if (val2.startsWith("/controladorSD")) {

                    Socket controler = new Socket(host, Integer.parseInt(port));
                    this.escribeSocket(controler, Cadena);
                    Cadena = this.leeSocket(controler, Cadena);
                           System.out.print("CADXX:"+Cadena);
                               //while(Cadena!=null){
                    this.escribeSocket(skCliente, Cadena);
                    Cadena = this.leeSocket(controler, Cadena);
                          System.out.println("CADYY:"+Cadena);
                    this.escribeSocket(skCliente, Cadena);

                               //}
                    //sr.leeSocket(skCliente, Cadena); 
                    controler.close();
                    //System.exit(0);	

                } else {
                    //try {
                        if (val2.equalsIgnoreCase("/") || val2.equalsIgnoreCase("/index.html") || val2.equalsIgnoreCase("/index.htm")){

                            fich = "index.html";
                            //System.out.print("fich");
                          
                        }
                          else {
                             String val6= val2.replaceFirst("/","");

                             if(new File(val6).isFile()){
                                   fich = val6;
                              }
                          
                        }
                       // System.out.println(fich);
                        this.escribeSocket2(skCliente, cadena);
                           leerFichero(skCliente,fich);

                      
                        


                }

            } else {
                //System.out.println("Escucho el puerto ");
                this.escribeSocket2(skCliente, cadena);
                this.escribeSocket2(skCliente, body);

            }

            this.leeSocket(skCliente, Cadena);
            //System.out.println(Cadena);

           
         }
 skCliente.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());

        }

    }

    public String leeSocket(Socket p_sk, String p_Datos) {
/*try {
p_Datos="";
InputStream aux = p_sk.getInputStream();
        BufferedReader  lineas = new BufferedReader(new InputStreamReader(aux, "UTF-8"));
        return p_Datos=lineas.readLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

return p_Datos;*/

        String cad = "", body = "", respuesta = "";
        byte bytedata[] = new byte[300];
        int leido = 0;
        try {
            InputStream aux = p_sk.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            System.out.println("flujo:"+flujo);
            //p_Datos = new String();
            leido = flujo.read(bytedata);

            if (leido > 0) {
                p_Datos = new String(bytedata, 0, (leido - 1));
            } else {
                p_Datos = "";
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return p_Datos;
    }

    /*
     * Escribe dato en el socket cliente. Devuelve numero de bytes escritos,
     * o -1 si hay error.
     */
    public void escribeSocket(Socket p_sk, String p_Datos) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(p_sk.getOutputStream(), true);

            out.println(p_Datos);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return;
    }

    public void escribeSocket2(Socket p_sk, String p_Datos) {
        try {
            OutputStream aux = p_sk.getOutputStream();
            DataOutputStream flujo = new DataOutputStream(aux);
            flujo.writeUTF(p_Datos);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return;
    }

}
