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
            if (queue.size() > 2) condition.await();
            queue.offer(boxTask);
            new Thread(new BoxFillThread(boxTask, template, queue)).start();
            if (queue.isEmpty()) condition.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
