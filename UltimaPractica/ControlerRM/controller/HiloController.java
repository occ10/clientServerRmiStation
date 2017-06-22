
import java.lang.reflect.Method;
import java.io.*;
import java.net.*;

import java.rmi.*;

public class HiloController extends Thread {

    private Socket skCliente;
    private String port1;
    private String port2;
    private String host;

    public HiloController(Socket p_cliente, String p_host, String p_puerto1, String p_puerto2) {
        this.skCliente = p_cliente;
        this.host = p_host;
        this.port1 = p_puerto1;
        this.port2 = p_puerto2;

    }

    /*
     * Lee datos del socket. Supone que se le pasa un buffer con hueco 
     *	suficiente para los datos. Devuelve el numero de bytes leidos o
     * 0 si se cierra fichero o -1 si hay error.
     */
    public String leeSocket(Socket p_sk, String p_Datos) {

        String cad = "", body = "", respuesta = "";
        byte bytedata[] = new byte[256];
        int leido = 0;
        try {
            InputStream aux = p_sk.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);

            //p_Datos = new String();
            leido = flujo.read(bytedata);

            if (leido > 0) {
                p_Datos = new String(bytedata, 0, (leido - 1));
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

////////////////////////
    //private void pedirOperacion(String host, String port1, String port2)
    public void run() {

        String cadenaX = "HTTP/1.1 200 OK\nDate: Sat, 15 Jan 2000 14:37:12 GMT\nServer : HiloController\nContent-Type: text/html; charset=utf-8\n";
        //System.out.println(cadenaX);
        String body = "";
        // Controller cr = new Controller();
        boolean modo = true;
        String resultado = "", Cadena = "", cadena = "", val = "";
        String val55 = "",val5="",val4="",val3="";
        char resp = 'x';
        String val6 = "";
        FileReader fr = null;
        InterfazRemoto objetoRemoto = null;
        InterfazRemoto objetoRemotoX = null;
        //InterfazRemotoY objetoRemotoY = null;
        InputStreamReader ent = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(ent);

        System.out.println(host);
        String servidor1 = "rmi://" + host + ":" + port2 + "/ObjetoRemoto";
        String servidor2 = "rmi://" + host + ":" + port2 + "/ObjetoRemotoX";
        String servidor3 = "rmi://" + host + ":" + port2 + "/ObjetoRemotoY";
        /*URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
         Method metodoAdd = URLClassLoader.class.getDeclaredMethod("addURL",
         new Class[] { URL.class });
         metodoAdd.setAccessible(true);*/

        try {
            try {
                // System.setSecurityManager(new RMISecurityManager());
                //http://192.168.181.128:9998/controladorSD/Humedad?Station=2
                val = this.leeSocket(skCliente, Cadena);
                String val1 = val.substring(val.indexOf("/") + 1, val.length());
                System.out.println(val1);
                String val2 = val1.substring(val1.indexOf("/"), val1.indexOf(" "));
                System.out.println(val2);

                if(val2.contains("Station")){
                   val3 = val2.substring(val2.indexOf("/") + 1, val2.indexOf("?"));
                          System.out.println("val 3:" + val3);
                }else{
                       val3 = val2.substring(val2.indexOf("/") + 1, val2.length());
                          System.out.println("val 3:" + val3);
                }


                if(val1.contains("Set")){
                 val4 = val2.substring(val2.indexOf("?") + 1, val2.lastIndexOf("&"));
                System.out.println("val 4:" + val4);
                if (val4.equals("Station=1")) {
                    System.out.println("son iguales");
                } else {
                    System.out.println("no son iguales");
                }

                
                 val5 = val2.substring(val2.lastIndexOf("&") + 1, val2.length());
                System.out.println(val5);
                val55 = val5 ;
                if (val5.contains("Set") && val5.length() > 4) {
                    //modo=false;
                    val55 = val5.substring(val5.lastIndexOf("?") + 1, val5.indexOf("="));
                    System.out.println("valor 55:"+val55);
                    val6 = val5.substring(val5.indexOf("=") + 1, val5.length());
                    System.out.println(val6);
                } else if(val5.contains("Set") && val5.length() < 4){
                      val55="";
                  }

                }else{
                      val4 = val2.substring(val2.indexOf("?") + 1, val2.length());

                }

                //String valor3 = valAux.substring(valAux.indexOf("?") + 1, valAux.indexOf(" "));
                //System.out.println(valor3);
                //if(!valor.equals("Temperatura")
                //objetoRemoto = (InterfazRemoto) Naming.lookup(servidor1);
                //resultado = objetoRemoto.Buscar(valor1); 
                //System.out.print( "resultado:"+resultado);

                this.escribeSocket(skCliente, cadenaX);
                switch (val3) {

                    case "Temperatura":
                    case "Humedad":
                    case "Luminosidad":
                    case "Pantalla":

                        switch (val4) {
                            case "Station=1":
                                
                                objetoRemoto = (InterfazRemoto) Naming.lookup(servidor1);

                                switch (val55) {

                                   

                                    case "Set":
                                           if(val3.equals("Pantalla")){
                                        //System.out.println("Estoy en el set");
                                        //System.out.println("val 6:"+val6);

                                        val6=URLDecoder.decode( val6, "UTF-8" );
                                        objetoRemoto.modoModificar(val3, val6);
                                        }else
                                         {

                                            resultado = "Variable no valida";
                                         }
                                        break;
                                    default:
                                                 //if(!val3.equals("Pantalla")){
                                        //System.out.println("son iguales");
                                        resultado = objetoRemoto.Buscar(val3);
                                        //System.out.println("resultado:" + resultado);
                                         /*}else
                                         {

                                            resultado = "Variable no valida";
                                         }*/
                                        break;

                                }
                                          break;
                            case "Station=2":
                                objetoRemotoX = (InterfazRemoto) Naming.lookup(servidor2);
                                switch (val55) {


                                    case "Set":
                                      if(val3.equals("Pantalla")){
                                         val6=URLDecoder.decode( val6, "UTF-8" );
                                        objetoRemotoX.modoModificar(val3, val6);

                                       }else
                                         {

                                            resultado = "Variable no valida";
                                         }
                                        break;
                                    default:
                                         // if(!val3.equals("Pantalla")){
                                         //System.out.println("son iguales");
                                        resultado = objetoRemotoX.Buscar(val3);
                                       //}else
                                       //  {

                                           // resultado = "Variable no valida";
                                        // }
                                        break;

                                }
                                  break;

                            default:

                                resultado = "Estacion no encontrada";
                                break;

                        }

                        break;
                            case "index":
		String[] names = Naming.list("//" + host + "/");
		for (int i = 0; i < names.length; i++)
			resultado+="<p>"+names[i]+"<p/>";


                            break;
                    default:
                        resultado = "Variable no valida";
                        break;
                }

                body = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Title of the document</title></head><body>" + resultado + "......</body></html>";
             // else if( Cadena.contains("Station=3")){

                // resultado = objetoRemotoY.Buscar("Temperatura");
                //System.out.println(resultado+"objetoRemotoY");
                //}
                this.escribeSocket(skCliente, body);
                this.leeSocket(skCliente, Cadena);

                skCliente.close();

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

        } catch (Exception ex) {
            System.out.println("Error al instanciar el objeto remoto " + ex);
            System.exit(0);
        }

        //objetoRemoto = null;
        //objetoRemotoX = null;
    }

}
