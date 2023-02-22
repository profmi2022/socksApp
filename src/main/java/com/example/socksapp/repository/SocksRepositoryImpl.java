package com.example.socksapp.repository;

import com.example.socksapp.model.Socks;
import com.example.socksapp.services.ValidationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
@RequiredArgsConstructor
public class SocksRepositoryImpl implements SocksRepository {

    private Map<Socks, Integer> socksMap = new HashMap<>();
    private final ValidationService validationService;

    public Map<Socks, Integer> getSocksMap() {
        return socksMap;
    }

    @Override
    public Integer save(Socks socks, Integer quantity) {
        if (!validationService.isValid(socks, quantity)) {
            throw new IllegalArgumentException();
        }
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + quantity);
        } else {
            socksMap.put(socks, quantity);
        }
        return socksMap.get(socks);
    }

    @Override
    public Integer remove(Socks socks, Integer quantity) {
        if (!validationService.isValid(socks, quantity)) {
            throw new IllegalArgumentException();
        }
        if (socksMap.containsKey(socks)) {
            if (socksMap.get(socks) >= quantity) {
                socksMap.replace(socks, socksMap.get(socks) - quantity);
                return quantity;
            } else {
                Integer leftover = socksMap.get(socks);
                socksMap.remove(socks);
                return leftover;
            }
        } else {
            throw new NotFoundException("Таких носков нет на складе");
        }
    }
}

