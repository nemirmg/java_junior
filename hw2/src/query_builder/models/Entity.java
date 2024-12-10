package query_builder.models;

import java.util.UUID;

import query_builder.Column;

@query_builder.Entity
public class Entity {
    @Column(name = "id", primaryKey = true)
    private UUID id;
}
