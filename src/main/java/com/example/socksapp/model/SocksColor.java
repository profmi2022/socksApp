package com.example.socksapp.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocksColor {

    WHITE("белый"),
    BLACK("черный"),
    RED("красный"),
    YELLOW("желтый"),
    PINK("розовый"),
    GRAY("серый"),
    GREEN("зеленый"),
    BLUE("синий"),
    BROWN("коричневый");
    final String name;
}
