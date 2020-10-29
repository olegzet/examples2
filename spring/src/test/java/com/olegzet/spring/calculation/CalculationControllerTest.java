package com.olegzet.spring.calculation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculationWithWrongShouldReturnErrorMessage() throws Exception {
        this.mockMvc.perform(
                post("/calculations").content("{\"number\":0}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Min number should be 1"));
    }
}
