package client;

import java.io.*;
import java.net.*;

public class Networking
{
    public void runClient(String ip, int port, String message)
    {
        try
        {
            Socket s = new Socket(ip,port);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(message);
            dout.flush();
            dout.close();
            s.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}