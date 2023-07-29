package com.it191.view.listeners;

import java.util.EventListener;

import com.it191.view.objects.SongEvent;

public interface ISongEventListener extends EventListener {
    public void onSongUpdated(SongEvent evt);
}
