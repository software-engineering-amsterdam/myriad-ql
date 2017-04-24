package org.qls.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Pagination {
    private final Button previousButton;
    private final Button nextButton;
    private final int maxPage;

    private int currentPage;

    public Pagination(QLSGUIFormBuilder formBuilder, int maxPage) {
        this.maxPage = maxPage;

        nextButton = createButton("Next page", event -> {
            if (hasReachedLastPage()) {
                next();
                formBuilder.constructFormPage();
                reviseButtonDisability();
            }
        });

        previousButton = createButton("Previous page", event -> {
            if (hasReachedBottomPage()) {
                previous();
                formBuilder.constructFormPage();
                reviseButtonDisability();
            }
        });

        reviseButtonDisability();
    }

    private boolean hasReachedBottomPage() {
        return !(currentPage <= 0);
    }

    private boolean hasReachedLastPage() {
        return currentPage < (maxPage-1);
    }

    private Button createButton(String text, EventHandler<ActionEvent> event) {
        Button button = new Button(text);
        button.setOnAction(event);
        return button;
    }

    private void reviseButtonDisability() {
        nextButton.setDisable(currentPage == (maxPage-1));
        previousButton.setDisable(currentPage == 0);
    }

    private void next() {
        currentPage++;
    }

    private void previous() {
        currentPage--;
    }

    GridPane getButtonsPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(previousButton, 0, 0);
        gridPane.add(nextButton, 1, 0);
        return gridPane;
    }

    int getCurrentPage() {
        return currentPage;
    }
}
