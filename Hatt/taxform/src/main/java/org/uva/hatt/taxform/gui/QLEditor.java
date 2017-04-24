package org.uva.hatt.taxform.gui;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

class QLEditor {

    private final VirtualizedScrollPane<CodeArea> scrollPane;
    private final CodeArea codeArea;

    QLEditor() {
        this.codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        String defaultCode = String.join("\n", new String[]{
                "form taxOffice { " +
                        "\n" +
                        "\n" +
                        "}"
        });

        codeArea.replaceText(0, 0, defaultCode);
        codeArea.requestFocus();

        this.scrollPane = new VirtualizedScrollPane<>(codeArea);
    }

    VirtualizedScrollPane<CodeArea> getScrollPane() {
        return scrollPane;
    }

    CodeArea getCodeArea() {
        return codeArea;
    }
}
