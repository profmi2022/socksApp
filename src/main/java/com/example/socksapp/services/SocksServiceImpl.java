package com.example.socksapp.services;

import com.example.socksapp.model.Socks;
import com.example.socksapp.model.SocksColor;
import com.example.socksapp.model.SocksSize;
import com.example.socksapp.repository.SocksRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class SocksServiceImpl implements SocksService{

    private final SocksRepositoryImpl socksRepository;
    private final ValidationService validationService;

    @Override
    public Map<Socks, Integer> getSocksLeftover(SocksColor color, SocksSize size,
                                                Integer cottonPartMin, Integer cottonPartMax) {
        if (!validationService.isValid(color, size, cottonPartMin, cottonPartMax)) {
            throw new IllegalArgumentException();
        }
        Map<Socks, Integer> outMap = new HashMap<>();
        for (Socks socks : socksRepository.getSocksMap().keySet()) {
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && isInRange(socks.getCottonPart(), cottonPartMin, cottonPartMax)) {
                outMap.put(socks, socksRepository.getSocksMap().get(socks));
            }
        }
        return outMap;
    }

    @Override
    public boolean isInRange(Integer value, Integer min, Integer max) {
        return value >= min && value <= max;
    }
}




