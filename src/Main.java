import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static void main(String[] args)
    {
        Main main = new Main();
        main.startServer();
    }

    public void startServer()
    {
        ServerSocket server = null;
        Socket client = null;

        try
        {
            server = new ServerSocket(9090);
            while (true)
            {
                System.out.println("Server:Waiting for request");

                client = server.accept();
                System.out.println("Server:Accepted.");

                InputStream stream = client.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;

                StringBuilder receivedData = new StringBuilder();
                while ((data = in.readLine()) != null)
                {
                    receivedData.append(data);
                }
                System.out.println("Server: ReceivedData = " + receivedData);
                in.close();
                stream.close();
                client.close();
                if (receivedData != null && "EXIT".equals(receivedData.toString()))
                {
                    System.out.println("Stop SockerServer");
                    break;
                }
                System.out.println("------------------");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (server != null)
            {
                try
                {
                    server.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}