import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ScheduledTask {
    @Test
    @Scheduled(cron = "0/5 * * * * ? ")   //每天1点添加前一天的时间的信息
    public void task1() {
        Date today = new Date();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();

        String pattern = "yyyy-MM-dd";//定义格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(yesterday);
        System.out.println(format);
    }
}
