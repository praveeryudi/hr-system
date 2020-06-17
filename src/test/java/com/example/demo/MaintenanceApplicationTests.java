package com.example.demo;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.entity.MaintenanceTxn;
import com.example.demo.request.TxnRequest;
import com.example.demo.service.FlatMaintenanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@RunWith(PowerMockRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
//@PrepareForTest({MaintenanceUtil.class})
public class MaintenanceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(MaintenanceApplicationTests.class);

	@Autowired
	private FlatMaintenanceService flatMaintenanceService;
	@MockBean
	private FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;
	@MockBean
	private MaintenanceTxnDAO maintenanceTxnDAO;
	@Mock
	private TxnRequest txnRequest;
	@Mock
	private MaintenanceTxn maintenanceTxn;

	@Test
	public void testFlatLookup() throws Exception {

		log.info("unit testing controller getAllFlatLookupData()...");
		when(flatMaintenanceLookUpDAO.findAll()).thenReturn(getAllFlatDataMockReturn());
		assertEquals(2, flatMaintenanceService.getAllFlatData().size());
		/*mockMvc.perform(get("/maintenance/lookup"))
				.andDo(print())
				.andExpect(status().isOk());*/
	}

	/*@Test
	public void testAddMaintenance() {
		log.info("unit testing controller addTransaction()...");
		txnRequest.setTxnDate(new Date());
		txnRequest.setSelectedMonth("JUN");
		txnRequest.setSelectedYear("2020");
		txnRequest.setExpectedMaintenance(430.0);
		txnRequest.setFlatNumber("1003");
		txnRequest.setActualPayment(430.0);
		txnRequest.setPaymentMode("ONLINE");

		PowerMockito.mockStatic(MaintenanceUtil.class);
		when(MaintenanceUtil.getPreviousMonthYear("JUN", "2020")).thenReturn(new String[]{"MAY", "2020"});
		when(maintenanceTxnDAO.getTxn("MAY", "2020", "1003")).thenReturn(null);
		when(maintenanceTxnDAO.saveAndFlush(maintenanceTxn)).thenReturn(maintenanceTxn);
	}*/

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
}