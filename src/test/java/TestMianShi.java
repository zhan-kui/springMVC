
import org.junit.Test;

public class TestMianShi {
    @Test
    public void test01(){
        int i = 1;
        i = i++;
        System.out.println(i);
        int j = i++;
        System.out.println(j);
       j = i;
        System.out.println("j=i =" + i);
        int d = i++;
        System.out.println(d);
        int c = i++;
        System.out.println(c);
    }
    @Test
    public void test02(){

        Singleton instance = Singleton.INSTANCE;
        System.out.println(instance); // INSTANCE
        Singleton2 singleton2 = Singleton2.INSTANCE;
        System.out.println(singleton2);
    }

}
enum Singleton{

    INSTANCE;
}
 class  Singleton2{
    public static final Singleton2 INSTANCE = new Singleton2();

private Singleton2()
{

}
 }