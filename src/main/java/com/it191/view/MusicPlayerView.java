package com.it191.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.it191.controller.MusicController;

public class MusicPlayerView extends JPanel {

    MusicController musicController;

    JButton playBtn;
    JButton pauseBtn;
    JButton stopBtn;
    JSlider seekSlider;
    JSlider volumeSlider;

    public MusicPlayerView() {
        musicController = new MusicController();

        playBtn = new JButton("Play");
        pauseBtn = new JButton("Pause");
        stopBtn = new JButton("Stop");
        seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);
        volumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, 90);
        volumeSlider.setPreferredSize(new Dimension(20, 120));

        this.add(playBtn);
        this.add(pauseBtn);
        this.add(stopBtn);
        this.add(seekSlider);
        this.add(volumeSlider);

        javax.swing.Timer seekSliderTimerUpdate = new javax.swing.Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double percent = musicController.getMusicPlayer().getCurrentPositionPercent();
                seekSlider.setValue((int) (percent * seekSlider.getMaximum()));
            }

        });

        playBtn.addActionListener((evt) -> {
            musicController.getMusicPlayer().Play();
            seekSliderTimerUpdate.restart();
        });

        pauseBtn.addActionListener((evt) -> {
            musicController.getMusicPlayer().Pause();
            seekSliderTimerUpdate.stop();
        });

        stopBtn.addActionListener((evt) -> {
            musicController.getMusicPlayer().Stop();
            seekSliderTimerUpdate.stop();
            seekSlider.setValue(0);
        });

        seekSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                musicController.getMusicPlayer().Pause();
                seekSliderTimerUpdate.stop();
                super.mousePressed(e);
            }
        });

        seekSlider.addChangeListener(new ChangeListener() {
            private javax.swing.Timer timer;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (seekSlider.getValueIsAdjusting()) {
                } else {
                    if (timer != null && timer.isRunning()) {
                        timer.restart();
                    } else {
                        timer = new javax.swing.Timer(200, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!seekSlider.getValueIsAdjusting()) {
                                    double percent = (double) seekSlider.getValue() / (double) seekSlider.getMaximum();
                                    musicController.getMusicPlayer().seekPlay(percent);
                                    timer.stop();
                                }
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = (float) volumeSlider.getValue() / (float) volumeSlider.getMaximum();
                musicController.getMusicPlayer().setVolume(volume);
            }
        });
    }
}
