package br.com.stockapi.handler;

import br.com.stockapi.dto.QuoteDTO;
import br.com.stockapi.model.Quote;
import br.com.stockapi.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.util.Date;

@Component
@AllArgsConstructor
public class QuoteHandler {
    private final QuoteRepository quoteRepository;

    public Mono<ServerResponse> getAllQuotes(ServerRequest req) {
        Flux<QuoteDTO> quotes = quoteRepository.findAll()
                .map(this::toDTO);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(quotes, QuoteDTO.class);
    }

    private QuoteDTO toDTO(Quote q) {
        return QuoteDTO.builder()
                .closeValue(q.getCloseValue())
                .openValue(q.getOpenValue())
                .symbol(q.getSymbol())
                .timestamp(Date.from(q.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }

    public Mono<ServerResponse> getLastQuote(ServerRequest req) {
        Mono<QuoteDTO> quote = quoteRepository.findAll().last()
                .map(this::toDTO);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(quote, Quote.class);
    }
}