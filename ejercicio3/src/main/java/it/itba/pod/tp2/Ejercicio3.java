package it.itba.pod.tp2;

import org.jgroups.Address;
import org.jgroups.Message;

import java.util.List;

public class Ejercicio3 extends Ejercicio2
{
    public Ejercicio3(String username) throws Exception
    {
        super(username);
    }

    public List<Address> getConnectedUsers()
    {
        return channel.getView().getMembers();
    }

    public boolean sendPersonalMessage(String message, Address address)
    {
        try
        {
            channel.send(new Message(address, null, message));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
