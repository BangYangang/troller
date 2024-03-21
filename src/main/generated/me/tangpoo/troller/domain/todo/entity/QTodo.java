package me.tangpoo.troller.domain.todo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = -1831557463L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final me.tangpoo.troller.domain.board.entity.QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath todoName = createString("todoName");

    public final StringPath todoOrder = createString("todoOrder");

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new me.tangpoo.troller.domain.board.entity.QBoard(forProperty("board")) : null;
    }

}

