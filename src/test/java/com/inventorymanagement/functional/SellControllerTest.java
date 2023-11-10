package com.inventorymanagement.functional;

import static com.inventorymanagement.utils.MasterData.getSellDTO;
import static com.inventorymanagement.utils.MasterData.getSellDTOList;
import static com.inventorymanagement.utils.TestUtils.businessTestFile;
import static com.inventorymanagement.utils.TestUtils.currentTest;
import static com.inventorymanagement.utils.TestUtils.testReport;
import static com.inventorymanagement.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.inventorymanagement.controller.SellController;
import com.inventorymanagement.dto.SellDTO;
import com.inventorymanagement.service.SellService;
import com.inventorymanagement.utils.MasterData;

@WebMvcTest(SellController.class)
@AutoConfigureMockMvc
public class SellControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SellService sellService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllSells() throws Exception {
		List<SellDTO> sellDTOS = getSellDTOList();

		when(this.sellService.getAllSells()).thenReturn(sellDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sells").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sellDTOS)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetSellById() throws Exception {
		SellDTO sellDTO = getSellDTO();
		when(this.sellService.getSellById(sellDTO.getId())).thenReturn(sellDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sells/" + sellDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sellDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateSell() throws Exception {
		SellDTO sellDTO = getSellDTO();

		when(this.sellService.createSell(any())).thenReturn(sellDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/sells")
				.content(MasterData.asJsonString(sellDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sellDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetProductsSoldLastMonth() throws Exception {
		Map<String, Integer> productSalesMap = MasterData.getProductSalesMap();
		when(this.sellService.getProductsSoldInLastMonth()).thenReturn(productSalesMap);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sells/products-sold-last-month")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productSalesMap))
						? "true"
						: "false"),
				businessTestFile);
	}
}
