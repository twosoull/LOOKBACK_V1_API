package com.lookback.presentation.users.controller;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.service.TrainingService;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.FindTrainingUsersResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RETRIEVE_ERROR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingService trainingService;

    @Test
    public void trainerMember_ShouldReturnOK_WhenValidFindTrainingUsersRequest() throws Exception {
        // Given
        FindTrainingUsersResponse response = new FindTrainingUsersResponse();

        // When
        given(trainingService.findAllTrainingUsers(any(FindTrainingUsersRequest.class))).willReturn(response);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/trainer/member")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void trainerMember_ShouldReturnInternalServerError_WhenTrainingServiceThrowsRestApiException() throws Exception {
        // Given
        given(trainingService.findAllTrainingUsers(any(FindTrainingUsersRequest.class))).willThrow(new RestApiException(RETRIEVE_ERROR));

        // When & Then
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/trainer/member")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

}