package server;

import java.io.*;
import java.net.*;

public class Networking
{
    public void runSerer()
    {
        try
        {
            ServerSocket ss=new ServerSocket(6666);
            while(true)
            {
                Socket s=ss.accept();
                DataInputStream dis=new DataInputStream(s.getInputStream());
                String str=(String)dis.readUTF();
                System.out.println("Message = "+str);
                //ss.close();
            }
        }
        catch(Exception e)
        {System.out.println(e);}
    }
    public static void main(String[] args) {
        Networking ob = new Networking();
        ob.runSerer();
    }
}
