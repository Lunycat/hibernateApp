package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    @Cascade({CascadeType.PERSIST})
    private List<Item> items;

    @OneToOne(mappedBy = "person")
    @Cascade({CascadeType.PERSIST})
    private Passport passport;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        item.setOwner(this);
    }

    public void addPassport(Passport passport) {
        this.passport = passport;
        passport.setPerson(this);
    }

    @Override
    public String toString() {
        return String.format("id = %s\nname=%s\nage = %s\n", id, name, age);
    }
}
