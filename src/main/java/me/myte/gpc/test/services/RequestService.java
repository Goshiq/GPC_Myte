package me.myte.gpc.test.services;

import lombok.AllArgsConstructor;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void add(Request request) {
        requestRepository.save(request);
    }
}
