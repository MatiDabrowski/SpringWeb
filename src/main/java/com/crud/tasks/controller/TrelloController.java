package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.stream()
//                .filter(trelloBoardDto -> trelloBoardDto.getId() != null)
//                .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
//                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
//                .collect(Collectors.toList())
//                .forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//        });
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
//            System.out.println("This board contains lists: ");
//            trelloBoardDto.getLists().forEach(trelloList -> {
//                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
//            });
//        });
        return trelloClient.getTrelloBoards();
    }
    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
