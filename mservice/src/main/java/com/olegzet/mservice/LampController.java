package com.olegzet.mservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

import java.util.UUID;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@RestController
@Scope(SCOPE_REQUEST)
@Path("/lamps")
public class LampController {
    TurnService turnService;

    public LampController(@Autowired TurnService turnService) {
        this.turnService = turnService;
    }

    @PostMapping
    public void lamps() {
            String uuid = UUID.randomUUID().toString();
            turnService.turnOn(uuid);
            return

    }



}
