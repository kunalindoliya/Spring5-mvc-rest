package guru.springframework.controllers.v1;

import guru.springframework.model.CustomerDTO;
import guru.springframework.model.CustomerListDTO;
import guru.springframework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kunal Indoliya.
 */
@Api(description = "This is my Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This gets all the customers",notes = "These are implemented notes")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers(){
       CustomerListDTO customerListDTO=new CustomerListDTO();
       customerListDTO.getCustomers().addAll(customerService.getAllCustomers());
       return customerListDTO;
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){

        return customerService.createNewCustomer(customerDTO);

    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id, customerDTO);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
       return customerService.pathCustomer(id, customerDTO);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
