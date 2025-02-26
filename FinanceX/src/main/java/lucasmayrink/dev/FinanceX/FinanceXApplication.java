package lucasmayrink.dev.FinanceX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinanceXApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceXApplication.class, args);
	}

}
