package com.example.websocket.dto;

import com.example.websocket.domain.Box;
import com.example.websocket.domain.BoxTask;
import com.example.websocket.enums.BoxTaskEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoxTaskDto {
    private BoxTaskEnum boxTaskType;
    private List<Box> boxes = new ArrayList<>();

    private BoxTaskDto(BoxTaskEnum boxTaskType, List<Box> boxes) {
        this.boxTaskType = boxTaskType;
        this.boxes = boxes;
    }

    public static BoxTaskDto of(BoxTask boxTask) {
        return new BoxTaskDto(boxTask.getBoxTaskType(), boxTask.getBoxes());
    }
}
