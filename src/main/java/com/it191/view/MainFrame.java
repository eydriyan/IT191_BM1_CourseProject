package com.it191.view;

import javax.swing.JFrame;

import javafx.embed.swing.JFXPanel;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Music Player");
        add(new MusicPlayerView());
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
