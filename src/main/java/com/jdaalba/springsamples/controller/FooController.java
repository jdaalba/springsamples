package com.example.demo.controller;

import com.example.demo.domain.Foo;
import com.example.demo.domain.cqrs.CreateFoo;
import com.example.demo.domain.cqrs.FindAllFoos;
import com.example.demo.domain.cqrs.FindFoo;
import com.example.demo.domain.cqrs.FindFooByBar;
import com.example.demo.domain.port.in.CommandHandler;
import com.example.demo.domain.port.in.QueryHandler;
import com.example.demo.domain.port.in.QueryManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/foos")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FooController {

    private final CommandHandler<CreateFoo, UUID> handler;

    private final QueryManager queryManager;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateFooDTO createFoo) {
        log.info("Saving foo {}", createFoo);
        UUID uuid = handler.accept(new CreateFoo(createFoo.bar()));
        return ResponseEntity.created(URI.create("http://localhost/foos/" + uuid)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FooDTO> findByUuid(@PathVariable("id") UUID uuid) {
        log.info("Looking for foo wth id {}", uuid);
        Optional<Foo> one = queryManager.findOne(new FindFoo(uuid));
        return ResponseEntity.of(one.map(FooDTO::from));
    }

    @GetMapping
    public List<FooDTO> findAll() {
        log.info("Looking for all foos");
        List<Foo> all = queryManager.findAll(FindAllFoos.INSTANCE);
        return all.stream().map(FooDTO::from).toList();
    }

    @GetMapping("/like")
    public ResponseEntity<FooDTO> findByBar(@RequestParam("bar") String bar) {
        log.info("Looking for foo with bar {}", bar);
        Optional<Foo> one = queryManager.findOne(new FindFooByBar(bar));
        return ResponseEntity.of(one.map(FooDTO::from));
    }

    public record CreateFooDTO(String bar) {
    }

    public record FooDTO(UUID uuid, String bar) {
        public static FooDTO from(Foo foo) {
            return new FooDTO(foo.uuid(), foo.bar());
        }
    }


}
