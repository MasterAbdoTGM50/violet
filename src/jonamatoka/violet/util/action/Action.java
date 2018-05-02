package jonamatoka.violet.util.action;

import javax.persistence.*;

@MappedSuperclass
@DiscriminatorColumn(name = "type")
public abstract class Action<T> {

    @Id
    @GeneratedValue
    protected long id;

    @Enumerated(EnumType.STRING)
    protected State state = State.UNEXECUTED;

    public abstract void exec(T actionee);

    public abstract void unexec(T actionee);

    public long getId() { return id; }

    public Action<T> setId(long id) { this.id = id; return this; }

    public State getState() { return state; }

    public Action<T> setState(State state) { this.state = state; return this; }

    public enum State {

        EXECUTED,
        UNEXECUTED

    }

}
