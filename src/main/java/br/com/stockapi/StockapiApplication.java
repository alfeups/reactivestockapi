package br.com.stockapi;

import br.com.stockapi.model.Quote;
import br.com.stockapi.repository.QuoteRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Log4j2
@Slf4j
@EnableScheduling
@SpringBootApplication
public class StockapiApplication {
	@Autowired
	QuoteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StockapiApplication.class, args);
	}

	@Scheduled(fixedDelay = 1000)
	public void generatedData(){
		log.info(repository.findFirstBySymbolOrderByTimestampDesc("TEST")
				.map(this::generateNewData)
				.orElseGet(this::initializeData));
	}

	private Quote initializeData() {
		return repository.save(Quote.builder()
				.symbol("TEST")
				.openValue(0.222222)
				.closeValue(0.222222)
				.timestamp(LocalDateTime.now())
				.build());
	}

	private Quote generateNewData(Quote quote) {
		return repository.save(Quote.builder()
				.symbol(quote.getSymbol())
				.openValue(quote.getOpenValue() * randomGenerator())
				.closeValue(quote.getCloseValue())
				.timestamp(LocalDateTime.now())
				.build());
	}

	private static double randomGenerator() {
		return new RandomDataGenerator()
				.nextUniform(-0.10, 0.10);
	}

}
