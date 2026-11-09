package com.example.springbootproject.dto;

public record BookSearchParametersDto(String[] title, String[] author, String[] isbn) {
}
