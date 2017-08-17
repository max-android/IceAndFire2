package exampleprilognew.ru.iceandfire2.model;

import java.util.Comparator;




public class MyComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {


        return book2.getReleased().compareToIgnoreCase(book1.getReleased());
    }
}
