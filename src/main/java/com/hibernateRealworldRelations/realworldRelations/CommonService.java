package com.hibernateRealworldRelations.realworldRelations;

import com.hibernateRealworldRelations.realworldRelations.entity.*;
import com.hibernateRealworldRelations.realworldRelations.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    public void addUser(String username) {
        User user = User.builder().username(username).build();
        User addedUser = userRepository.save(user);
        System.out.println("user with username: " + username + " added to repository");
        System.out.println(addedUser.toString());
    }

    @Transactional
    public void followUserById(long userIdFrom, long userIdTo) throws InterruptedException {
        User userFrom = userRepository.findById(userIdFrom);
        System.out.println(userFrom);
        User userTo = userRepository.findById(userIdTo);
        Follower follower = new Follower(userFrom,userTo);
        follower = followerRepository.save(follower);
        Set<Follower> following = userFrom.getFollowing();
        following.add(follower);
        Set<Follower> followers = userTo.getFollowers();
        followers.add(follower);

        userRepository.save(userFrom);
        userRepository.save(userTo);
    }


    @Transactional
    public void addArticleByUserId(long id) {
        User author = userRepository.findById(id);
        Article article = Article.builder().author(author).build();
        article = articleRepository.save(article);
        List<Article> articles = author.getArticles();
        articles.add(article);
        author.setArticles(articles);

        userRepository.save(author);
        System.out.println("article with id: " + article.getId() + " added to repository");
        System.out.println(article);
    }

    @Transactional
    public void printUserById(long userId) {
        User author = userRepository.findById(userId);
        System.out.println(author);
    }

    @Transactional
    public void addCommentToArticle(long articleId, long userId) {
        Article article = articleRepository.findById(articleId);
        User author = userRepository.findById(userId);
        Comment comment = Comment.builder().article(article).author(author).build();
        comment = commentRepository.save(comment);
        List<Comment> comments = article.getComments();
        List<Comment> userComments = author.getComments();
        comments.add(comment);
        userComments.add(comment);
        article.setComments(comments);
        author.setComments(userComments);

        articleRepository.save(article);
        userRepository.save(author);
        System.out.println("comment with article_id: " + article.getId() + " added to repository");
        System.out.println(comment);
    }

    @Transactional
    public void printArticleById(long articleId) {
        Article article = articleRepository.findById(articleId);
        System.out.println(article);
    }

    @Transactional
    public void makeArticleFavorite(long articleId, long userId) {
        Article article = articleRepository.findById(articleId);
        User user = userRepository.findById(userId);
        Set<Article> favoritesArticles = user.getFavoriteArticles();
        Set<User> followingUsers = article.getFollowingUsers();
        favoritesArticles.add(article);
        followingUsers.add(user);
        article.setFollowingUsers(followingUsers);
        user.setFavoriteArticles(favoritesArticles);

        articleRepository.save(article);
        userRepository.save(user);
    }

    @Transactional
    public void addTagToArticle(long articleId, String tagName) {
        Article article = articleRepository.findById(articleId);
        Tag tag = Tag.builder().article(article).name(tagName).build();
        tag = tagRepository.save(tag);
        Set<Tag> tags = article.getTagList();
        tags.add(tag);
        article.setTagList(tags);

        articleRepository.save(article);
    }

    public void addTestData() {
        addUser("john"); // user_id = 1
        addUser("mike"); // user_id = 2
        addUser("bob"); // user_id = 3
        addUser("rob"); // user_id = 4
        addUser("albrecht"); // user_id = 5
        addUser("szczepan"); // user_id = 6
        addUser("steven"); // user_id = 7
        addUser("james"); // user_id = 8
        addUser("peter"); // user_id = 9
        addUser("jason"); // user_id = 10
        addArticleByUserId(1); // article_id = 1
        addArticleByUserId(2); // article_id = 2
        addArticleByUserId(3); // article_id = 3
        addArticleByUserId(4); // article_id = 4
        addArticleByUserId(5); // article_id = 5
        addArticleByUserId(6); // article_id = 6
        addArticleByUserId(7); // article_id = 7
        addArticleByUserId(8); // article_id = 8
        addArticleByUserId(9); // article_id = 9
        addArticleByUserId(10); // article_id = 10
    }
}