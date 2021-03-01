package smartlabserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;
 
public class SmartLabServer {
 
    static SerialPort serialPort;
    static String portFileName="port";
    
    void startTX(String port)//Start the serial port communication
    {
        serialPort = new SerialPort(port); 
        try {
            serialPort.openPort();
            serialPort.setParams(115200, 8, 1, 0);
            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
            //Well, for example, we need to know what came some data, thus in the mask must have the
            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
            int mask = SerialPort.MASK_RXCHAR;
            //Set the prepared mask
            serialPort.setEventsMask(mask);
            //Add an interface through which we will receive information about events
            SerialPortReader object=new SerialPortReader();
            serialPort.addEventListener(object);
            //System.out.println(object.s1);
       
            
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
 
    String askPort()throws IOException //Ask for which port should be used
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        System.out.println("Available Ports are: ");
        String portNames[]=SerialPortList.getPortNames();
        System.out.println("Port Index\tPort Name");
        for(int i=0;i<portNames.length;i++)
            System.out.println(i+"\t\t"+portNames[i]);
        System.out.print("Enter the port index with which NodeMCU is connected: ");
        int index=Integer.parseInt(in.readLine());
        return portNames[index];
        
    }
    
    void writePortToFile(String portName) throws FileNotFoundException, IOException //Write the supplied port name to file
    {
        FileOutputStream file=new FileOutputStream(portFileName);
        byte b[]=new byte[portName.length()];
        for(int i=0;i<portName.length();i++)
            b[i]=(byte)portName.charAt(i);
        file.write(b);
        file.close();
        
    }
    
     String getPortFromFile() throws IOException //Read the port from file
    {
        String s="";int ch;
        File file = new File(portFileName);
        if(file.createNewFile())
        {
            String portName=askPort();
            writePortToFile(portName);
        }
           
            FileInputStream portFile = new FileInputStream(portFileName);
            while((ch=portFile.read())!=-1)
                s+=(char)ch;
            return s;
        /*catch(IOException | NumberFormatException e)
        {
            if(e==FileNotFoundException)
                
        }*/
    }
    
     
     
    public static void main(String[] args) throws IOException {
        
        SmartLabServer ob = new SmartLabServer();
        ob.startTX(ob.getPortFromFile());
        /*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        public void run() {
            try {
                serialPort.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
            }
        }, "Shutdown-thread"));*/
        
        
    }
 
    static class SerialPortReader implements SerialPortEventListener {
 
        String s1="";
        
        @Override
        public void serialEvent(SerialPortEvent event) {
            //Object type SerialPortEvent carries information about which event occurred and a value.
            //For example, if the data came a method event.getEventValue() returns us the number of bytes in the input buffer.
            
            if(event.isRXCHAR()){
            
                try{
                String s = serialPort.readString();
                
                if(s!=null)
                {
                    //System.out.print(s);
                    s1+=s;
                    if(s.indexOf('/')!=-1)
                    {                
                        Task obj=new Task();
                        obj.selectTask(s1);
                        System.out.println(s1);
                        s1="";
                    }
                }
                }catch(SerialPortException | IOException ee){System.out.println(ee);}
                
            }                
        }
    }
}