package com.song.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientOneServiceImpl implements ClientOneService {

    @Autowired
    private  ClientOneFeign clientOneFeign;

    @Override
    public String hello() {
        return clientOneFeign.hello();
    }
}
