package com.vector.authorbookcommon.service.impl;


import com.vector.authorbookcommon.dto.BitcoinResponseDto;
import com.vector.authorbookcommon.dto.BookDto;
import com.vector.authorbookcommon.dto.SaveBookRequest;
import com.vector.authorbookcommon.mapper.BookMapper;
import com.vector.authorbookcommon.repository.BookRepository;
import com.vector.authorbookcommon.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final RestTemplate restTemplate;

    @Override
    public List<BookDto> findAll() {
        List<BookDto> dtoList = bookMapper.toDtoList(bookRepository.findAll());
        //set rub and usd prices
        ResponseEntity<HashMap> forEntity = restTemplate.getForEntity("https://cb.am/latest.json.php", HashMap.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", "M5wxN3Kys7vYBqy3xkhc+Q==RKsQGis2h4FM0nq7");

        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<BitcoinResponseDto> bitcoinResponseDtoResponseEntity = restTemplate.exchange("https://api.api-ninjas.com/v1/bitcoin", HttpMethod.GET, httpEntity, BitcoinResponseDto.class);

        if (forEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            HashMap<String, String> currencyResponseMap = forEntity.getBody();
            BitcoinResponseDto bitcoinResponseDto = bitcoinResponseDtoResponseEntity.getBody();
            if (currencyResponseMap != null && currencyResponseMap.containsKey("RUB")
                    && currencyResponseMap.containsKey("USD")
                    && bitcoinResponseDto != null) {

                for (BookDto bookDto : dtoList) {
                    bookDto.setPriceRUB(bookDto.getPrice() / Double.parseDouble(currencyResponseMap.get("RUB")));
                    bookDto.setPriceUSD(bookDto.getPrice() / Double.parseDouble(currencyResponseMap.get("USD")));
                    bookDto.setPriceBTC(bookDto.getPriceUSD() / Double.parseDouble(bitcoinResponseDto.getPrice()));
                }
            }
        }
        return dtoList;
    }

    @Override
    public BookDto save(SaveBookRequest bookRequest) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookRequest)));
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto findById(int id) {
        return bookMapper.toDto(bookRepository.findById(id).orElse(null));
    }
}
