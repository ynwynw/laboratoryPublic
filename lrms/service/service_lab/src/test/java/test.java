import com.li.lrms.common.utils.MD5;
import org.junit.Test;

public class test {

    @Test
    public void test1(){
        String encrypt = MD5.encrypt("123");
        System.out.println(encrypt);

    }
}
