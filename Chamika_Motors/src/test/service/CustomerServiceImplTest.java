package test.service;

import dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.CustomerRepository;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CustomerServiceImpl
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testRegisterCustomer_Success() throws Exception {
        // Arrange
        String mobile = "0771234567";
        String name = "John Doe";
        when(customerRepository.findByMobile(mobile)).thenReturn(Optional.empty());

        // Act
        customerService.registerCustomer(mobile, name);

        // Assert
        verify(customerRepository).findByMobile(mobile);
        verify(customerRepository).createCustomer(mobile, name);
    }

    @Test
    void testRegisterCustomer_AlreadyExists() throws Exception {
        // Arrange
        String mobile = "0771234567";
        String name = "John Doe";
        CustomerDto existingCustomer = new CustomerDto(mobile, "Existing", 100.0);
        when(customerRepository.findByMobile(mobile)).thenReturn(Optional.of(existingCustomer));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            customerService.registerCustomer(mobile, name);
        });

        assertTrue(exception.getMessage().contains("already exists"));
        verify(customerRepository).findByMobile(mobile);
        verify(customerRepository, never()).createCustomer(anyString(), anyString());
    }

    @Test
    void testUpdateCustomer_Success() throws Exception {
        // Arrange
        String mobile = "0771234567";
        String name = "Jane Doe";

        // Act
        customerService.updateCustomer(mobile, name);

        // Assert
        verify(customerRepository).updateCustomer(mobile, name);
    }

    @Test
    void testSearchCustomers_Success() throws Exception {
        // Arrange
        String namePattern = "John";
        CustomerDto customer1 = new CustomerDto("0771234567", "John Doe", 100.0);
        CustomerDto customer2 = new CustomerDto("0779876543", "Johnny Cash", 200.0);
        List<CustomerDto> expectedCustomers = Arrays.asList(customer1, customer2);
        
        when(customerRepository.searchByName(namePattern)).thenReturn(expectedCustomers);

        // Act
        List<CustomerDto> result = customerService.searchCustomers(namePattern);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Johnny Cash", result.get(1).getName());
        verify(customerRepository).searchByName(namePattern);
    }

    @Test
    void testGetCustomer_Success() throws Exception {
        // Arrange
        String mobile = "0771234567";
        CustomerDto expectedCustomer = new CustomerDto(mobile, "John Doe", 150.0);
        when(customerRepository.findByMobile(mobile)).thenReturn(Optional.of(expectedCustomer));

        // Act
        CustomerDto result = customerService.getCustomer(mobile);

        // Assert
        assertNotNull(result);
        assertEquals(mobile, result.getMobile());
        assertEquals("John Doe", result.getName());
        assertEquals(150.0, result.getPoints());
        verify(customerRepository).findByMobile(mobile);
    }

    @Test
    void testGetCustomer_NotFound() throws Exception {
        // Arrange
        String mobile = "0771234567";
        when(customerRepository.findByMobile(mobile)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            customerService.getCustomer(mobile);
        });

        assertTrue(exception.getMessage().contains("not found"));
        verify(customerRepository).findByMobile(mobile);
    }

    @Test
    void testSearchCustomers_EmptyResult() throws Exception {
        // Arrange
        String namePattern = "NonExistent";
        when(customerRepository.searchByName(namePattern)).thenReturn(Arrays.asList());

        // Act
        List<CustomerDto> result = customerService.searchCustomers(namePattern);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(customerRepository).searchByName(namePattern);
    }
}
