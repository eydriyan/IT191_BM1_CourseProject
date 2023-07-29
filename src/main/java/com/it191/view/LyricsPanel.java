package com.it191.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LyricsPanel extends JScrollPane {
    
    private JTextArea lyricsArea;

    public LyricsPanel(){
        lyricsArea = new javax.swing.JTextArea();

        lyricsArea.setBackground(new Color(51, 51, 51));
        lyricsArea.setColumns(20);
        lyricsArea.setFont(new Font("STXihei", 1, 20)); // NOI18N
        lyricsArea.setForeground(new Color(255, 255, 255));
        lyricsArea.setLineWrap(true);
        lyricsArea.setRows(5);
        lyricsArea.setBorder(null);
        this.setViewportView(lyricsArea);
    }

    public void setLyrics(String lyricsString) {
        this.lyricsArea.setText(lyricsString);
    }
}
