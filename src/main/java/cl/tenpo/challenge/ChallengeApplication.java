package cl.tenpo.challenge;

import cl.tenpo.challenge.config.ChallengeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ ChallengeProperties.class })
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
