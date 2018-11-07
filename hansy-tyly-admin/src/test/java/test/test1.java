package test;

import com.google.common.base.internal.Finalizer;
import org.junit.Test;

public class test1 {

    public static Finalizer SAVE_HOOK=null;
    public static void main(String[] args) {
        System.out.println("--------------");
        System.gc();
    }

    @Override
    protected void finalize() throws Throwable{
        System.out.println("1111");
        super.finalize();
    }
    @Test
    public  void voidTest() {
       /* UserCache cache=UserCache.getInstanse();

        for(int i=0;i<100;i++){
            System.out.println(cache.getObject("123").toString());
        }
        cache.clearAll();
        System.out.println(cache.getObject("123").toString());*/
        System.out.print(3*0.1);
        Object o=new Object();
        try {
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            o.notify();
        }

        

    }

}
