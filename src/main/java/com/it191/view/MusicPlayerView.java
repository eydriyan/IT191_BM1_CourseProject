package com.it191.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.it191.controller.MusicController;
import com.it191.view.listeners.ISongRequestListener;
import com.it191.view.listeners.IViewUpdateListener;
import com.it191.view.listeners.IPlayerUpdateListener;
import com.it191.view.objects.SongEvent;

public class MusicPlayerView extends JPanel implements IPlayerUpdateListener, ISongRequestListener, IViewUpdateListener {

    MusicController musicController;

    private JPanel favoritesBtn;
    private JLabel appSongImage;
    private JLabel playerSongImage;
    private JLabel lyricsBtn;
    private JLabel nameOfArtist;
    private JLabel nextBtn;
    private JLabel pauseBtn;
    private JLabel playBtn;
    private JLabel previousBtn;
    private JTextField searchBar;
    private JSlider seekSlider;
    private JLabel songTitle;
    private JPanel songsBtn;
    private JLabel stopBtn;
    private JSlider volumeSlider;
    private GridBagConstraints gridBagConstraints;

    private JLabel songsLabel;
    private JLabel favoritesLabel;
    private JPanel panelHolder;

    private LyricsPanel lyricsPanel;
    private FavoritesPanel favoritesPanel;
    private SongsPanel songsPanel;

    private String currentLoadedView;

