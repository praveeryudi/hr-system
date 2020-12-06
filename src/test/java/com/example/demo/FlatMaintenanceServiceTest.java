package com.example.demo;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.service.FlatMaintenanceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = FlatMaintenanceService.class)
@ActiveProfiles(profiles = "dev")
@Slf4j
public class FlatMaintenanceServiceTest {

	@MockBean
	private FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;
	@MockBean
	private MaintenanceTxnDAO maintenanceTxnDAO;
	@Autowired
	private FlatMaintenanceService flatMaintenanceService;
	@MockBean
	private FlatMaintenanceLookUp flatMaintenanceLookUp;

	@Test
	public void testFlatLookup() {

		log.info("unit testing service getAllFlatData()...");
		when(flatMaintenanceLookUpDAO.findAll()).thenReturn(getAllFlatDataMockReturn());
		assertEquals(2, flatMaintenanceService.getAllFlatData().size());
	}

	@Test
	public void testGetAllTransactions() {
		log.info("unit testing service getAllTransactions()...");
		when(maintenanceTxnDAO.findAll(Sort.by(Sort.Direction.ASC, "flatNumber"))).thenReturn(getAllTransactionsMockReturn());
		assertEquals(3, flatMaintenanceService.getAllTransactions().size());
	}

	@Test
	public void testGetIndividualFlatData() {
		log.info("unit testing service method getIndividualFlatData()...");
		flatMaintenanceLookUp.setFlatNumber("1002");
		flatMaintenanceLookUp.setExpectedMaintenance(430.0);
		flatMaintenanceLookUp.setOwnerName("TestXXX");
		when(flatMaintenanceLookUpDAO.findById("1002")).thenReturn(java.util.Optional.ofNullable(flatMaintenanceLookUp));
		Assert.assertNotNull(flatMaintenanceLookUp);
	}

	@Test
	public void testDeleteTransactions() {
		log.info("unit testing service method deleteTransactions()...");
		List<Long> txnIds = Stream.of(10L, 12L, 20L).collect(Collectors.toList());
		doNothing().when(maintenanceTxnDAO).deleteById(Mockito.anyLong());
		flatMaintenanceService.deleteTransactions(txnIds);
		verify(maintenanceTxnDAO, times(txnIds.size())).deleteById(Mockito.anyLong());
	}

	private List<FlatMaintenanceLookUp> getAllFlatDataMockReturn() {

		List<FlatMaintenanceLookUp> list = new ArrayList<>();
		FlatMaintenanceLookUp flat1 = new FlatMaintenanceLookUp();
		flat1.setFlatNumber("1001");
		flat1.setOwnerName("test001");
		flat1.setExpectedMaintenance(10.0);

		FlatMaintenanceLookUp flat2 = new FlatMaintenanceLookUp();
		flat2.setFlatNumber("1002");
		flat2.setOwnerName("test002");
		flat2.setExpectedMaintenance(20.0);

		list.add(flat1);
		list.add(flat2);
		return list;
	}

	private List<MaintenanceTxn> getAllTransactionsMockReturn() {
		List<MaintenanceTxn> list = new ArrayList<>();
		MaintenanceTxn txn1 = new MaintenanceTxn();
		txn1.setTxnId(1L);
		txn1.setFlatNumber("1001");
		txn1.setTxnDate(new Date());
		txn1.setMonth("JUN");
		txn1.setYear("2020");
		txn1.setBalance(0.0);
		txn1.setActualPayment(100.0);
		txn1.setPaymentMode("CASH");

		MaintenanceTxn txn2 = new MaintenanceTxn();
		txn1.setTxnId(2L);
		txn1.setFlatNumber("1002");
		txn1.setTxnDate(new Date());
		txn1.setMonth("JUN");
		txn1.setYear("2020");
		txn1.setBalance(0.0);
		txn1.setActualPayment(100.0);
		txn1.setPaymentMode("ONLINE");

		MaintenanceTxn txn3 = new MaintenanceTxn();
		txn1.setTxnId(3L);
		txn1.setFlatNumber("1003");
		txn1.setTxnDate(new Date());
		txn1.setMonth("JUN");
		txn1.setYear("2020");
		txn1.setBalance(0.0);
		txn1.setActualPayment(100.0);
		txn1.setPaymentMode("CASH");

		list.add(txn1);
		list.add(txn2);
		list.add(txn3);

		return list;
	}
}