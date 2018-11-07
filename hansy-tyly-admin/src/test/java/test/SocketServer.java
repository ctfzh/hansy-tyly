package test;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SocketServer {
    //用于保存客户端Socket
    public static List<Socket> clientSocketList = new ArrayList<Socket>();
    //服务器端口
    public static final int port = 1088;
    //服务器主机名
    public static final String address =  "192.168.0.128";
    //服务器接收消息，以日志形式记录，动态
    private static Logger log = Logger.getLogger("server");

    public static void main(String[] args) throws IOException {
        //创建ServerSocket监听
        ServerSocket serverSocket = new ServerSocket(port);
        log.log(Level.INFO, "服务器开启监听，端口：" + port);
        while (true) {
            Socket client = serverSocket.accept();//阻塞监听
            new SocketDoWith(client).start();//创建线程对其进行操作
            clientSocketList.add(client);//将链接添加到，集合中，用以群发
        }
    }

    //服务器处理客户端Socket消息线程
    static class SocketDoWith extends Thread {
        Logger log = Logger.getLogger("server");
        private Socket socket = null;

        public SocketDoWith(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            if (socket == null) return;
            try {
                String s = null;
                while (!socket.isClosed()) {
                    //将输入封装成对象流（处理起来更方便）
                    ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
                    s = (String) oi.readObject();
                    //将输入作为日志打印
                    log.log(Level.INFO, "服务器接收内容：" + s);

                    //把信息输出到，当前连接的所有客户端。
                    for (Socket client : clientSocketList) {
                        if (!client.isClosed()) {//防止发现送消息给，，断连客户端。
                            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                            oos.writeObject(s);
                            oos.flush();
                            log.log(Level.INFO, "服务器发送内容：" + s);//+ client.toString() + "  "
                        } else {//断开的Socket就移除
                            clientSocketList.remove(client);//移除
                        }
                    }
                }
            } catch (SocketException e) {
                log.log(Level.INFO, "客户端断开连接！！！");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
