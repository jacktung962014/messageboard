package com.example;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 允許JPA使用審計功能(可記錄時間等等)
@SpringBootApplication
public class MessageboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageboardApplication.class, args);
		//啟動伺服器後自動導向至首頁
		try {
			InetAddress addr = InetAddress.getLocalHost();
			System.out.println("Local HostAddress: " + addr.getHostAddress());
			Runtime.getRuntime().exec("cmd    /c start     http://" + addr.getHostAddress() + ":8080");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
