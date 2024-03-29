package com.hibernateRealworldRelations.realworldRelations.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MultipleTagResponse {

    private List<String> tags;

    @Override
    public String toString() {
        return "MultipleTagResponse{" +
                "tags=" + tags +
                '}';
    }
}