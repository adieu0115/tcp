import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer
{
    // 0.반드시 cmd가 아닌, bash를 사용할 것.
    // 1.echo "hello world" | ncat -u localhost 7070 명령어로 통신
    // 2.printf "EXIT" | ncat -u localhost 7070 명령어로 종료
    // ex. for i in {1..100}; do echo "Packet $i" | ncat -u localhost 7070; done
    public void startServer()
    {
        DatagramSocket server = null;
        try
        {
            server = new DatagramSocket(7070, InetAddress.getByName("0.0.0.0"));
            int bufferLength = 256;
            byte[] buffer = new byte[bufferLength];
            DatagramPacket packet = new DatagramPacket(buffer, bufferLength);

            while (true)
            {
                System.out.println("Server:Waiting for request.");
                server.receive(packet);
                int dataLength = packet.getLength();
                System.out.println("Server:received. Data length=" + dataLength);
                String data = new String(packet.getData(), 0, dataLength);
                System.out.println("Received data:" + data);
                if (data.equals("EXIT"))
                {
                    System.out.println("Stop DatagramServer");
                    break;
                }
                System.out.println("--------------------------");
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (server != null)
            {
                server.close();
            }
        }
    }
}
