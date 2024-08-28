//package com;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.controller.ProController;
//
//import com.service.UsersService;
//
//@ExtendWith(SpringExtension.class)
//public class ProController_tests {
//
//	@InjectMocks
//	ProController proController;
//
//	@InjectMocks
//	Utility utility;
//
//	@Mock
//	UsersService empService;
//
//	@Test
//	public void fetchEmpList_test() {
//		// return empService.fetchEmpList();
//
//		// List<Employee> op = empRepo.findAll();
//
//		Mockito.when(empService.fetchEmpList()).thenReturn(utility.empPojoList());
//		assertEquals(utility.empPojoList(), proController.fetchEmpList());
//	}
//
//	@Test
//	public void getEmployeeByIdByPV_test() {
//		// return empService.fetchEmpByIdByPV();
//		Mockito.when(empService.fetchEmpByIdByPV(0)).thenReturn(new EmployeePojo());
//		assertEquals(new EmployeePojo(), proController.getEmployeeByIdByPV(0));
//	}
//
//	public void getEmployeeByIdByRP_test() {
//		// return empService.fetchEmpByIdByRP();
//		Mockito.when(empService.fetchEmpByIdByRP(0)).thenReturn(new EmployeePojo());
//		assertEquals(new EmployeePojo(), proController.getEmployeeByIdByRP(0));
//		
//		
//	}
//
//	public void deleteEmpById_test() {
//		// empService.deleteById();
//
//	}
//
//
//
//}
