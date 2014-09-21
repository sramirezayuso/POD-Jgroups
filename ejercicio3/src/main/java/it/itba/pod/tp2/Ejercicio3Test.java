package it.itba.pod.tp2;

import org.jgroups.Address;

public class Ejercicio3Test
{
    public static void main(String[] args)
    {
        try
        {
            Ejercicio3 ejercicio3 = new Ejercicio3("TestUser");
            ejercicio3.connect("TestCluster");

            for (Address connectedUser : ejercicio3.getConnectedUsers())
            {
                ejercicio3.sendPersonalMessage("Test Message", connectedUser);
            }

            Thread.sleep(5000);
            ejercicio3.disconnect();
        }
        catch (Exception e)
        {

        }
    }
}
