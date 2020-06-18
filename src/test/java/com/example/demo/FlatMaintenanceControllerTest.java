package com.example.demo;

import com.example.demo.controller.FlatMaintenanceController;
import com.example.demo.entity.FlatMaintenanceLookUp;
import com.example.demo.service.FlatMaintenanceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FlatMaintenanceController.class, FlatMaintenanceService.class})
@ActiveProfiles(profiles = "dev")
public class FlatMaintenanceControllerTest {

    private static final Logger log = LoggerFactory.getLogger(FlatMaintenanceControllerTest.class);

    private MockMvc mockMvc;
    private FlatMaintenanceService flatMaintenanceService;

    @Before
    public void setup() {
        flatMaintenanceService = mock(FlatMaintenanceService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new FlatMaintenanceController(flatMaintenanceService)).build();
    }

    @Test
    public void testGetAllFlatLookupData() throws Exception {
        log.info("unit testing controller method getAllFlatLookupData()...");
        when(flatMaintenanceService.getAllFlatData()).thenReturn(getAllFlatDataMockReturn());
        int listSize = flatMaintenanceService.getAllFlatData().size();
        mockMvc.perform(MockMvcRequestBuilders.get("/maintenance/lookup"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(flatMaintenanceService, atLeastOnce()).getAllFlatData();
        assertEquals(2, listSize);
    }

    @Test
    public void testGetIndividualFlatLookup() throws Exception {
        log.info("unit testing controller method getIndividualFlatLookup()...");
        FlatMaintenanceLookUp flatMaintenanceLookUpMock = new FlatMaintenanceLookUp();
        flatMaintenanceLookUpMock.setFlatNumber("1001");
        flatMaintenanceLookUpMock.setOwnerName("testXXX");
        flatMaintenanceLookUpMock.setExpectedMaintenance(12.0);

        when(flatMaintenanceService.getIndividualFlatData(Mockito.anyString())).thenReturn(flatMaintenanceLookUpMock);
        mockMvc.perform(MockMvcRequestBuilders.get("/maintenance/flat/1001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(flatMaintenanceService, atMost(1)).getIndividualFlatData(Mockito.any());
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
