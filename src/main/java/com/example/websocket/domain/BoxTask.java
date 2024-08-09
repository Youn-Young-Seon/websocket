package com.example.websocket.domain;

import com.example.websocket.dto.BoxTaskDto;
import com.example.websocket.enums.BoxTaskEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoxTask {
    private Integer idx;
    private BoxTaskEnum boxTaskType;
    private List<Box> boxes = new ArrayList<>();

    public BoxTask(Integer idx, BoxTaskEnum boxTaskType, List<Box> boxes) {
        this.idx = idx;
        this.boxTaskType = boxTaskType;
        this.boxes = boxes;
    }

    public static BoxTask toBoxTask(BoxTaskDto boxTaskDto) {
        return new BoxTask(boxTaskDto.getIdx(), boxTaskDto.getBoxTaskType(), boxTaskDto.getBoxes());
    }
}
