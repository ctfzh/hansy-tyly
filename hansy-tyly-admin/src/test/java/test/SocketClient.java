package test;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClient {
    public static final int port = 1088;
    public static final String address = "192.168.0.128";
    private static Logger log = Logger.getLogger("client");
    //为了方便使用直接定义为静态属性，，调用很方便。
    private static Socket client = null;
    //给客户端起个名字
    private static String ClientName = "老刘";

    public static void main(String[] args) throws IOException, InterruptedException {
        client = new Socket(address, port);
        new SendThread().start();
        new ReceiveThread().start();
        while (!client.isClosed()) {
            Thread.sleep(1000);
        }
    }

    /**
     * 发送Socket的socket
     *
     * @param outStr 待发送信息
     * @param client 客户端Socket
     */
    private static void sendSocketServer(String outStr, Socket client) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(outStr);
            oos.flush();//刷新，将流发出去
//            log.log(Level.INFO, "客户端，" + SocketClient.class.getName() + "发送了：" + outStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息线程
     */
    static class SendThread extends Thread {
        @Override
        public void run()  {
            super.run();

            while (!client.isClosed()) {//插入停止条件
                Scanner in = new Scanner(System.in);//接收输入
                String inputStr="";
                if(in.hasNext()){
                    inputStr+= in.next();
                }

                if (inputStr != null) {
                    sendSocketServer(ClientName + "：" + inputStr, client);//发送消息
                }
            }
        }
    }

    /**
     * 接受信息线程，，运行在后台，，等待输入信息
     */
    static class ReceiveThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                InputStream serverInputStream = client.getInputStream();
                while (!client.isClosed() && serverInputStream != null) {//插入停止条件
                    ObjectInputStream ois = new ObjectInputStream(serverInputStream);
                    String inputStr = (String) ois.readObject();
                    //获取输出流，经过测试发现，输入流貌似是阻塞，，也就是没有输入时，
                    // 他就停在这里了，，一直等着输入，，所以无需加入Thread.sleep().
                    System.out.println(inputStr);
                }
            } catch (SocketException e) {
                log.log(Level.INFO, "服务器断开连接！！！");
                try {
                    client.close();//服务器断开连接此时需要关闭客户端连接
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
