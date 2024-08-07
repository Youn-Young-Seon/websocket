package com.example.websocket.api;

import com.example.websocket.domain.BoxFill;
import com.example.websocket.dto.BoxTaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BoxController {
    private final BoxFill boxFill;

    public BoxController(BoxFill boxFill) {
        this.boxFill = boxFill;
    }

    @MessageMapping("/fill")
//    @SendTo("/box/fill")
    public ResponseEntity<BoxTaskDto> boxFill(BoxTaskDto boxTaskDto) {
        BoxTaskDto execute = boxFill.execute(boxTaskDto);
        return ResponseEntity.ok(execute);
    }
}
