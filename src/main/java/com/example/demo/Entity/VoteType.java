package com.example.demo.Entity;

import java.util.Arrays;

import com.example.demo.Exceptions.BlogPortException;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new BlogPortException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}