package com.bala.codechallenge.quotes;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bala.codechallenge.quotes.SpringBootQuotesService;

@RestController

public class SpringBootQuotesController {

  private SpringBootQuotesService springBootQuotesService;

  public SpringBootQuotesController(
      SpringBootQuotesService springBootQuotesService) {
    this.springBootQuotesService = springBootQuotesService;
  }

  @RequestMapping(path = "/quotes", method = RequestMethod.GET)
  public List<Quote> quotes() {
    return Arrays.asList(springBootQuotesService.getQuotes());
  }

  @RequestMapping(path = "/quotes/random", method = RequestMethod.GET)
  public Quote quote() {
    return springBootQuotesService.getRandomQuote();
  }
  
  // get Git user details from using user name
  


}
