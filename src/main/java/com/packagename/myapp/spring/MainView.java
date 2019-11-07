package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@UIScope
@Route(value = "")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) throws URISyntaxException, IOException {

        File fnew = new File(this.getClass().getResource("/META-INF/resources/icons/logo_RGB-02.jpg").toURI());
        BufferedImage originalImage = ImageIO.read(fnew);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();


        StreamResource resource = new StreamResource("dummyImageName.jpg", () -> new ByteArrayInputStream(imageBytes));
        Image image = new Image(resource, "dummy image");
        add(image);
        image.setWidth("30%");

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Object details = authentication.getDetails();

        Paragraph greeting = new Paragraph("");
        TextField textField = new TextField();
        AtomicInteger i = new AtomicInteger(1);
        ArrayList<Paragraph> paragraphs = new ArrayList<>();
        ArrayList<String> winners = new ArrayList<>();
        Button button = new Button("START", event -> {
            try {
                if (paragraphs.size() == 3) {
                    paragraphs.forEach(this::remove);
                    paragraphs.clear();
                    winners.clear();
                    i.set(1);
                }
                Pair<String, String> winnerAndMassage = bean.getMessage(details, textField.getValue(), winners);
                Paragraph paragraph = new Paragraph(
                        i.getAndIncrement() + " Место: " + winnerAndMassage.getKey() + " Когда отметила: " + winnerAndMassage.getValue());
                paragraphs.add(paragraph);
                winners.add(winnerAndMassage.getKey());
                Style style = paragraph.getStyle();
                style.set("color", "#cc004a");
                addComponentAtIndex(i.get(), paragraph);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Span title2 = new Span("Вставьте ссылку на пост");

        add(greeting, title2, textField, button);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }

}

