package com.example.shoeshop.Service;

import com.example.shoeshop.Repository.ICustomerRepository;
import com.example.shoeshop.entity.Customer;
import com.example.shoeshop.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
     @Autowired
    private ICustomerRepository iCustomerRepository;
    @Override
    public void saveNewUser(Customer customer) {
      Customer newCustomer = new Customer();
      newCustomer.setId(customer.getId());
      newCustomer.setAddress(customer.getAddress());
      newCustomer.setFirstName(customer.getFirstName());
      newCustomer.setLastName(customer.getLastName());
      newCustomer.setEmail(customer.getEmail());
      iCustomerRepository.save(newCustomer);
    }
}
