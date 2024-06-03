package com.jdaalba.springsamples.domain.port.in;

import com.jdaalba.springsamples.domain.cqrs.Command;

public interface CommandHandler<C extends Command, R> {

    R accept(C command);
}
