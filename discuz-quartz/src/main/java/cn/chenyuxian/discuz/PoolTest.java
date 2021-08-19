package cn.chenyuxian.discuz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolTest {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		Executors.newSingleThreadExecutor();
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		scheduledExecutorService.scheduleAtFixedRate(new MyTimerTask(), 10, 2, TimeUnit.SECONDS);
	}
}
