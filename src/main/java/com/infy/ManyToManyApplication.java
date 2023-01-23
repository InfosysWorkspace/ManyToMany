package com.infy;

import com.infy.dto.CustomerDTO;
import com.infy.dto.ServicesDTO;
import com.infy.service.BankServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(ManyToManyApplication.class);

	@Autowired
	BankServices bankServices;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
//		addCustomerAndService();
//		addExistingServiceToExistingCustomer();
//		deallocateServiceForExistingCustomer();
//		deleteCustomer();
		getCustomer();
	}

	public void addCustomerAndService(){
		try{
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setDateOfBirth(LocalDate.of(1993, 2, 1));
			customerDTO.setEmailId("maiwada@infy.com");
			customerDTO.setName("Maiwada");

			Set<ServicesDTO> servicesList = new LinkedHashSet<ServicesDTO>();
			ServicesDTO servicesDTO = new ServicesDTO();
			servicesDTO.setServiceId(3004);
			servicesDTO.setServiceName("Demat Services");
			servicesDTO.setServiceCost(200);
			servicesList.add(servicesDTO);
			customerDTO.setBankServices(servicesList);
			Integer customerId = bankServices.addCustomerAndService(customerDTO);

			LOGGER.info(environment.getProperty("UserInterface.NEW_CUSTOMER_SUCCESS") +customerId);
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more detatils!!");
			LOGGER.info(message);
		}
	}

	public void addExistingServiceToExistingCustomer(){
		try{
			Integer customerId = 1004;
			List<Integer> serviceIds = new ArrayList<>();
			serviceIds.add(3001);
			serviceIds.add(3003);
			bankServices.addExistingServiceToExistingCustomer(customerId, serviceIds);

			LOGGER.info(environment.getProperty("UserInterface.NEW_CUSTOMER_SERVICE_ALLOCATION_SUCCESS"));
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more detatils!!" );
			LOGGER.info(message);
		}
	}

	public void deallocateServiceForExistingCustomer(){
		try{
			Integer customerId = 1002;
			List<Integer> serviceIds = new ArrayList<>();
			serviceIds.add(3003);
			bankServices.deallocateServiceForExistingCustomer(customerId, serviceIds);

			LOGGER.info(environment.getProperty("UserInterface.CUSTOMER_SERVICE_DEALLOCATION_SUCCESS"));
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more detatils!!" );
			LOGGER.info(message);
		}
	}

	public void deleteCustomer(){
		try{
			Integer customerId=1001;
			bankServices.deleteCustomer(customerId);

			LOGGER.info(environment.getProperty("UserInterface.CUSTOMER_DELETE_SUCCESS"));
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more detatils!!" );
			LOGGER.info(message);
		}
	}

	public void getCustomer(){
		try{
			Integer customerId = 1004;
			CustomerDTO customerDTO=bankServices.getCustomer(customerId);
			LOGGER.info(customerDTO);
			Set<ServicesDTO> serviceList=customerDTO.getBankServices();
			if (serviceList == null){
				LOGGER.info(environment.getProperty("UserInterface.SERVICE_UNAVAILABLE"));
			}
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more detatils!!" );
			LOGGER.info(message);
		}
	}



}
