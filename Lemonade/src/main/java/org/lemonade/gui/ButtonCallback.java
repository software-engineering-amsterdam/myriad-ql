package org.lemonade.gui;

import java.io.File;
import java.io.IOException;

public interface ButtonCallback {

    boolean goToQuestionnaire(File file);

    void submitForm(File file);

}
