
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjetoRemotoX extends UnicastRemoteObject
        implements InterfazRemoto, Serializable {

    public ObjetoRemotoX() throws RemoteException {
        super();
    }

    public String Buscar(String palabra) {

        FileReader fr = null;
        String Cadena = "";
        String valor = "";
        try {

            fr = new FileReader("datos2.txt");
            BufferedReader entrada = new BufferedReader(fr);

                   // String cadena = "HTTP/1.1 200 OK Date: Sat, 15 Jan 2000 14:37:12 GMT Server : Microsoft-IIS/2.0 Content-Type : text/HTML Content-Length : 1245 Last-Modified : Fri, 14 Jan 2000 08:25:13 GMT";
            Cadena = entrada.readLine();

            while (Cadena != null) {
                //sr.escribeSocket(skCliente, Cadena);
                if (Cadena.contains(palabra)) {
                     if(Cadena.contains(":")){
                    valor = Cadena.substring(Cadena.indexOf(":") + 1, Cadena.length());
                    }else{
                     valor = Cadena.substring(Cadena.indexOf("=") + 1, Cadena.length());
                    }
                    break;
                }
                Cadena = entrada.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return valor;

    }



public void modoModificar(String palabra,String valor){

FileReader fr = null;
        PrintWriter salida = null;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> st = new ArrayList();

        try {
            //File fr2 = new File("fich.txt");
            fr = new FileReader("datos2.txt");
            //File ruta = new File("datos.txt");
            //ruta.createNewFile();
            //salida = new PrintWriter(ruta);

            BufferedReader entrada = new BufferedReader(fr);
            String cadena = entrada.readLine();
            while (cadena != null) {
                //System.out.println(cadena);
                if (cadena.contains(palabra)) {

                    if(palabra.equals("Temperatura"))
                    st.add("Temperatura:" + valor + " ºC");
                    else if(palabra.equals("Pantalla"))
                    st.add(palabra+"=" + valor);
                    else
                    st.add(palabra+":" + valor);
                   //salida.println("Temperatura=" +100 +"ºC");

                } else
                      st.add(cadena);
                 //System.out.println(cadena.substring(0, cadena.indexOf("=")+1));

                cadena = entrada.readLine();
            }

            //salida.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();

                    

                }
                 salida = new PrintWriter("datos2.txt");
                for (String cad : st) {
                    System.out.print(cad);
                    salida.println(cad);
                    //System.out.println(i);
                }
                salida.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}


