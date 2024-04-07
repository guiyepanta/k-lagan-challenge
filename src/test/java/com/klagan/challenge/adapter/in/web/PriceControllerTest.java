package com.klagan.challenge.adapter.in.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test01() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-14 10:00:00/35455/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test02() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-14 16:00:00/35455/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }

    @Test
    @DisplayName("Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ) ")
    void test03() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-14 21:00:00/35455/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)")
    void test04() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-15 10:00:00/35455/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }

    @Test
    @DisplayName("Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)")
    void test05() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-16 21:00:00/35455/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4));
    }

    @Test
    @DisplayName("Test 06: create price")
    void test06() throws Exception {

	// GIVEN
	String contentBody = "{\r\n" + "    \"brandId\": 2,\r\n" + "    \"startDate\": \"2020-06-15 00:00:00\",\r\n"
		+ "    \"endDate\": \"2020-06-15 11:00:00\",\r\n" + "    \"priceList\": 5,\r\n"
		+ "    \"productId\": 111111,\r\n" + "    \"priority\": 0,\r\n" + "    \"price\": 20000.99,\r\n"
		+ "    \"curr\": \"ARS\"\r\n" + "}";

	mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/price").content(contentBody)
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.curr").value("ARS"));

    }

    @Test
    @DisplayName("Test 03: update price")
    void test07() throws Exception {

	// GIVEN
	String contentBody = "{\r\n" + "    \"brandId\": 2,\r\n" + "    \"startDate\": \"2020-06-15 00:00:00\",\r\n"
		+ "    \"endDate\": \"2020-06-15 11:00:00\",\r\n" + "    \"priceList\": 5,\r\n"
		+ "    \"productId\": 111111,\r\n" + "    \"priority\": 0,\r\n" + "    \"price\": 10000.99,\r\n"
		+ "    \"curr\": \"ARS\"\r\n" + "}";

	mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/price/6").content(contentBody)
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.curr").value("ARS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(Float.valueOf("10000.99")));

    }

    @Test
    @DisplayName("Test 04: Get price NOT_FOUND")
    void test08() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/price/2020-06-14 10:00:00/99999/1")
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Test 05: update price BAD_REQUEST: without name")
    void test09() throws Exception {

	// GIVEN
	String contentBodyWithoutName = "{\r\n" + "    \"startDate\": \"2020-06-15 00:00:00\",\r\n"
		+ "    \"endDate\": \"2020-06-15 11:00:00\",\r\n" + "    \"priceList\": 12,\r\n"
		+ "    \"productId\": 35455,\r\n" + "    \"priority\": 9,\r\n" + "    \"price\": 30.5,\r\n"
		+ "    \"curr\": \"USD\"\r\n" + "}";

	mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/price/6").content(contentBodyWithoutName)
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
