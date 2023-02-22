package com.example.socksapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {

    private SocksColor color;
    private SocksSize size;
    private Integer cottonPart;



}
