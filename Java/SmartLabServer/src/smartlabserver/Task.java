package smartlabserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import jssc.SerialPort;
import jssc.SerialPortException;


public class Task
{
    String usersFile="users";
    SmartLabServer ob;
    /*Task readFile(String fname)
    {}*/
    
    void serialWrite(String s) throws IOException
    {
        ob=new SmartLabServer();
        SerialPort sp=SmartLabServer.serialPort;//new SerialPort(ob.getPortFromFile());
        try
        {
            /*sp.openPort();
            sp.setParams(9600, 8, 1, 0);
            int mask = SerialPort.MASK_RXCHAR;
            sp.setEventsMask(mask);*/    
            sp.writeString(s);
            s="";
            
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
    
    String login(String uname, String pass, String type) throws FileNotFoundException //Return found if username and password match & notfound if does not match
    {
        File file=new File(usersFile);
        Scanner sc=new Scanner(file);
        sc.useDelimiter("\n");
        if(!(type.equals("admin")))
            sc.nextLine();
        while(sc.hasNextLine())
        {
            String s=sc.nextLine();
            String s1=s.substring(0,s.indexOf(' '));
            String s2=s.substring(s.indexOf(' ')+1);
            //System.out.println(uname+pass+type);
            if(type.equals("admin"))
            {
                if(s1.equals(uname) && s2.equals(pass))
                    {return "found";}
                else
                    {return "notfound";}
            }
            else if(s1.equals(uname) && s2.equals(pass))
                {return "found";}
                
        }
        return "notfound";
    }

    void selectTask(String s) throws FileNotFoundException, IOException
    {
        char ch=s.charAt(0);
        int p1,p2,p3,p4;
        switch(ch)
        {
            case '1':
                p1=s.indexOf(' ')+1;
                p2=s.indexOf(' ', p1)+1;
                p3=s.indexOf(' ', p2)+1;
                p4=s.indexOf('/');
                //System.out.println(s.substring(p1,p2)+" "+s.substring(p2,p3)+" "+s.substring(p3,p4));
                serialWrite(login(s.substring(p1,p2-1), s.substring(p2,p3-1), s.substring(p3,p4)));
            case '2':
        }
    }
}
