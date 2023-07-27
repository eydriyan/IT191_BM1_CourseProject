package com.it191.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Music Player");
        add(new MusicPlayerView());
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
