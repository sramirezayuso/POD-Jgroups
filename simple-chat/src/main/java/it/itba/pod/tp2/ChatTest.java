package it.itba.pod.tp2;

import it.itba.pod.tp2.SimpleChat;

public class ChatTest
{
    public static void main(String[] args)
    {
        try
        {
            SimpleChat chat = new SimpleChat("TestUser");
            chat.connect("TestChat");
        }
        catch (Exception e)
        {

        }
    }
}
