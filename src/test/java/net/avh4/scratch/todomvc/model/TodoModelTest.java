package net.avh4.scratch.todomvc.model;

import net.avh4.scratch.todomvc.model.event.AddedTodo;
import net.avh4.test.otto.TestBus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class TodoModelTest {

    private TodoModel subject;
    private TestBus bus;

    @Before
    public void setUp() throws Exception {
        bus = new TestBus();
        subject = new TodoModel(bus);
    }

    @Test
    public void shouldStartEmpty() throws Exception {
        assertThat(subject.getTodos().size(), is(0));
    }

    @Test
    public void testAddingATodo() throws Exception {
        subject.addTodo("Eat cake");
        assertThat(subject.getTodos(), contains(new Todo("Eat cake")));
    }

    @Test
    public void add_shouldPostEvent() throws Exception {
        subject.addTodo("Eat carrots");
        bus.verify(new AddedTodo(new Todo("Eat carrots")));
    }
}