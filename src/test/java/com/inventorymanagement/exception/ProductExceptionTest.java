package com.inventorymanagement.exception;

import static com.inventorymanagement.utils.MasterData.getProductDTO;
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

import com.inventorymanagement.controller.ProductController;
import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.service.ProductService;
import com.inventorymanagement.utils.MasterData;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateProductInvalidDataException() throws Exception {
		ProductDTO productDTO = getProductDTO();
		productDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products")
				.content(MasterData.asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateProductInvalidDataException() throws Exception {
		ProductDTO productDTO = getProductDTO();
		productDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/products/" + productDTO.getId())
				.content(MasterData.asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetProductByIdResourceNotFoundException() throws Exception {
		ProductDTO productDTO = getProductDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Product not found");

		when(this.productService.getProductById(productDTO.getId()))
				.thenThrow(new NotFoundException("Product not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/" + productDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}