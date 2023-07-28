package com.it191.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Music Player");

        add(new MusicPlayerView(), BorderLayout.CENTER);
        setVisible(true);
        setMinimumSize(new Dimension(1400, 950));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
