package ru.job4j.di;

public class StartUI {

    private Store store;

    private ConsoleInput input = new ConsoleInput();

    public StartUI(Store store) {
        this.store = store;
    }

    public void add() {
        store.add(input.askStr());
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
