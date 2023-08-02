package com.it191;

import javax.swing.SwingUtilities;

import com.it191.view.MainFrame;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Generate New JFrame
                new MainFrame();
            }
        });
    }
}
