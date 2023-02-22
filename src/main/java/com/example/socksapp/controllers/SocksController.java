package com.example.socksapp.controllers;

import com.example.socksapp.model.Socks;
import com.example.socksapp.model.SocksColor;
import com.example.socksapp.model.SocksSize;
import com.example.socksapp.repository.SocksRepositoryImpl;
import com.example.socksapp.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Tag(name = "Контроллер приложения учета товаров на складе интернет-магазина носков")

public class SocksController {

    private final SocksRepositoryImpl socksRepository;
    private final SocksService socksService;

    @Operation(summary = "Регистрирует приход носков на склад")
    @PostMapping
    @ApiResponse(responseCode = "200", description = "Товар добавлен на склад")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> income (@RequestParam @Parameter(description = "Цвет")SocksColor color,
                                     @RequestParam @Parameter(description = "Размер")SocksSize size,
                                     @RequestParam @Parameter(description = "% хлопка в составе")Integer cottonPart,
                                     @RequestParam @Parameter(description = "Количество")Integer quantity){
        try{
          return ResponseEntity.ok(socksRepository.save(new Socks(color, size, cottonPart), quantity));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Регистрирует отпуск носков со склада")
    @PutMapping
    @ApiResponse(responseCode = "200", description = "Отпуск носков со склада произведен")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> outcome (@RequestParam @Parameter(description = "Цвет")SocksColor color,
                                      @RequestParam @Parameter(description = "Размер")SocksSize size,
                                      @RequestParam @Parameter(description = "% хлопка в составе")Integer cottonPart,
                                      @RequestParam @Parameter(description = "Количество")Integer quantity){
        try{
            return ResponseEntity.ok(socksRepository.remove(new Socks(color, size, cottonPart), quantity));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> getSocksLeftover (@RequestParam @Parameter(description = "Цвет")SocksColor color,
                                      @RequestParam @Parameter(description = "Размер")SocksSize size,
                                      @RequestParam @Parameter(description = "% хлопка мин.")Integer cottonPartMin,
                                      @RequestParam @Parameter(description = "% хлопка макс.")Integer cottonPartMax){
        try{
            return ResponseEntity.ok(socksService.getSocksLeftover(color, size, cottonPartMin, cottonPartMax));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @DeleteMapping
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> reject (@RequestParam @Parameter(description = "Цвет")SocksColor color,
                                      @RequestParam @Parameter(description = "Размер")SocksSize size,
                                      @RequestParam @Parameter(description = "% хлопка в составе")Integer cottonPart,
                                      @RequestParam @Parameter(description = "Количество")Integer quantity){
        try{
            return ResponseEntity.ok(socksRepository.remove(new Socks(color, size, cottonPart), quantity));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Возвращает все позиции на складе")
    @GetMapping("/leftover")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> getAllSocksLeftover (){
        try{
            return ResponseEntity.ok(socksRepository.getSocksMap());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
