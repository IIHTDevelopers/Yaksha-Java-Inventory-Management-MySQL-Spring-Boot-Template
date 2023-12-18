package com.inventorymanagement.boundary;

import static com.inventorymanagement.utils.TestUtils.boundaryTestFile;
import static com.inventorymanagement.utils.TestUtils.currentTest;
import static com.inventorymanagement.utils.TestUtils.testReport;
import static com.inventorymanagement.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.inventorymanagement.dto.SellDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class SellBoundaryTest {

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
	public void testProductIdNotNull() throws IOException {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setProductId(null);
		Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testQuantityNotNull() throws IOException {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setQuantity(null);
		Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testQuantityMinOne() throws IOException {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setQuantity(0); // Less than 1
		Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testQuantityMax999() throws IOException {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setQuantity(1000); // More than 999
		Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testOrderDateNotNull() throws IOException {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setOrderDate(null);
		Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (IOException e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testOrderDateInvalidFormat() throws IOException {
		try {
			SellDTO sellDTO = new SellDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date futureDate = sdf.parse("2030-01-15");
			sellDTO.setOrderDate(futureDate);
			Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testOrderDateInFuture() throws ParseException, IOException {
		SellDTO sellDTO = new SellDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date futureDate = sdf.parse("2030-01-01");
			sellDTO.setOrderDate(futureDate);
			Set<ConstraintViolation<SellDTO>> violations = validator.validate(sellDTO);
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception ex) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

}
