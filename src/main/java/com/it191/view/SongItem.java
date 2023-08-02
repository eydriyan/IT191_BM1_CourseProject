package com.it191.view;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.it191.model.SongModel;
import com.it191.view.listeners.ISongRequestListener;
import com.it191.view.listeners.ISongUpdateListener;
import com.it191.view.objects.SongEvent;

public class SongItem extends JPanel {

    private JCheckBox favoriteBtn;
    private JLabel durationHolder;
    private JLabel titleHolder;
    private JLabel artistHolder;
    private JLabel imageHolder;
    private JPanel imagePanel;
    private JPanel titlePanel;
    private JLabel optionsButton;
    private GridBagConstraints gridBagConstraints;

    private ISongRequestListener songRequestListener;
    private ISongUpdateListener songUpdateListener;

    public SongItem(SongModel songModel) {
        this.onUISetup();

        titleHolder.setText(songModel.getTitle());
        artistHolder.setText(songModel.getArtist());

        int minutes = songModel.getDuration() / 60;
        int remainingSeconds = songModel.getDuration() % 60;
        durationHolder.setText(String.format("%02d:%02d", minutes, remainingSeconds));

        ImageIcon songImageIcon = new ImageIcon(new ImageIcon(songModel.getImgPath()).getImage().getScaledInstance(80, 75, Image.SCALE_SMOOTH));
        imageHolder.setIcon(songImageIcon);

        imageHolder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (songRequestListener == null)
                    return;

                SongEvent songEvent = new SongEvent(
                    e,
                    songModel.getSongId(),
                    songModel.getTitle(),
                    songModel.getArtist(),
                    songModel.getLyrics(),
                    songModel.getDuration(),
                    songModel.getSongPath(),
                    songModel.getImgPath()
                );
                songRequestListener.onSongRequest(songEvent);
            }
        });
    
        favoriteBtn.setSelected(songModel.isInFavorites());

        favoriteBtn.addActionListener(e -> {
            SongEvent songEvent = new SongEvent(
                this,
                songModel.getSongId(),
                songModel.getTitle(),
                songModel.getArtist(),
                songModel.getLyrics(),
                songModel.getDuration(),
                songModel.getSongPath(),
                songModel.getImgPath(),
                favoriteBtn.isSelected()
            );
            songUpdateListener.onSongUpdate(songEvent);
        });
    }

    public void setSongRequestListener(ISongRequestListener songRequestListener) {
        this.songRequestListener = songRequestListener;
    }

    public void setSongUpdateListener(ISongUpdateListener songUpdateListener) {
        this.songUpdateListener = songUpdateListener;
    }

    private void onUISetup() {
        imagePanel = new JPanel();
        favoriteBtn = new JCheckBox();
        imageHolder = new JLabel();
        titlePanel = new JPanel();
        titleHolder = new JLabel();
        artistHolder = new JLabel();
        durationHolder = new JLabel();
        optionsButton = new JLabel();

        JPanel jPanel33 = new JPanel();
        JPanel jPanel34 = new JPanel();
        JPanel jPanel35 = new JPanel();

        this.setBackground(new java.awt.Color(30, 30, 30));
        this.setLayout(new java.awt.GridLayout(1, 0));

        jPanel33.setBackground(new java.awt.Color(30, 30, 30));
        jPanel33.setLayout(new java.awt.BorderLayout());

        imagePanel.setBackground(new java.awt.Color(30, 30, 30));
        imagePanel.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 65;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 14);
        imagePanel.add(favoriteBtn, gridBagConstraints);
        favoriteBtn.setOpaque(false);

        imageHolder.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\album-cover.jpg"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        imagePanel.add(imageHolder, gridBagConstraints);

        jPanel33.add(imagePanel, java.awt.BorderLayout.WEST);

        titlePanel.setBackground(new java.awt.Color(30, 30, 30));
        titlePanel.setLayout(new java.awt.GridBagLayout());

        titleHolder.setFont(new java.awt.Font("Segoe UI", 1, 20));
        titleHolder.setForeground(new java.awt.Color(255, 255, 255));
        titleHolder.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        titlePanel.add(titleHolder, gridBagConstraints);

        artistHolder.setFont(new java.awt.Font("Segoe UI", 1, 20));
        artistHolder.setForeground(new java.awt.Color(204, 204, 204));
        artistHolder.setText("Artist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 25, 0, 22);
        titlePanel.add(artistHolder, gridBagConstraints);

        jPanel33.add(titlePanel, java.awt.BorderLayout.EAST);

        this.add(jPanel33);

        jPanel35.setBackground(new java.awt.Color(30, 30, 30));
        jPanel35.setLayout(new java.awt.BorderLayout());

        durationHolder.setFont(new java.awt.Font("Segoe UI", 1, 20));
        durationHolder.setForeground(new java.awt.Color(255, 255, 255));
        durationHolder.setText("4:07");
        jPanel35.add(durationHolder, java.awt.BorderLayout.EAST);

        this.add(jPanel35);

        jPanel34.setBackground(new java.awt.Color(30, 30, 30));
        jPanel34.setLayout(new java.awt.BorderLayout());

        optionsButton.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\dots.png"));
        jPanel34.add(optionsButton, java.awt.BorderLayout.EAST);

        this.add(jPanel34);
    }
}
