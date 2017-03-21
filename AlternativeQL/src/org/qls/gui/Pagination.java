package org.qls.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Pagination {
    private int currentPage;
    private Button previousButton = new Button();
    private Button nextButton = new Button();

    public Pagination(QLSGUIFormBuilder qlsGUIFormBuilder, int maxPage) {
        nextButton.setText("Next page");
        nextButton.setOnAction(event -> {
            if(currentPage < (maxPage-1)) {
                nextPage();
                qlsGUIFormBuilder.constructFormPage(currentPage);
            }
        });

        previousButton.setText("Previous page");
        previousButton.setOnAction(event -> {
            if(!(currentPage <= 0)) {
                previousPage();
                qlsGUIFormBuilder.constructFormPage(currentPage);
            }
        });
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
