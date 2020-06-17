package com.example.demo;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.service.FlatMaintenanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@DataJpaTest
public class MaintenanceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(MaintenanceApplicationTests.class);

	@Autowired
	private FlatMaintenanceService flatMaintenanceService;
	@MockBean
	private FlatMaintenanceLookUpDAO flatMaintenanceLookUpDAO;

	@Test
	public void testFlatLookup() throws Exception {

		log.info("unit testing controller getAllFlatLookupData()...");
		when(flatMaintenanceLookUpDAO.findAll()).thenReturn(getAllFlatDataMockReturn());
		assertEquals(2, flatMaintenanceService.getAllFlatData().size());
		/*mockMvc.perform(get("/maintenance/lookup"))
				.andDo(print())
				.andExpect(status().isOk());*/
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
}