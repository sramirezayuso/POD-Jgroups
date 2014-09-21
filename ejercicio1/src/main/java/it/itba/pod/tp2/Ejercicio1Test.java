package it.itba.pod.tp2;

public class Ejercicio1Test
{
    public static void main(String[] args)
    {
        try
        {
            Ejercicio1 ejercicio1 = new Ejercicio1("TestUser");
            ejercicio1.connect("TestCluster");
            Thread.sleep(5000);
            ejercicio1.disconnect();
        }
        catch (Exception e)
        {

        }
    }
}
