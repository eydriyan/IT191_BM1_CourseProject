package com.it191.view;

import javax.swing.JScrollPane;

public class LyricsPanel extends JScrollPane {
    
    private javax.swing.JTextArea jTextArea1;

    public LyricsPanel(){
        jTextArea1 = new javax.swing.JTextArea();

        jTextArea1.setBackground(new java.awt.Color(51, 51, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("STXihei", 1, 20)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        this.setViewportView(jTextArea1);

    }
}
