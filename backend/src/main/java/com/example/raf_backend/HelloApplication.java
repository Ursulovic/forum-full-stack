package com.example.raf_backend;

import com.example.raf_backend.repositories.article.ArticleRepository;
import com.example.raf_backend.repositories.article.MySqlArticleRepository;
import com.example.raf_backend.repositories.category.CategoryRepository;
import com.example.raf_backend.repositories.category.MySqlCategoryRepository;
import com.example.raf_backend.repositories.comment.CommentRepository;
import com.example.raf_backend.repositories.comment.MySqlCommentRepository;
import com.example.raf_backend.repositories.tag.MySqlTagRepository;
import com.example.raf_backend.repositories.tag.TagRepository;
import com.example.raf_backend.repositories.user.MySqlUserRepositry;
import com.example.raf_backend.repositories.user.UserRepository;
import com.example.raf_backend.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlUserRepositry.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);



                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(ArticleService.class);


            }
        };

        register(binder);

        packages("com.example.raf_backend");

    }

}