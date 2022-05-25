package com.example.bruce.service;

import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class ChatSocket extends WebSocket<String> {
    private static List<Out<String>> outputs = new ArrayList<>();

    public WebSocket<String> open() {
        return new ChatSocket();
    }

    @Override
    public void onReady(In<String> in, Out<String> out) {
        outputs.add(out);
        in.onMessage(message -> {
            System.out.println("receiverMessage: " + message);
            outputs.forEach(ot -> ot.write(message));
        });

        in.onClose(() -> {
            outputs.remove(out);
        });
    }
}