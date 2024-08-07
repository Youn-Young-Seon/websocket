package com.example.websocket.domain;

import com.example.websocket.dto.BoxTaskDto;
import com.example.websocket.enums.BoxTaskEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoxTask {
    private BoxTaskEnum boxTaskType;
    private List<Box> boxes = new ArrayList<>();

    private BoxTask(BoxTaskEnum boxTaskType, List<Box> boxes) {
        this.boxTaskType = boxTaskType;
        this.boxes = boxes;
    }

    public static BoxTask toBoxTask(BoxTaskDto boxTaskDto) {
        return new BoxTask(boxTaskDto.getBoxTaskType(), boxTaskDto.getBoxes());
    }

    public BoxTask executeBoxFill() {
        return null;
    }
}
