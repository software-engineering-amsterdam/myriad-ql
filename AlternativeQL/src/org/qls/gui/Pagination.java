package org.qls.gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Pagination {
    private int currentPage;
    private Button previousButton;
    private Button nextButton;
    private int maxPage;

    public Pagination(QLSGUIFormBuilder qlsGUIFormBuilder, int maxPage) {
        this.maxPage = maxPage;

        nextButton = createButton("Next page", event -> {
            if(hasReachedLastPage()) {
                nextPage();
                performPageAction(qlsGUIFormBuilder);
            }
        });

        previousButton = createButton("Previous page", event -> {
            if(hasReachedBottomPage()) {
                previousPage();
                performPageAction(qlsGUIFormBuilder);
            }
        });

        checkButtonActivity();
    }

    private void performPageAction(QLSGUIFormBuilder qlsGUIFormBuilder) {
        qlsGUIFormBuilder.constructFormPage(currentPage);
        checkButtonActivity();
    }

    private boolean hasReachedBottomPage() {
        return !(currentPage <= 0);
    }

    private boolean hasReachedLastPage() {
        return currentPage < (maxPage-1);
    }

    private Button createButton(String text, EventHandler event) {
        Button button = new Button(text);
        button.setOnAction(event);
        return button;
    }

    private void checkButtonActivity() {
        nextButton.setDisable(currentPage == (maxPage-1));
        previousButton.setDisable(currentPage == 0);
    }

    public void nextPage() {
        currentPage++;
    }

    public void previousPage() {
        currentPage--;
    }

    public GridPane getButtonsPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(previousButton, 0, 0);
        gridPane.add(nextButton, 1, 0);
        return gridPane;
    }
}
