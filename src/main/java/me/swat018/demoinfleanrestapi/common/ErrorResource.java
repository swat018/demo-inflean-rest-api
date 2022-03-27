package me.swat018.demoinfleanrestapi.common;

import me.swat018.demoinfleanrestapi.index.IndexController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class ErrorResource extends EntityModel<Errors> {
    public ErrorResource(Errors content, Link... links) {
        super(content, Arrays.asList(links));
        add(linkTo(IndexController.class).withRel("index"));
    }

}