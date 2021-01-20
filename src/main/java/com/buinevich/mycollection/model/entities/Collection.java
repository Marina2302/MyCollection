package com.buinevich.mycollection.model.entities;

import com.buinevich.mycollection.model.enums.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "theme")
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @ManyToMany(mappedBy = "collections", fetch = FetchType.LAZY)
    private List<Tag> tags;

    @ManyToMany(mappedBy = "collections", fetch = FetchType.LAZY)
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;
}
