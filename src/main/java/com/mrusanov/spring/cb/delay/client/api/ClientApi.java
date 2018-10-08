package com.mrusanov.spring.cb.delay.client.api;

import com.mrusanov.spring.cb.delay.client.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ClientApi {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.delayEndpoint}")
    private String serverDelayEndpoint;

    private ReadService readService;

    @Autowired
    public ClientApi(ReadService readService) {
        this.readService = readService;
    }

    @RequestMapping(value = "/invoke-server/delay/{delay}", params = "reliable", method = RequestMethod.GET)
    public String invokeServerWithDelay(@PathVariable(value = "delay") int delay, @RequestParam(value = "reliable") boolean reliable) {
        URI uri = URI.create(serverUrl + serverDelayEndpoint + delay);
        if(reliable) {
            return readService.readReliably(uri);
        }
        return readService.read(uri);
    }

}
