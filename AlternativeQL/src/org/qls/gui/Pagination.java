package org.qls.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Pagination {
    private int currentPage;
    private int maxPage;
    private Button previousButton = new Button();
    private Button nextButton = new Button();

    public Pagination(QLSGUIFormBuilder qlsGUIFormBuilder, int maxPage) {
        this.maxPage = maxPage;

        nextButton.setText("Next page");
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(currentPage >= maxPage)) {
                    nextPage();
                    qlsGUIFormBuilder.constructFormPage(currentPage);
                }
            }
        });

        previousButton.setText("Previous page");
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(currentPage <= 0)) {
                    previousPage();
                    qlsGUIFormBuilder.constructFormPage(currentPage);
                }
            }
        });
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getCurrentPage() {
        return currentPage;
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
