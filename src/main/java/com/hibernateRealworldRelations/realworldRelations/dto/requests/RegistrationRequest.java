package com.hibernateRealworldRelations.realworldRelations.dto.requests;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "user")
public class RegistrationRequest {

    private String username;
    private String email;
    private String password;

}