package org.booking.bookingapp.service.customer;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.CustomerRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{

    private CustomerRepository customerRepository;
    private UsersRepository usersRepository;

    @Override
    public MessageResponse createCustomer(AddCustomerDTO addCustomerDTO) {

        if(customerRepository.findById(addCustomerDTO.getUserId()).isPresent()){
            return MessageResponse.builder().message("Customer already exists").statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

        Users user = customerRepository.findUserIsCustomer().stream()
                .filter(users -> users.getUserId().equals(addCustomerDTO.getUserId()))
                .findFirst()
                .orElseThrow(() -> new ApiRequestException("Doesn't create because that user isn't customer role"));

        Customer customer = new Customer();
        customer.setUserId(usersRepository.findById(user.getUserId()).orElseThrow(()->new NotFoundException("Doesn't exist that userid in system")));
        customer.setFirstName(addCustomerDTO.getFirstName());
        customer.setLastName(addCustomerDTO.getLastName());
        customer.setAddress(addCustomerDTO.getAddress());
        customer.setPhoneNumber(addCustomerDTO.getPhoneNumber());
        customer.setAvatar(addCustomerDTO.getAvatar());
        customerRepository.save(customer);
        return MessageResponse.builder().message("Customer register successfully").statusCode(HttpStatus.OK.value()).build();
    }
}
