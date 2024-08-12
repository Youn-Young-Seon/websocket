package com.example.websocket.thread;

import com.example.websocket.domain.Box;
import com.example.websocket.domain.BoxTask;
import com.example.websocket.dto.BoxTaskDto;
import com.example.websocket.enums.BoxTaskEnum;
import com.example.websocket.utils.ThreadSleep;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class BoxFillThread implements Runnable {
    private final BoxTask boxTask;
    private final SimpMessagingTemplate template;
    private final Queue<BoxTask> queue;
    private final Lock lock;
    private final Condition condition;
    private final int second;

    private boolean isRunning = true;

    public BoxFillThread(BoxTask boxTask, SimpMessagingTemplate template, Queue<BoxTask> queue, Lock lock, Condition condition, int second) {
        this.boxTask = boxTask;
        this.template = template;
        this.queue = queue;
        this.lock = lock;
        this.condition = condition;
        this.second = second;
    }

    @Override
    public void run() {
        while (isRunning) {
            boxTask.getBoxes().forEach(task -> {
                task.setFill(true);
                ThreadSleep.sleep(second);
                template.convertAndSend("/box/fill", BoxTaskDto.of(boxTask));
            });

            boolean isFinish = boxTask.getBoxes().stream().allMatch(Box::getFill);

            if (isFinish) {
                BoxTaskDto respBoxTaskDto = BoxTaskDto.of(boxTask);
                respBoxTaskDto.setBoxTaskType(BoxTaskEnum.FINISH);
                template.convertAndSend("/box/fill", respBoxTaskDto);

                lock.lock();
                try {
                    queue.poll();
                    condition.signal();
                    isRunning = false;
                } finally {
                    lock.unlock();
                }
                break;
            }
        }
    }
}
