package ru.netology.javaqa;

public class NotFoundException extends RuntimeException { //Создал класс исключения NotFoundException, отнаследовавшись от RuntimeException

    public NotFoundException(String s) { //реализовал  конструктор с параметром-сообщением. Он будет просто вызывать суперконструктор предка
        super(s);
    }
}
