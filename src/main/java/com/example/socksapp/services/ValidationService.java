package com.example.socksapp.services;

import com.example.socksapp.model.Socks;
import com.example.socksapp.model.SocksColor;
import com.example.socksapp.model.SocksSize;

public interface ValidationService {

    /**
     * Проверка параметров на корректность.
     */
    boolean isValid (Socks socks, Integer quantity);

    /**
     * Проверка параметров на корректность.
     */
    boolean isValid (SocksColor color, SocksSize size, Integer cottonPartMin, Integer cottonPartMax);

}
