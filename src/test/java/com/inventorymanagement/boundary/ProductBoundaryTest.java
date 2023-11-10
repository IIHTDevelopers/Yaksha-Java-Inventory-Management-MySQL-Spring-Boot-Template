package com.inventorymanagement.boundary;

import static com.inventorymanagement.utils.TestUtils.boundaryTestFile;
import static com.inventorymanagement.utils.TestUtils.currentTest;
import static com.inventorymanagement.utils.TestUtils.testReport;
import static com.inventorymanagement.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.inventorymanagement.dto.ProductDTO;

public class ProductBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotNull() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(null);
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMinThree() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("ab"); // Less than 3 characters
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMax255() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("a".repeat(256)); // More than 255 characters
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPriceNotNull() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPrice(null);
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPriceMinZero() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPrice(-0.01); // Less than 0.01
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPriceMax9999() throws IOException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setPrice(10000.00); // More than 9999
		Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
