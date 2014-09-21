package it.itba.pod.tp2;

import org.jgroups.View;

import java.util.logging.Logger;

public class Ejercicio2 extends Ejercicio1
{
    private static transient Logger LOGGER = Logger.getLogger(Ejercicio2.class.getName());

    public Ejercicio2(String username) throws Exception
    {
        super(username);
    }

    @Override
    public boolean connect(String clusterName)
    {
        if (super.connect(clusterName))
        {
            channel.setReceiver(this);
            return true;
        }
        return false;
    }

    @Override
    public void viewAccepted(View newView)
    {
        LOGGER.info("Cluster members changed: " + newView.getMembers());
    }

}
