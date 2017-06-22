
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class Registro {

    public static void main(String args[]) {
        String URLRegistro;
        String URLRegistroX;
        try {
            System.setSecurityManager(new RMISecurityManager());
            ObjetoRemotoX objetoRemotoX = new ObjetoRemotoX();

            URLRegistroX = "/ObjetoRemotoX";
            Naming.rebind(URLRegistroX, objetoRemotoX);

            ObjetoRemoto objetoRemoto = new ObjetoRemoto();

            URLRegistro = "/ObjetoRemoto";
            Naming.rebind(URLRegistro, objetoRemoto);

            System.out.println("Servidor de objeto preparado.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