    javax.swing.Timer seekSliderTimerUpdate = new javax.swing.Timer(90, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double percent = musicController.getCurrentPositionPercent();
                seekSlider.setValue((int) (percent * seekSlider.getMaximum()));
            }
        });

    public MusicPlayerView() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 1000);

        this.onUISetup();
        this.onControlsSetup();
        this.changeViewControlSetup();
    }

    @Override
    public void onPlayerUpdateSong(SongEvent evt) {
        songTitle.setText(evt.getTitle());
        nameOfArtist.setText(evt.getArtist());
        lyricsPanel.setLyrics(evt.getLyrics());

        ImageIcon playerSongImageIcon = new ImageIcon(new ImageIcon(evt.getImgPath()).getImage().getScaledInstance(110, 105, Image.SCALE_SMOOTH));
        playerSongImage.setIcon(playerSongImageIcon);
    }

    @Override
    public void onSongRequest(SongEvent evt) {
        seekSliderTimerUpdate.stop();
        musicController.stopSong();
        musicController.songToPlay(evt);
        seekSlider.setValue(0);
    }

    @Override
    public void onViewUpdated(SongEvent evt) {
        ImageIcon appImageIcon = new ImageIcon(new ImageIcon(evt.getImgPath()).getImage().getScaledInstance(210, 190, Image.SCALE_SMOOTH));
        appSongImage.setIcon(appImageIcon);
    }

    private void changeViewControlSetup() {
        panelHolder.add(lyricsPanel, "Lyrics");
        panelHolder.add(favoritesPanel, "Favorites");
        panelHolder.add(songsPanel, "Songs");

        CardLayout cardLayout = (CardLayout) panelHolder.getLayout();
        cardLayout.show(panelHolder, "Songs");
        songsPanel.onForceDatabaseReload();
        currentLoadedView = "Songs";

        lyricsBtn.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    CardLayout cardLayout = (CardLayout) panelHolder.getLayout();
                    cardLayout.show(panelHolder, "Lyrics");
                }
            }
        );

        songsBtn.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    CardLayout cardLayout = (CardLayout) panelHolder.getLayout();
                    cardLayout.show(panelHolder, "Songs");
                    songsPanel.onForceDatabaseReload();
                    currentLoadedView = "Songs";
                }
            }
        );

        favoritesBtn.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    CardLayout cardLayout = (CardLayout) panelHolder.getLayout();
                    cardLayout.show(panelHolder, "Favorites");
                    favoritesPanel.onForceDatabaseReload();
                    currentLoadedView = "Favorites";
                }
            }
        );
    }

    private void onControlsSetup() {
        currentLoadedView = "";
        lyricsPanel = new LyricsPanel();
        favoritesPanel = new FavoritesPanel();
        songsPanel = new SongsPanel();

        songsPanel.setViewUpdateListener(this);
        favoritesPanel.setViewUpdateListener(this);

        musicController = new MusicController();
        musicController.setSongUpdateListener(this);

        songsPanel.setSongRequestListener(this);
        favoritesPanel.setSongRequestListener(this);

        appSongImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentLoadedView.equals("Songs")) {
                    musicController.addSongFromCollection(
                        songsPanel.getCurrentSongs()
                    );

                    JOptionPane.showMessageDialog(null, "Songs Loaded to Queue", "Songs", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (currentLoadedView.equals("Favorites")) {
                    musicController.addSongFromCollection(
                        favoritesPanel.getCurrentSongs()
                    );

                    JOptionPane.showMessageDialog(null, "Songs Loaded to Queue", "Favorites", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        searchBar.addActionListener(e -> {
            songsPanel.setSongFilterByName(searchBar.getText());
            favoritesPanel.setSongFilterByName(searchBar.getText());
        });

        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!musicController.isSongLoaded()) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No Song to Play",
                        "Play Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if(!musicController.playSong()) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Already Playing",
                        "Play Alert",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                seekSliderTimerUpdate.restart();
                
            }
        });
        pauseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seekSliderTimerUpdate.stop();
                musicController.pauseSong();
            }
        });
        stopBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seekSliderTimerUpdate.stop();
                musicController.stopSong();
                seekSlider.setValue(0);
            }
        });
        previousBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seekSliderTimerUpdate.stop();
                musicController.stopSong();
                seekSlider.setValue(0);

                if(!musicController.prevSong()) {
                    JOptionPane.showMessageDialog(null, "Song History has No Songs yet", "Prev Song", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        nextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seekSliderTimerUpdate.stop();
                musicController.stopSong();
                seekSlider.setValue(0);

                if(!musicController.nextSong()) {
                    JOptionPane.showMessageDialog(null, "No Queued Songs Left", "Next Song", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        seekSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                seekSliderTimerUpdate.stop();
                musicController.pauseSong();
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
                        timer = new javax.swing.Timer(50, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!seekSlider.getValueIsAdjusting()) {
                                    double percent = (double) seekSlider.getValue() / (double) seekSlider.getMaximum();
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
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = (float) volumeSlider.getValue() / (float) volumeSlider.getMaximum();
                musicController.setVolume(volume);
            }
        });
    }

    private void onUISetup() {
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        lyricsBtn = new JLabel();
        songTitle = new JLabel();
        nameOfArtist = new JLabel();
        appSongImage = new JLabel();
        playerSongImage = new JLabel();
        seekSlider = new JSlider(JSlider.HORIZONTAL, 0, 100000, 0);
        previousBtn = new JLabel();
        pauseBtn = new JLabel();
        playBtn = new JLabel();
        stopBtn = new JLabel();
        nextBtn = new JLabel();
        favoritesBtn = new JPanel();
        songsBtn = new JPanel();
        searchBar = new JTextField();
        songsLabel = new JLabel();
        favoritesLabel = new JLabel();
        panelHolder = new JPanel();

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel14 = new JPanel();
        JLabel volumeIcon = new JLabel();
        JPanel playerSongImageContainer = new JPanel();
        JPanel jPanel18 = new JPanel();
        JPanel jPanel15 = new JPanel();
        JPanel jPanel17 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JLabel jLabel7 = new JLabel();
        JLabel jLabel8 = new JLabel();
        JPanel jPanel5 = new JPanel();
        JPanel jPanel9 = new JPanel();
        JPanel jPanel7 = new JPanel();
        JPanel jPanel8 = new JPanel();
        JPanel jPanel10 = new JPanel();
        JPanel jPanel12 = new JPanel();
        JPanel jPanel11 = new JPanel();
        JPanel appSongImageContainer = new JPanel();
        JPanel jPanel21 = new JPanel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel9 = new JLabel();
        JPanel jPanel13 = new JPanel();
        JPanel jPanel23 = new JPanel();
        JPanel jPanel24 = new JPanel();
        JLabel jLabel12 = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();

        setMinimumSize(new java.awt.Dimension(1400, 900));
        setPreferredSize(new java.awt.Dimension(1400, 900));

        jPanel1.setBackground(new java.awt.Color(244, 245, 254));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(82, 89, 114));
        jPanel2.setPreferredSize(new java.awt.Dimension(1300, 130));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(72, 51, 87));
        jPanel14.setPreferredSize(new java.awt.Dimension(302, 113));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        volumeIcon.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\volumeIcon.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jPanel14.add(volumeIcon, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 145;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 8, 0, 32);
        jPanel14.add(volumeSlider, gridBagConstraints);
        volumeSlider.setOpaque(false);

        lyricsBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\lyrics-icon.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 40, 0);
        jPanel14.add(lyricsBtn, gridBagConstraints);

        jPanel2.add(jPanel14, java.awt.BorderLayout.EAST);

        playerSongImageContainer.setBackground(new java.awt.Color(72, 51, 87));
        playerSongImageContainer.setPreferredSize(new java.awt.Dimension(302, 113));
        playerSongImageContainer.setLayout(new java.awt.GridBagLayout());

        songTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        songTitle.setForeground(new java.awt.Color(255, 255, 255));
        songTitle.setText("Pick a song");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 12, 0, 0);
        playerSongImageContainer.add(songTitle, gridBagConstraints);

        nameOfArtist.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        nameOfArtist.setForeground(new java.awt.Color(255, 255, 255));
        nameOfArtist.setText("??");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 28);
        playerSongImageContainer.add(nameOfArtist, gridBagConstraints);

        playerSongImage.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\album-cover - Copy.jpg"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 0);
        playerSongImageContainer.add(playerSongImage, gridBagConstraints);

        jPanel2.add(playerSongImageContainer, java.awt.BorderLayout.WEST);

        jPanel18.setBackground(new java.awt.Color(72, 51, 87));

        jPanel15.setBackground(new java.awt.Color(72, 51, 87));
        jPanel15.setPreferredSize(new java.awt.Dimension(735, 130));
        jPanel15.setLayout(new java.awt.BorderLayout(0, -35));
        jPanel15.add(seekSlider, java.awt.BorderLayout.CENTER);
        seekSlider.setOpaque(false);

        jPanel17.setBackground(new java.awt.Color(72, 51, 87));
        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 25));

        previousBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\previous-button.png"));
        jPanel17.add(previousBtn);

        pauseBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\pause-button.png"));
        jPanel17.add(pauseBtn);

        playBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\play-button.png"));
        jPanel17.add(playBtn);

        stopBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\stop-button.png"));
        jPanel17.add(stopBtn);

        nextBtn.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\next-button.png"));
        jPanel17.add(nextBtn);

        jPanel15.add(jPanel17, java.awt.BorderLayout.NORTH);

        jPanel18.add(jPanel15);

        jPanel2.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(72, 51, 87));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 100));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\logo.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 580, 16, 0);
        jPanel3.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Music Player");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 16, 0, 601);
        jPanel3.add(jLabel8, gridBagConstraints);

        jPanel4.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setPreferredSize(new java.awt.Dimension(1300, 10));
        jPanel5.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setPreferredSize(new java.awt.Dimension(1300, 10));
        jPanel5.add(jPanel7, java.awt.BorderLayout.SOUTH);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(10, 494));
        jPanel5.add(jPanel8, java.awt.BorderLayout.WEST);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 494));
        jPanel5.add(jPanel10, java.awt.BorderLayout.EAST);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setPreferredSize(new java.awt.Dimension(250, 494));
        jPanel11.setLayout(new java.awt.BorderLayout());

        appSongImageContainer.setBackground(new java.awt.Color(51, 51, 51));
        appSongImageContainer.setPreferredSize(new java.awt.Dimension(250, 205));

        appSongImage.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\album-cover - Copy - Copy.jpg")); // NOI18N
        appSongImage.setPreferredSize(new java.awt.Dimension(210, 190));
        appSongImageContainer.add(appSongImage);

        jPanel11.add(appSongImageContainer, java.awt.BorderLayout.NORTH);

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));
        jPanel21.setLayout(new java.awt.GridBagLayout());

        songsBtn.setBackground(new java.awt.Color(102, 102, 102));
        songsBtn.setPreferredSize(new java.awt.Dimension(250, 97));
        songsBtn.setLayout(new java.awt.GridBagLayout());

        songsLabel.setFont(new java.awt.Font("Segoe UI", 1, 30));
        songsLabel.setForeground(new java.awt.Color(255, 255, 255));
        songsLabel.setText("Songs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 31, 31, 76);
        songsBtn.add(songsLabel, gridBagConstraints);

        jLabel4.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\songs-icon.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 26, 31, 0);
        songsBtn.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(54, 0, 0, 0);
        jPanel21.add(songsBtn, gridBagConstraints);

        ///////////////////////
        JPanel playlistsBtn = new JPanel();
        JLabel playlistLabel = new JLabel();
        JLabel jLabel14 = new JLabel();

        playlistsBtn.setBackground(new java.awt.Color(51, 51, 51));
        playlistsBtn.setPreferredSize(new java.awt.Dimension(250, 97));
        playlistsBtn.setLayout(new java.awt.GridBagLayout());

        playlistLabel.setFont(new java.awt.Font("Segoe UI", 1, 30));
        playlistLabel.setForeground(new java.awt.Color(255, 255, 255));
        playlistLabel.setText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 18, 29, 48);
        playlistsBtn.add(playlistLabel, gridBagConstraints);


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 24, 29, 0);
        playlistsBtn.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 171, 0);
        jPanel21.add(playlistsBtn, gridBagConstraints);

        ///////////////////////

        favoritesBtn.setBackground(new java.awt.Color(102, 102, 102));
        favoritesBtn.setPreferredSize(new java.awt.Dimension(250, 97));
        favoritesBtn.setLayout(new java.awt.GridBagLayout());

        favoritesLabel.setFont(new java.awt.Font("Segoe UI", 1, 30));
        favoritesLabel.setForeground(new java.awt.Color(255, 255, 255));
        favoritesLabel.setText("Favorites");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 25, 27, 34);
        favoritesBtn.add(favoritesLabel, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\favorites-icon.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 25, 0, 0);
        favoritesBtn.add(jLabel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        jPanel21.add(favoritesBtn, gridBagConstraints);

        jPanel11.add(jPanel21, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel11, java.awt.BorderLayout.WEST);

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));
        jPanel13.setDoubleBuffered(false);
        jPanel13.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel23.setBackground(new java.awt.Color(51, 51, 51));
        jPanel23.setPreferredSize(new java.awt.Dimension(1193, 50));
        jPanel23.setLayout(new java.awt.BorderLayout());

        searchBar.setBackground(new java.awt.Color(51, 51, 51));
        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 20));
        searchBar.setForeground(new java.awt.Color(255, 255, 255));
        searchBar.setBorder(null);
        jPanel23.add(searchBar, java.awt.BorderLayout.CENTER);

        jPanel24.setBackground(new java.awt.Color(51, 51, 51));
        jPanel24.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon("target\\classes\\com\\it191\\view\\images\\search-icon.png"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 19, 0, 17);
        jPanel24.add(jLabel12, gridBagConstraints);

        jPanel23.add(jPanel24, java.awt.BorderLayout.WEST);

        jPanel13.add(jPanel23, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(null);

        panelHolder.setBackground(new java.awt.Color(51, 51, 51));
        panelHolder.setPreferredSize(new java.awt.Dimension(200, 1000));
        panelHolder.setLayout(new CardLayout());

        jScrollPane1.setViewportView(panelHolder);

        jPanel13.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        this.add(jPanel1, java.awt.BorderLayout.CENTER);
    }
}
