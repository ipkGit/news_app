package com.example.news_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "origin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<News> news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Origin origin = (Origin) o;
        return id != null && Objects.equals(id, origin.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}