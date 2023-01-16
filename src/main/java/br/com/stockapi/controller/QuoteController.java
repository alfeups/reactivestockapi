package br.com.stockapi.controller;

import br.com.stockapi.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/quotes")
public class QuoteController {

    private final QuoteRepository quoteRepository;

    /*
    private final QuoteRepository quoteRepository;
    @GetMapping
    public Flux<Quote> getAll(){
        return quoteRepository.findAll();
    }*/

}
