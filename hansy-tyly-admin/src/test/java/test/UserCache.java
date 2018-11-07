package test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;
import java.util.Random;

public class UserCache {
    private static UserCache cache;
    private Hashtable<String, PersonRef> userRefs;
    private ReferenceQueue<PersonTest> q;


    //persony软引用  如果一个对象具有软引用，在内存空间充足时，GC就不会回收该对象；当内存空间不足时，GC会回收该对象的内存（回收发生在OutOfMemoryError之前）。
    private class PersonRef extends SoftReference<PersonTest> {
        private String key="";
        public PersonRef(PersonTest personTest,ReferenceQueue<PersonTest> q){
            super(personTest,q);
            key=personTest.getId();
        }
    }

    //空的构造方法
    private UserCache(){
        q=new ReferenceQueue<>();

        userRefs=new Hashtable<>();
    }

    //创建实例的方法
    public static UserCache getInstanse(){
        synchronized (UserCache.class){
            if(cache==null){
                synchronized (UserCache.class){
                    cache=new UserCache();
                }
            }
        }
        return cache;
    }

    /**
     * 缓存用户数据
     * @param user
     */
    private void cacheObject(PersonTest personTest){
        try {
            cleanCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PersonRef ref = new PersonRef(personTest, q);//创建软引用
        userRefs.put(personTest.getId(), ref);//放入table
    }



    /**
     * 获取用户数据
     * @param id
     * @return
     */
    public PersonTest getObject(String id){
        PersonTest personTest=null;
        if(userRefs.containsKey(id)){
            System.err.println("从缓存中取");
            PersonRef ref = userRefs.get(id);
            personTest=ref.get();
        }
        if(personTest==null){
            System.err.println("从数据中取");
            personTest=new PersonTest();
            personTest.setId(id);
            personTest.setName("呜哈哈"+new Random().nextInt(10));
            cacheObject(personTest);
        }
        return personTest;
    }

    private void cleanCache() {
        PersonRef ref=null;
        while((ref=(PersonRef) q.poll())!=null){
            userRefs.remove(ref.key);
        }

    }

    public void clearAll(){
        cleanCache();
        userRefs.clear();
        System.gc();
        System.runFinalization();
    }


}
