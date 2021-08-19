package cn.chenyuxian.discuz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		for(int i = 0 ; i < 2; i++) {
			TimerTask task = new MyTimerTask();
			
		}
	}
}


class MyTimerTask extends TimerTask{

	@Override
	public void run() {
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
	
}
