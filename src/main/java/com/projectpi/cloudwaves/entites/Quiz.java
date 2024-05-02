package com.projectpi.cloudwaves.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Quiz {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;

        @ManyToMany(cascade =CascadeType.PERSIST, fetch = FetchType.EAGER)
        private List<Questions> questions;
}
