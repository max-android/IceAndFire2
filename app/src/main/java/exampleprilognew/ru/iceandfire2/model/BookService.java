package exampleprilognew.ru.iceandfire2.model;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.screen.BooksList.BookAdapter;
import exampleprilognew.ru.iceandfire2.screen.BooksList.BookCallBack;


public class BookService extends AsyncTask<URL,String,String> {


    private Context context;

    private BookCallBack bookCallBack;

    public static final String TITLEBOOK = "Downloading books";
    public static final int NUMBERBOOKS = 10;

    public BookService(Context context, BookCallBack bookCallBack) {

        this.context = context;

        this.bookCallBack=bookCallBack;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        bookCallBack.getProgressDialog().showDialog(TITLEBOOK,NUMBERBOOKS);

    }

    @Override
    protected String doInBackground(URL... params) {

        return getDateFromService(params);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        bookCallBack.getProgressDialog().showProgress(values);


    }

    @Override
    protected void onPostExecute(String jsonStr) {
        super.onPostExecute(jsonStr);

        if (!isCancelled()) {

            bookCallBack.getRecyclerView().setAdapter(new BookAdapter(convertJsonStringtoList(jsonStr),bookCallBack.getBF()));
            bookCallBack.getProgressDialog().dismissDialog();


            bookCallBack.getDB().writeDataIntoBD(convertJsonStringtoList(jsonStr));
        }

    }

    private List<Book> convertJsonStringtoList(String jsonStr) {

        List<Book> listBook=new ArrayList<>();

        try {

            JSONArray  list = new JSONArray(jsonStr);

            for (int i = 0; i <list.length(); i++) {

                JSONObject book=list.getJSONObject(i);

              int massLenth = book.getJSONArray("characters").length();

                JSONArray jsonArrayCharacters=book.getJSONArray("characters");
                JSONArray jsonArrayAuthors=book.getJSONArray("authors");

                listBook.add(new Book(
                        book.getString("name"),
                        book.getInt("numberOfPages"),convertDate(book.getString("released")),
                        massLenth,
                        (convertListToStrForDescriptionBook(jsonArrayCharacters)).substring(1, convertListToStrForDescriptionBook(jsonArrayCharacters).length()-1),
                        (convertListToStrForDescriptionBook(jsonArrayAuthors)).substring(1, convertListToStrForDescriptionBook(jsonArrayAuthors).length()-1),
                        book.getString("publisher"),
                        book.getString("country"),
                        book.getString("mediaType")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
          return   listBook;
    }

    //может быть использован при необходимости сортировки по дате от новой к старой
    //по умолчанию данные попадают в список из json по порядку увеличения даты
    private List<Book> sortDate(List<Book> list){

        Collections.sort(list,new MyComparator());

        return list;
    }

    private String getDateFromService(URL...urls) {
        HttpURLConnection connection = null;
        StringBuilder stringBuilder = null;
        try {
            connection = (HttpURLConnection) urls[0].openConnection();

            int responce = connection.getResponseCode();

            if(responce==HttpURLConnection.HTTP_OK) {
                try {
                    stringBuilder=new StringBuilder();

                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(connection.getInputStream()));

                    String line;

                    while((line=reader.readLine())!=null) {

                        stringBuilder.append(line);
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }

                if (isCancelled()) {

                    return null;

                }
                publishProgress(stringBuilder.toString());

                return stringBuilder.toString();

            }else{
                Toast.makeText(context,context.getResources().getString(R.string.request_error),Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;

    }

    private String convertListToStrForDescriptionBook(JSONArray jsonArray){

        List<String> arrayCharacters = new ArrayList<>();

        for (int j=0;j<jsonArray.length();j++){

            try {
                arrayCharacters.add(jsonArray.getString(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayCharacters.toString();
    }

    private String convertDate(String str){
        String oldStr=str.substring(0,10).replace('-','/');
        char zeroStr=oldStr.charAt(8);
        char oneStr=oldStr.charAt(9);
        char twoStr=oldStr.charAt(7);
        char threeStr=oldStr.charAt(5);
        char fourStr=oldStr.charAt(6);
        char fiveStr=oldStr.charAt(4);
        char sixStr=oldStr.charAt(0);
        char sevenStr=oldStr.charAt(1);
        char eightStr=oldStr.charAt(2);
        char nineStr=oldStr.charAt(3);
        String newStr= new StringBuffer().append(zeroStr).append(oneStr).append(twoStr).append(threeStr).append(fourStr).append(fiveStr).append(sixStr).append(sevenStr).append(eightStr).append(nineStr).toString();
        return newStr;
    }
}
