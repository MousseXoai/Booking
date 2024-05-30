package org.booking.bookingapp.service.customer;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddCustomerDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.repository.CustomerRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{

    private CustomerRepository customerRepository;
    private UsersRepository usersRepository;

    @Override
    public Customer createCustomer(AddCustomerDTO addCustomerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(addCustomerDTO.getFirstName());
        customer.setLastName(addCustomerDTO.getLastName());
        customer.setAddress(addCustomerDTO.getAddress());
        customer.setPhoneNumber(addCustomerDTO.getPhoneNumber());
        customer.setAvatar(addCustomerDTO.getAvatar());
        customer.setUserId(usersRepository.findById(addCustomerDTO.getUserId()).orElseThrow(()->new NotFoundException("Doesn't exist that userid in system")));
        return customerRepository.save(customer);
    }
}
