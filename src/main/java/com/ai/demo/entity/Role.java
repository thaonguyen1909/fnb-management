package com.ai.demo.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true, length = 50)
    String name;

    @Type(JsonBinaryType.class)
    @Column(name = "permissions", columnDefinition = "jsonb")
    Map<String, List<String>> permissions;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<User> users;
}
