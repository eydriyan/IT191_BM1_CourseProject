package com.it191.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LyricsPanel extends JPanel {
    
    private JTextPane lyricsPane;

    public LyricsPanel(){
        JScrollPane scrollPane = new JScrollPane();

        lyricsPane = new JTextPane();
        lyricsPane.setEditable(false);
        lyricsPane.setBackground(new Color(51, 51, 51));
        lyricsPane.setFont(new Font("STXihei", Font.PLAIN, 20));
        lyricsPane.setForeground(new Color(255, 255, 255));

        // Create a StyledDocument and set it as the StyledDocument for the JTextPane
        StyledDocument doc = lyricsPane.getStyledDocument();

        // Set the paragraph alignment to center
        SimpleAttributeSet centerAlignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlignment, StyleConstants.ALIGN_CENTER);

        // Set the center alignment style for the document
        doc.setParagraphAttributes(0, doc.getLength(), centerAlignment, false);

        scrollPane.setViewportView(lyricsPane);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setLyrics(String lyricsString) {            
        this.lyricsPane.setText(lyricsString);
    }
}
