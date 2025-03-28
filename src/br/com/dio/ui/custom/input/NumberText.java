package br.com.dio.ui.custom.input;

import br.com.dio.model.Space;
import br.com.dio.service.EventEnum;
import br.com.dio.service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NumberText extends JTextField implements EventListener {

    private final Space space;

    public NumberText(final Space space){
        this.space = space;
        var dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());
        if (space.isFixed()){
            this.setText(space.getCurrent().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace(e);
            }

            private void changeSpace(DocumentEvent e) {
                if (getText().isEmpty()){
                    space.clearSpace();
                    return;
                }
                space.setCurrent(Integer.parseInt(getText()));
            }
        });

    }

    @Override
    public void update(EventEnum eventType) {
        if (eventType.equals(EventEnum.CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
        }
    }
}
