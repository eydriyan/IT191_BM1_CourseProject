package com.it191.view.listeners;

import java.util.EventListener;

import com.it191.view.objects.SongEvent;

public interface ISongUpdateListener extends EventListener {
    public void onSongUpdate(SongEvent evt);
}
