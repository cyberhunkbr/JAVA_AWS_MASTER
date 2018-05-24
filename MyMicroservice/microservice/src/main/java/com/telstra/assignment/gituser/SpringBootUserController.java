package com.telstra.assignment.gituser;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class SpringBootUserController {

  private SpringBootUsersService springBootQuotesService;

  public SpringBootUserController(SpringBootUsersService springBootQuotesService) {
    this.springBootQuotesService = springBootQuotesService;
  }
  
  @RequestMapping(path = "/users", method = RequestMethod.GET)
  public Users user(@RequestParam(value = "name") String name) {
	   return springBootQuotesService.getUserDetails(name);
 
  }
  

/*	  @HystrixCommand(commandProperties = {
	  			@HystrixProperty(name = "execution.timeout.enabled", value = "false") }, fallbackMethod = "fallBackMethod")
	  	@RequestMapping(path = "/users", method = RequestMethod.GET)
	  	public ResponseEntity<?> users(@RequestParam(value = ServiceConstants.Q_PARAM, defaultValue = ServiceConstants.Q_FOLLOWER_VALUE) String q,
	  			@RequestParam(value = ServiceConstants.SORT_PARAM, defaultValue = ServiceConstants.SORT_USER_VALUE) String sort,
	  			@RequestParam(value = ServiceConstants.ORDER_PARAM, defaultValue = ServiceConstants.ORDER_ASC_VALUE) String order,
	  			@RequestParam(value = ServiceConstants.LIMIT_PARAM) String limit) {
	  		try {
	  			return new ResponseEntity<>(springBootUsersService.getUsers(q, sort, order, Integer.parseInt(limit)),
	  					HttpStatus.OK);
	  		} catch (NumberFormatException e) {
	  			log.error("NumberFormatException ocurred");
	  			ErrorResponse myResponse = new ErrorResponse("Error", "Please provide numbers as a limit");
	  			return new ResponseEntity<>(myResponse, HttpStatus.BAD_REQUEST);
	  		}
	  	} 
	  	
	  public ResponseEntity<?> fallBackMethod(String q, String sort, String order, String limit) {
		ErrorResponse myResponse = new ErrorResponse("Error", "Service not available. Please try after sometime!");
		return new ResponseEntity<>(myResponse, HttpStatus.SERVICE_UNAVAILABLE);
	} 
*/
  
}
