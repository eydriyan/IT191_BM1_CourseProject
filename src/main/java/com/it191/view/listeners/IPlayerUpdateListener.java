package com.it191.view.listeners;

import java.util.EventListener;

import com.it191.view.objects.SongEvent;

public interface IPlayerUpdateListener extends EventListener {
    public void onPlayerUpdateSong(SongEvent evt);
}
