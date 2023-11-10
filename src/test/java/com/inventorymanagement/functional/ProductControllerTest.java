package com.inventorymanagement.functional;

import static com.inventorymanagement.utils.MasterData.getProductDTO;
import static com.inventorymanagement.utils.MasterData.getProductDTOList;
import static com.inventorymanagement.utils.TestUtils.businessTestFile;
import static com.inventorymanagement.utils.TestUtils.currentTest;
import static com.inventorymanagement.utils.TestUtils.testReport;
import static com.inventorymanagement.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

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

import com.inventorymanagement.controller.ProductController;
import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.service.ProductService;
import com.inventorymanagement.utils.MasterData;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		List<ProductDTO> productDTOS = getProductDTOList();

		when(this.productService.getAllProducts()).thenReturn(productDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDTOS)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetProductById() throws Exception {
		ProductDTO productDTO = getProductDTO();
		when(this.productService.getProductById(productDTO.getId())).thenReturn(productDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/" + productDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateProduct() throws Exception {
		ProductDTO productDTO = getProductDTO();

		when(this.productService.createProduct(any())).thenReturn(productDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products")
				.content(MasterData.asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateProduct() throws Exception {
		ProductDTO productDTO = getProductDTO();

		when(this.productService.updateProduct(eq(productDTO.getId()), any())).thenReturn(productDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/products/" + productDTO.getId())
				.content(MasterData.asJsonString(productDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(productDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteProduct() throws Exception {
		ProductDTO productDTO = getProductDTO();
		when(this.productService.deleteProduct(productDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/products/" + productDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}
}
