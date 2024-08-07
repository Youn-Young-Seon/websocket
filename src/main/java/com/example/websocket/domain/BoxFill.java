package com.example.websocket.domain;

import com.example.websocket.dto.BoxTaskDto;
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

    public BoxTaskDto execute(BoxTaskDto boxTaskDto) {
        lock.lock();
        try {
            BoxTask boxTask = BoxTask.toBoxTask(boxTaskDto);
            queue.offer(boxTask);
            if (queue.size() == 5) condition.await();

            // template 사용

            if (queue.isEmpty()) condition.signal();
            BoxTask poll = queue.poll();
            return BoxTaskDto.of(poll);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
