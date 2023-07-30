package com.it191.view.listeners;

import java.util.EventListener;

import com.it191.view.objects.SongEvent;

public interface ISongRequestListener extends EventListener {
    public void onSongRequest(SongEvent evt);
}
