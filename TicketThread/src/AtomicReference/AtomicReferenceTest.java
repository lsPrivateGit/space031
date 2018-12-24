package AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

/*
 * AtomicReference 类的 实例
 * AtomicReference是作用是对"对象"进行原子操作。
 */
public class AtomicReferenceTest {
	
	public static void main(String[] args) {
		 // 创建两个Person对象，它们的id分别是101和102。
		Person p1 = new Person(101);
        Person p2 = new Person(102);
        
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        
       // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
       ar.compareAndSet(p1,p2);
       
       Person p3 = (Person)ar.get();
       System.out.println("p3 is "+p3);
       System.out.println("p3.equals(p1)="+p3.equals(p1));
	}
}
class Person {
    volatile long id;   // 使用 volatile 修饰的变量，当在高并发环境中时，id的值发生改变时，在其他线程中获取的id值也是最新发生改变的值
    public Person(long id) {
        this.id = id;
    }
    public String toString() {
        return "id:"+id;
    }
}