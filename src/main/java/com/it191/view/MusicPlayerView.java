package com.it191.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.it191.controller.MusicController;

public class MusicPlayerView extends JPanel {

    MusicController musicController = new MusicController();

    public MusicPlayerView() {
        JButton playBtn = new JButton("Play");
        JButton pauseBtn = new JButton("Pause");
        JButton stopBtn = new JButton("Stop");
        JSlider seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);

        this.add(playBtn);
        this.add(pauseBtn);
        this.add(stopBtn);
        this.add(seekSlider);

        playBtn.addActionListener((evt) -> {
            musicController.playMusic();
        });

        pauseBtn.addActionListener((evt) -> {
            musicController.pauseMusic();
        });

        stopBtn.addActionListener((evt) -> {
            musicController.stopMusic();
        });
    }
}
