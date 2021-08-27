package me.myte.gpc.test.services;

import lombok.AllArgsConstructor;
import me.myte.gpc.test.models.Request;
import me.myte.gpc.test.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public Request findById(long id) {
        Optional<Request> ans = requestRepository.findById(id);
        if (ans.isEmpty()) {
            return null;
        }
        return ans.get();
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public void add(Request request) {
        requestRepository.save(request);
    }
}
