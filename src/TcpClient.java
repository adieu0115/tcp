import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class TcpClient
{
    public static void main(String[] args)
    {
        TcpClient sample = new TcpClient();
        sample.sendSocketSample();
    }

    public void sendSocketSample()
    {
        for (int loop = 0; loop < 3; loop++)
        {
            sendSocketData("I liked java at " + new Date());
        }
        sendSocketData("EXIT");
    }

    public void sendSocketData(String data)
    {
        Socket socket = null;
        try
        {
            System.out.println("Client: Connecting");
            socket = new Socket("127.0.0.1", 9090);
            System.out.println("Client: Connect status=" + socket.isConnected());
            Thread.sleep(1000);
            OutputStream stream = socket.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(stream);
            byte[] bytes = data.getBytes();
            out.write(bytes);
            System.out.println("Client:Sent data");
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
