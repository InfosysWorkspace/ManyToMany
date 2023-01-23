package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

import java.util.List;

public interface BankServices {
    public Integer addCustomerAndService(CustomerDTO customerDTO) throws InfyBankException;

    public void addExistingServiceToExistingCustomer(Integer customerId, List<Integer> serviceIds) throws  InfyBankException;

    public void deallocateServiceForExistingCustomer(Integer customerId, List<Integer> serviceIds) throws InfyBankException;

    public void deleteCustomer(Integer customerId) throws InfyBankException;

    public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;
}
