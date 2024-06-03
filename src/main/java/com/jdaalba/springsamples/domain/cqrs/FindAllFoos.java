package com.example.demo.domain.cqrs;

import com.example.demo.domain.Foo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindAllFoos implements Query<Foo> {

    public static FindAllFoos INSTANCE = new FindAllFoos();
}
