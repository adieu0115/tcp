import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer
{
    // 0.반드시 cmd가 아닌, bash를 사용할 것.
    // 1.echo "hello world" | ncat localhost 9090 명령어로 통신
    // 2.printf "EXIT" | ncat localhost 9090 명령어로 종료
    public void startServer()
    {
        ServerSocket server = null;
        Socket client = null;

        try
        {
            server = new ServerSocket(9090);
            while (true)
            {
                System.out.println("Server: Waiting for request");

                client = server.accept();
                System.out.println("Server: Accepted.");

                InputStream stream = client.getInputStream();
                StringBuilder receivedData = new StringBuilder();
                int data;

                // 데이터를 한 바이트씩 읽어서 StringBuilder에 추가
                while ((data = stream.read()) != -1)
                { // EOF에 도달하면 -1 반환
                    receivedData.append((char) data);
                }

                System.out.println("Server: ReceivedData = " + receivedData);

                stream.close();
                client.close();

                // 종료 조건 확인
                if ("EXIT".equals(receivedData.toString().trim()))
                {
                    System.out.println("Stop SocketServer");
                    break;
                }
                System.out.println("------------------");
            }
        }
        catch (Exception e)
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
