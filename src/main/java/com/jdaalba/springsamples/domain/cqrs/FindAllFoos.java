package com.jdaalba.springsamples.domain.cqrs;

import com.jdaalba.springsamples.domain.Foo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindAllFoos implements Query<Foo> {

    public static FindAllFoos INSTANCE = new FindAllFoos();
}
