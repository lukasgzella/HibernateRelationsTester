package com.hibernateRealworldRelations.realworldRelations.dto;

import lombok.AllArgsConstructor;

import java.util.List;

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