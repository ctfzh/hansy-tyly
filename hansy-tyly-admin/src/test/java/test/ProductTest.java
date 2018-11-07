package test;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductTest {
    private int j;
    private Lock lock = new ReentrantLock();
    public static void main(String args[]) {
        // TODO Auto-generated method stub
        ProductTest tt = new ProductTest();
        for(int i=0;i<2;i++)
        {
            new Thread(tt.new Adder()).start();
            new Thread(tt.new Subtractor()).start();
        }
    }
    private class Subtractor implements Runnable
    {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true)
            {
              /*synchronized (ThreadTest.this) {
                  System.out.println("j--=" + j--);
                  //这里抛异常了，锁能释放吗？
              }*/
                lock.lock();
                try
                {
                    System.out.println("j--=" + j--);
                }finally
                {
                    lock.unlock();
                }
            }
        }

    }

    private class Adder implements Runnable
    {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true)
            {
              /*synchronized (ThreadTest.this) {
              System.out.println("j++=" + j++);
              }*/
                lock.lock();
                try
                {
                    System.out.println("j++=" + j++);
                }finally
                {
                    lock.unlock();
                }
            }
        }

    }
    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }
    @Test
    public void showTest(){
        String s1="ab";
        String s2="a"+"b";
        System.out.print(s1==s2);

    }

}
