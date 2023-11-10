package com.inventorymanagement.exception;

import static com.inventorymanagement.utils.MasterData.getSellDTO;
import static com.inventorymanagement.utils.TestUtils.currentTest;
import static com.inventorymanagement.utils.TestUtils.exceptionTestFile;
import static com.inventorymanagement.utils.TestUtils.testReport;
import static com.inventorymanagement.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
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
public class SellExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SellService sellService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateSellInvalidDataException() throws Exception {
		SellDTO sellDTO = getSellDTO();
		sellDTO.setProductId(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/sells")
				.content(MasterData.asJsonString(sellDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetSellByIdResourceNotFoundException() throws Exception {
		SellDTO sellDTO = getSellDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Sell not found");

		when(this.sellService.getSellById(sellDTO.getId())).thenThrow(new NotFoundException("Sell not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/sells/" + sellDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
