package com.it191.view;

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

    MusicController musicController = new MusicController();

    JButton playBtn;
    JButton pauseBtn;
    JButton stopBtn;
    JSlider seekSlider;

    public MusicPlayerView() {
        playBtn = new JButton("Play");
        pauseBtn = new JButton("Pause");
        stopBtn = new JButton("Stop");
        seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);

        this.add(playBtn);
        this.add(pauseBtn);
        this.add(stopBtn);
        this.add(seekSlider);

        seekSlider.setMaximum(musicController.getDuration());

        javax.swing.Timer seekSliderTimerUpdate = new javax.swing.Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double percent = musicController.getCurrentPositionPercent();
                seekSlider.setValue((int)(percent * seekSlider.getMaximum()));
            }
            
        });

        playBtn.addActionListener((evt) -> {
            musicController.playMusic();
            seekSliderTimerUpdate.restart();
        });

        pauseBtn.addActionListener((evt) -> {
            musicController.pauseMusic();
            seekSliderTimerUpdate.stop();
        });

        stopBtn.addActionListener((evt) -> {
            musicController.stopMusic();
            seekSliderTimerUpdate.stop();
            seekSlider.setValue(0);
        });

        seekSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                musicController.pauseMusic();
                seekSliderTimerUpdate.stop();
                super.mousePressed(e);
            }
        });

        seekSlider.addChangeListener(new ChangeListener() {
            private javax.swing.Timer timer;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (seekSlider.getValueIsAdjusting() ) {
                } else {
                    if (timer != null && timer.isRunning()) {
                        timer.restart();
                    } else {
                        timer = new javax.swing.Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!seekSlider.getValueIsAdjusting()) {
                                    double percent = (double)seekSlider.getValue() / (double)seekSlider.getMaximum();
                                    musicController.seekPlay(percent);
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

        
    }
}
