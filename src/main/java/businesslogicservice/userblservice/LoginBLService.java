package businesslogicservice.userblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface LoginBLService extends Remote{

	public boolean login(String id,String password) throws RemoteException;
	
	public String getUserName() throws RemoteException;
	
	public String getUserIdentity() throws RemoteException;
	
	public int getUserLevel() throws RemoteException;
}