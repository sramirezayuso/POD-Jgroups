package it.itba.pod.tp2;

import org.jgroups.*;
import org.jgroups.util.UUID;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleChat extends ReceiverAdapter
{
    private static transient Logger LOGGER = Logger.getLogger(SimpleChat.class.getName());

    private JChannel channel;
    private String username;

    public SimpleChat(String username) throws Exception
    {
        this.channel = new JChannel();
        this.username = username;
        this.channel.setName(username);
    }

    public void connect(String chatName)
    {
        try
        {
            channel.connect(chatName);
            channel.setReceiver(this);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                try
                {
                    System.out.print("> "); System.out.flush();
                    String line = in.readLine().toLowerCase();
                    if (line.startsWith("disconnect"))
                    {
                        disconnect();
                        break;
                    }
                    else if (line.matches("^#.*:.*$"))
                    {
                        Pattern namePattern = Pattern.compile("#(.*?):");
                        Matcher nameMatcher = namePattern.matcher(line);

                        nameMatcher.find();
                        line = "{" + username + "} " + line;
                        sendPersonalMessage(line, UUID.getByName(nameMatcher.group(1)));
                    }
                    else
                    {
                        line = "<" + username + "> " + line;
                        sendMessage(line);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    LOGGER.severe("Failed to send message");
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.severe("Failed to connect to chat");
        }
    }

    public void disconnect()
    {
        channel.disconnect();
    }

    public List<Address> getConnectedUsers()
    {
        return channel.getView().getMembers();
    }

    public boolean sendPersonalMessage(String message, Address address) throws Exception
    {
        channel.send(new Message(address, null, message));
        return true;
    }

    public boolean sendMessage(String message) throws Exception
    {
        channel.send(new Message(null, null, message));
        return true;
    }

    @Override
    public void viewAccepted(View newView)
    {
        LOGGER.info("Chat members changed, current chat members: " + newView.getMembers());
    }

    @Override
    public void receive(Message message) {
        System.out.println("" + message.getObject());
        System.out.print("> "); System.out.flush();
    }
}
