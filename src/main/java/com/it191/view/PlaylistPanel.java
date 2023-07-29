package com.it191.view;

import javax.swing.JPanel;

public class PlaylistPanel extends JPanel {
    
    private javax.swing.JLabel imagePlaylist1;
    private javax.swing.JLabel imagePlaylist2;
    private javax.swing.JLabel imagePlaylist3;
    private javax.swing.JLabel imagePlaylist4;
    private javax.swing.JLabel imagePlaylist5;
    private javax.swing.JLabel imagePlaylist6;
    private javax.swing.JLabel imagePlaylist7;
    private javax.swing.JLabel imagePlaylist8;
    private javax.swing.JLabel nameOfPlaylist1;
    private javax.swing.JLabel nameOfPlaylist2;
    private javax.swing.JLabel nameOfPlaylist3;
    private javax.swing.JLabel nameOfPlaylist4;
    private javax.swing.JLabel nameOfPlaylist5;
    private javax.swing.JLabel nameOfPlaylist6;
    private javax.swing.JLabel nameOfPlaylist7;
    private javax.swing.JLabel nameOfPlaylist8;
    private javax.swing.JPanel playlistButton1;
    private javax.swing.JPanel playlistButton2;
    private javax.swing.JPanel playlistButton3;
    private javax.swing.JPanel playlistButton4;
    private javax.swing.JPanel playlistButton5;
    private javax.swing.JPanel playlistButton6;
    private javax.swing.JPanel playlistButton7;
    private javax.swing.JPanel playlistButton8;

    public PlaylistPanel(){

        playlistButton1 = new javax.swing.JPanel();
        nameOfPlaylist1 = new javax.swing.JLabel();
        imagePlaylist1 = new javax.swing.JLabel();
        playlistButton2 = new javax.swing.JPanel();
        nameOfPlaylist2 = new javax.swing.JLabel();
        imagePlaylist2 = new javax.swing.JLabel();
        playlistButton3 = new javax.swing.JPanel();
        nameOfPlaylist3 = new javax.swing.JLabel();
        imagePlaylist3 = new javax.swing.JLabel();
        playlistButton4 = new javax.swing.JPanel();
        nameOfPlaylist4 = new javax.swing.JLabel();
        imagePlaylist4 = new javax.swing.JLabel();
        playlistButton5 = new javax.swing.JPanel();
        nameOfPlaylist5 = new javax.swing.JLabel();
        imagePlaylist5 = new javax.swing.JLabel();
        playlistButton6 = new javax.swing.JPanel();
        nameOfPlaylist6 = new javax.swing.JLabel();
        imagePlaylist6 = new javax.swing.JLabel();
        playlistButton7 = new javax.swing.JPanel();
        nameOfPlaylist7 = new javax.swing.JLabel();
        imagePlaylist7 = new javax.swing.JLabel();
        playlistButton8 = new javax.swing.JPanel();
        nameOfPlaylist8 = new javax.swing.JLabel();
        imagePlaylist8 = new javax.swing.JLabel();
        java.awt.GridBagConstraints gridBagConstraints;


        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        playlistButton1.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton1.setPreferredSize(new java.awt.Dimension(255, 255));
        playlistButton1.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist1.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist1.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist1.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton1.add(nameOfPlaylist1, gridBagConstraints);

        imagePlaylist1.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton1.add(imagePlaylist1, gridBagConstraints);

        this.add(playlistButton1);

        playlistButton2.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton2.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist2.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist2.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist2.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton2.add(nameOfPlaylist2, gridBagConstraints);

        imagePlaylist2.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton2.add(imagePlaylist2, gridBagConstraints);

        this.add(playlistButton2);

        playlistButton3.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton3.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist3.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist3.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist3.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton3.add(nameOfPlaylist3, gridBagConstraints);

        imagePlaylist3.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton3.add(imagePlaylist3, gridBagConstraints);

        this.add(playlistButton3);

        playlistButton4.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton4.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist4.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist4.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist4.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton4.add(nameOfPlaylist4, gridBagConstraints);

        imagePlaylist4.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton4.add(imagePlaylist4, gridBagConstraints);

        this.add(playlistButton4);

        playlistButton5.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton5.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist5.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist5.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist5.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton5.add(nameOfPlaylist5, gridBagConstraints);

        imagePlaylist5.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton5.add(imagePlaylist5, gridBagConstraints);

        this.add(playlistButton5);

        playlistButton6.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton6.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist6.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist6.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist6.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton6.add(nameOfPlaylist6, gridBagConstraints);

        imagePlaylist6.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton6.add(imagePlaylist6, gridBagConstraints);

        this.add(playlistButton6);

        playlistButton7.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton7.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist7.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist7.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist7.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton7.add(nameOfPlaylist7, gridBagConstraints);

        imagePlaylist7.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton7.add(imagePlaylist7, gridBagConstraints);

        this.add(playlistButton7);

        playlistButton8.setBackground(new java.awt.Color(30, 30, 30));
        playlistButton8.setLayout(new java.awt.GridBagLayout());

        nameOfPlaylist8.setFont(new java.awt.Font("STXihei", 1, 24)); // NOI18N
        nameOfPlaylist8.setForeground(new java.awt.Color(255, 255, 255));
        nameOfPlaylist8.setText("Name of playlist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 27, 18, 26);
        playlistButton8.add(nameOfPlaylist8, gridBagConstraints);

        imagePlaylist8.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\playlist-image.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 35, 0, 0);
        playlistButton8.add(imagePlaylist8, gridBagConstraints);

        this.add(playlistButton8);
    }
}
