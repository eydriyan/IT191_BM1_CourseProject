package com.it191.view;

import javax.swing.JPanel;

public class SongsPanel extends JPanel{

    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JLabel optionsButton;
    private javax.swing.JPanel song1Btn;
    java.awt.GridBagConstraints gridBagConstraints;
    

    public SongsPanel(){
        
        song1Btn = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        optionsButton = new javax.swing.JLabel();

        this.setBackground(new java.awt.Color(51, 51, 51));
        this.setLayout(new java.awt.GridLayout(10, 1));

        song1Btn.setBackground(new java.awt.Color(30, 30, 30));
        song1Btn.setLayout(new java.awt.GridLayout(1, 0));

        jPanel33.setBackground(new java.awt.Color(30, 30, 30));
        jPanel33.setLayout(new java.awt.BorderLayout());

        jPanel31.setBackground(new java.awt.Color(30, 30, 30));
        jPanel31.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 65;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 14);
        jPanel31.add(jCheckBox1, gridBagConstraints);
        jCheckBox1.setOpaque(false);

        jLabel22.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\album-cover.jpg"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel31.add(jLabel22, gridBagConstraints);

        jPanel33.add(jPanel31, java.awt.BorderLayout.WEST);

        jPanel32.setBackground(new java.awt.Color(30, 30, 30));
        jPanel32.setLayout(new java.awt.GridBagLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        jPanel32.add(jLabel20, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("Artist");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 25, 0, 22);
        jPanel32.add(jLabel21, gridBagConstraints);

        jPanel33.add(jPanel32, java.awt.BorderLayout.EAST);

        song1Btn.add(jPanel33);

        jPanel35.setBackground(new java.awt.Color(30, 30, 30));
        jPanel35.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("4:07");
        jPanel35.add(jLabel13, java.awt.BorderLayout.EAST);

        song1Btn.add(jPanel35);

        jPanel34.setBackground(new java.awt.Color(30, 30, 30));
        jPanel34.setLayout(new java.awt.BorderLayout());

        optionsButton.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\dots.png"));
        jPanel34.add(optionsButton, java.awt.BorderLayout.EAST);

        song1Btn.add(jPanel34);

        this.add(song1Btn);
    }
}
