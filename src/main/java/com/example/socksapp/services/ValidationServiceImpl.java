package com.example.socksapp.services;

import com.example.socksapp.model.Socks;
import com.example.socksapp.model.SocksColor;
import com.example.socksapp.model.SocksSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class ValidationServiceImpl implements ValidationService{

    @Override
    public boolean isValid(Socks socks, Integer quantity) {

        return socks != null
                && quantity != null
                && quantity > 0
                && socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100
                && socks.getSize() != null
                && socks.getColor() != null;
    }

    @Override
    public boolean isValid(SocksColor color, SocksSize size, Integer cottonPartMin, Integer cottonPartMax) {

        return cottonPartMin != null
                && cottonPartMax != null
                && cottonPartMin >= 0 && cottonPartMin <= 100
                && cottonPartMax >= 0 && cottonPartMax <= 100
                && cottonPartMin <= cottonPartMax;
    }
}
