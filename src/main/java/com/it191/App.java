package com.it191;

import javax.swing.SwingUtilities;

import com.it191.view.MainFrame;

import javafx.embed.swing.JFXPanel;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // By Creating an instance of JFXPanel
                // We are initializing JavaFX
                // This is required for MediaPlayer usage
                new JFXPanel();

                // Generate New JFrame
                new MainFrame();
            }
        });
    }
}
