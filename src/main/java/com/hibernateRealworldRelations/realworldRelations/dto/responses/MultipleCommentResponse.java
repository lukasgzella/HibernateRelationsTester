package com.hibernateRealworldRelations.realworldRelations.dto.responses;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MultipleCommentResponse {

    private List<CommentResponse> comments;

    @Override
    public String toString() {
        return "MultipleCommentResponse{" +
                "comments=" + comments.toString() +
                '}';
    }
}