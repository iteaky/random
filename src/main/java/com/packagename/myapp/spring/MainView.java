package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@UIScope
@Route(value = "")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Object details = authentication.getDetails();

        Paragraph greeting = new Paragraph("");

        Button button = new Button("START", event -> {
            try {
                greeting.setText(bean.getMessage(details));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button.setHeight("2000");
        button.setWidth("2000");
        add(greeting, button);
        setPadding(true);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        button.setMaxHeight("2000px");

    }
}
