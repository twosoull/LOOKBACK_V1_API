package com.lookback.presentation.users.dto;

import com.lookback.domain.user.entity.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FindTrainingUsersResponse {

    List<UsersDto> result = new ArrayList<>();

    public static FindTrainingUsersResponse getFindTrainingUsersResponse(List<Users> usersList) {
        FindTrainingUsersResponse findTrainingUsersResponse = new FindTrainingUsersResponse();
        findTrainingUsersResponse
                .setResult(usersList.stream()
                            .map(users -> UsersDto.fromEntity(users))
                            .collect(Collectors.toList()));
        return findTrainingUsersResponse;
    }
}
