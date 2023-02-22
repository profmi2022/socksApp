package com.example.socksapp.repository;

import com.example.socksapp.model.Socks;

public interface SocksRepository {

    /**
     * добавляет в коллекцию партию носков
     * @param socks уникальный тип носков
     * @param quantity количество пар носков
     * @return возвращает количество носков на складе после операции
     */
    Integer save (Socks socks, Integer quantity);

    /**
     * удаляет из коллекции партию носков
     * @param socks уникальный тип носков
     * @param quantity количество пар носков
     * @return количество пар носков, фактически выданное со склада
     */
    Integer remove (Socks socks, Integer quantity);

}
