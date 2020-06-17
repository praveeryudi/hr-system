package com.example.demo;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.service.FlatMaintenanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class FlatMaintenanceServiceTest {

	private static final Logger log = LoggerFactory.getLogger(FlatMaintenanceServiceTest.class);

	@MockBean
	private FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;
	@MockBean
	private MaintenanceTxnDAO maintenanceTxnDAO;
	@Autowired
	private FlatMaintenanceService flatMaintenanceService;

	@Test
	public void testFlatLookup() throws Exception {

		log.info("unit testing service getAllFlatData()...");
		when(flatMaintenanceLookUpDAO.findAll()).thenReturn(getAllFlatDataMockReturn());
		assertEquals(2, flatMaintenanceService.getAllFlatData().size());
		/*mockMvc.perform(get("/maintenance/lookup"))
				.andDo(print())
				.andExpect(status().isOk());*/
	}

	@Test
	public void testGetAllTransactions() {
		log.info("unit testing service getAllTransactions()...");
		when(maintenanceTxnDAO.findAll(Sort.by(Sort.Direction.ASC, "flatNumber"))).thenReturn(getAllTransactionsMockReturn());
		assertEquals(3, flatMaintenanceService.getAllTransactions().size());
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