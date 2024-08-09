package com.example.websocket.thread;

import com.example.websocket.domain.Box;
import com.example.websocket.domain.BoxTask;
import com.example.websocket.dto.BoxTaskDto;
import com.example.websocket.enums.BoxTaskEnum;
import com.example.websocket.utils.ThreadSleep;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class BoxFillThread implements Runnable {
    private final BoxTask boxTask;
    private final SimpMessagingTemplate template;
    private final Queue<BoxTask> queue;

    private final int second = new Random().nextInt(4) * 1000;

    public BoxFillThread(BoxTask boxTask, SimpMessagingTemplate template, Queue<BoxTask> queue) {
        this.boxTask = boxTask;
        this.template = template;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
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
                queue.poll();
                break;
            }
        }
    }
}
