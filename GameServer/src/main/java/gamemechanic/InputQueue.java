package gameserver;

import client.Action;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InputQueue {

    private static BlockingQueue<Action> instance = new LinkedBlockingQueue<>();
    public static BlockingQueue<Action> getInstance() {
        return instance;
    }

}