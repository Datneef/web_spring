package com.spring.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private int id;
    private String name;
    private String username;
    private String password;
    private String role;
}
