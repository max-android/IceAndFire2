package exampleprilognew.ru.iceandfire2.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import exampleprilognew.ru.iceandfire2.model.Book;

public class DatabaseManager {

    private SQLiteDatabase sqLiteDatabase;


    public DatabaseManager(Context context) {
        DBHelper dbHelper=new DBHelper(context);
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public Cursor getCursor(){
        Cursor cursor=sqLiteDatabase.query(DBHelper.BOOKS,null,null,null,null,null,null);
    return  cursor;
    }


    public  List<Book> getDataFromBD(Cursor cursor){

        List<Book> bookList=new  ArrayList<Book>();

        if(cursor!=null){

            if(cursor.moveToFirst()){

                int indexName = cursor.getColumnIndex("name");
                int indexNumberOfPages = cursor.getColumnIndex("numberOfPages");
                int indexReleased = cursor.getColumnIndex("released");
                int indexCharacters = cursor.getColumnIndex("characters");

                int indexListCharacters = cursor.getColumnIndex("listCharacters");
                int indexAuthors = cursor.getColumnIndex("authors");
                int indexPublisher = cursor.getColumnIndex("publisher");
                int indexCountry = cursor.getColumnIndex("country");
                int indexMediaType = cursor.getColumnIndex("mediaType");


                do{
                    bookList.add(new Book(
                            cursor.getString(indexName),
                            cursor.getInt(indexNumberOfPages),
                            cursor.getString(indexReleased),
                            cursor.getInt(indexCharacters),

                            cursor.getString(indexListCharacters),
                            cursor.getString(indexAuthors),
                            cursor.getString(indexPublisher),
                            cursor.getString(indexCountry),
                            cursor.getString(indexMediaType)
                    ));
                }while(cursor.moveToNext());
            }
        }
        return  bookList;
    }

   public void writeDataIntoBD(List<Book> bookList){
       ContentValues contentValues=new ContentValues();

       for(Book book:bookList){
           contentValues.put(DBHelper.KEY_NAME,book.getName());
           contentValues.put(DBHelper.KEY_NUMBER_OF_PAGES,book.getNumberOfPages());
           contentValues.put(DBHelper.KEY_RELEASED,book.getReleased());
           contentValues.put(DBHelper.KEY_CHARACTERS,book.getCharacters());

           contentValues.put(DBHelper.KEY_LISTCHARACTERS,book.getCharactersList());
           contentValues.put(DBHelper.KEY_AUTHORS,book.getAuthors());
           contentValues.put(DBHelper.KEY_PUBLISHER,book.getPublisher());
           contentValues.put(DBHelper.KEY_COUNTRY,book.getCountry());
           contentValues.put(DBHelper.KEY_MEDIATYPE,book.getMediaType());

           sqLiteDatabase.insert(DBHelper.BOOKS,null,contentValues);
       }

   }

   public void deleteBD(){

       sqLiteDatabase.delete(DBHelper.BOOKS,null,null);

   }

}



