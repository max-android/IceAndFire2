package exampleprilognew.ru.iceandfire2.model;

import android.content.Context;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import exampleprilognew.ru.iceandfire2.screen.BookListCharacters.BookListCharacterAdapter;
import exampleprilognew.ru.iceandfire2.screen.BookListCharacters.BookListCharactersCallBack;

/**
 * Created by Максим on 13.08.2017.
 */

public class BookCharctersService extends AsyncTask<Void, String , List<String>>  {



    private Context context;
    private BookListCharactersCallBack bookListCharacterCallBack;
    public static final String TITLECHARACTER = "Downloading characters";


    public BookCharctersService(Context context, BookListCharactersCallBack bookListCharacterCallBack) {
        this.context = context;
        this.bookListCharacterCallBack = bookListCharacterCallBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

     int numberCharacters = bookListCharacterCallBack.getNumberOfcharacters();

        bookListCharacterCallBack.getProgressDialog().showDialog(TITLECHARACTER,numberCharacters);
    }

    @Override
    protected List<String>  doInBackground(Void... params) {
        return getDateFromService();

    }

    private List<String>  getDateFromService() {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        List<String> jsonStrList=new ArrayList<>();

        for (String urlStr : bookListCharacterCallBack.getUrlCharacters()) {
            try {
                URL url = new URL(urlStr.replace(",",""));

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            jsonStrList.add(resultJson);

        }

        if (isCancelled()) {

            return null;
        }

        publishProgress(jsonStrList.toString());

        return  jsonStrList;
    }



    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        bookListCharacterCallBack.getProgressDialog().showProgress(values);

    }

    @Override
    protected void onPostExecute(List<String> jsonStrList) {
        super.onPostExecute(jsonStrList);

        if (!isCancelled()) {

            List<Character> characterArrayList = new ArrayList<>();

            for (String jsonStr : jsonStrList) {

                characterArrayList.add(createCharacter(jsonStr));
            }

            bookListCharacterCallBack.getProgressDialog().dismissDialog();

            bookListCharacterCallBack.getRecyclerView().setAdapter(new BookListCharacterAdapter(characterArrayList));


        }
    }

    private Character createCharacter(String jsonStr) {

        Character charact = null;

        try {
            JSONObject character = new JSONObject(jsonStr);

            JSONArray jsonArrayDignity = character.getJSONArray("titles");

            charact = new Character(character.getString("name"),
                    character.getString("gender"),
                    (createDignity(jsonArrayDignity)).substring(1, (createDignity(jsonArrayDignity)).length() - 1)
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return charact;
    }

    private String createDignity(JSONArray jsonArray) {

        List<String> arrayCharacters = new ArrayList<>();

        for (int j = 0; j < jsonArray.length(); j++) {

            try {
                arrayCharacters.add(jsonArray.getString(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayCharacters.toString();
    }
}
