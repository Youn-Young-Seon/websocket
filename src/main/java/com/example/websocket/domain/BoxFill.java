package com.example.websocket.domain;

import com.example.websocket.dto.BoxTaskDto;
import com.example.websocket.thread.BoxFillThread;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class BoxFill {
    private Queue<BoxTask> queue = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private final SimpMessagingTemplate template;

    public BoxFill(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void execute(BoxTaskDto boxTaskDto) {
        BoxTask boxTask = BoxTask.toBoxTask(boxTaskDto);
        lock.lock();
        try {
            queue.offer(boxTask);
            if (queue.size() > 2) condition.await();
            new Thread(new BoxFillThread(boxTask, template, queue, lock, condition)).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
