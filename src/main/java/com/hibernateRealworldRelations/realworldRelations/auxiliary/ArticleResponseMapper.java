package com.hibernateRealworldRelations.realworldRelations.auxiliary;

import com.hibernateRealworldRelations.realworldRelations.dto.responses.ArticleResponse;
import com.hibernateRealworldRelations.realworldRelations.dto.Author;
import com.hibernateRealworldRelations.realworldRelations.entity.Article;
import com.hibernateRealworldRelations.realworldRelations.entity.Tag;

import java.util.function.Function;

public class ArticleResponseMapper implements Function<Article, ArticleResponse> {
    @Override
    public ArticleResponse apply(Article article) {
        return ArticleResponse.builder()
                .slug(article.getSlug())
                .title(article.getTitle())
                .description(article.getDescription())
                .body(article.getBody())
                .tagList(article.getTagList().stream().map(Tag::getName).toList())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .favorited(article.isFavorited())
                .favoritesCount(article.getFollowingUsers().size()) // followingUsers Hashset.size()
                .author(new Author(
                        article.getAuthor().getUsernameDB(),
                        article.getAuthor().getBio(),
                        article.getAuthor().getImage(),
                        article.isFollowing()
                ))
                .build();
    }
}