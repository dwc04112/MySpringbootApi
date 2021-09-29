package kr.ac.daegu.springbootapi.board.controller;

import lombok.Builder;

import java.util.List;

public class ApiResponse<T> {
    private String status;
    private List<T> data;

    @Builder
    public ApiResponse(String status, List<T> data) {
        this.status = status;
        this.data = data;
    }
}