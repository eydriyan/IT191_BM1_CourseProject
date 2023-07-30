package com.it191.view;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlaylistItem extends JPanel {
    private JLabel imagePlaylist;
    private JLabel nameOfPlaylist;
    private GridBagConstraints gridBagConstraints;

    public PlaylistItem(String name) {
        this.onUISetup();

        nameOfPlaylist.setText(name);
    }

    private void onUISetup() {
        nameOfPlaylist = new JLabel();
        imagePlaylist = new JLabel();

        this.setBackground(new java.awt.Color(30, 30, 30));
        this.setPreferredSize(new java.awt.Dimension(255, 255));
        this.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        this.add(nameOfPlaylist, gridBagConstraints);

        imagePlaylist.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        this.add(imagePlaylist, gridBagConstraints);
    }
}
