import java.rmi.Remote;

public interface InterfazRemoto extends Remote {
    public String Buscar(String palabra)  throws java.rmi.RemoteException;

   public void modoModificar(String palabra,String valor) throws java.rmi.RemoteException;
   
}



