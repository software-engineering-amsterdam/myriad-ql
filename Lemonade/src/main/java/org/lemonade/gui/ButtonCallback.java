package org.lemonade.gui;

import java.io.File;

public interface ButtonCallback {

    boolean goToQuestionnaire(File file);

    void submitForm(File file);

}
