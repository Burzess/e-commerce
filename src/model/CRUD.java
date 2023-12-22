package model;

public interface CRUD<T> {
    void create(T data);
    T read(int id);
    void update(T data);
    void delete(int id);
}