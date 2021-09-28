package kr.ac.daegu.springbootapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor //testDTO가 가지고있는 모든 Constructor 만들어라 (lombok)
@Getter
@Setter

public class TestDTO {
    private int num;
    private String name;
}
