//package com.abdu.productservice;
//
//import com.abdu.productservice.dto.ProductRequestDTO;
//import com.abdu.productservice.repository.ProductRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        String s = objectMapper.writeValueAsString(getProductRequestDTO());
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/api/product/create")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(s)
//                )
//                .andExpect(status().isCreated());
//        Assertions.assertEquals(1, productRepository.findAll().size());
//    }
//
//    private ProductRequestDTO getProductRequestDTO() {
//        return ProductRequestDTO.builder()
//                .name("Test Product")
//                .description("Test Product description")
//                .price(10.0)
//                .build();
//    }
//
//}
