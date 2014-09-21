package it.itba.pod.tp2;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

public class Ejercicio1 extends ReceiverAdapter
{
    protected JChannel channel;
    protected String username;

    public Ejercicio1(String username) throws Exception
    {
        this.channel = new JChannel();
        this.username = username;
        this.channel.setName(username);
    }

    public boolean connect(String clusterName)
    {
        try
        {
            channel.connect(clusterName);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void disconnect()
    {
        channel.disconnect();
    }

    public boolean sendMessage(String message)
    {
        try
        {
            channel.send(new Message(null, null, message));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
