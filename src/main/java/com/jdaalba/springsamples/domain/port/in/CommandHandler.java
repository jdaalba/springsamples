package com.example.demo.domain.port.in;

import com.example.demo.domain.cqrs.Command;

public interface CommandHandler<C extends Command, R> {

    R accept(C command);
}
