package com.example.socksapp.services;

import com.example.socksapp.model.Socks;
import com.example.socksapp.model.SocksColor;
import com.example.socksapp.model.SocksSize;

import java.util.Map;

public interface SocksService {

    /**
     * Возвращает коллекцию, в которой содержатся все остатки на складе по условиям поиска.
     */
    Map<Socks, Integer> getSocksLeftover(SocksColor color, SocksSize size,
                                                Integer cottonPartMin, Integer cottonPartMax);

    /**
     * Проверяет, входит ли параметр в заданный диапазон.
     */
    boolean isInRange (Integer value, Integer min, Integer max);


}
