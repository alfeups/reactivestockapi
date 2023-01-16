package br.com.stockapi.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
public class QuoteDTO {
    @Id
    private Long id;
    private String symbol;
    private Double openValue;
    private Double closeValue;
    private Date timestamp;
}