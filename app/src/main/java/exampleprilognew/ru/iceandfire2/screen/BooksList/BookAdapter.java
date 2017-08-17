package exampleprilognew.ru.iceandfire2.screen.BooksList;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.model.Book;

/**
 * Created by Максим on 23.07.2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>  {
    private final List<Book> list;
    private final BookClickListener listener;


    public BookAdapter(List<Book> list,BookClickListener listener) {
        this.list = list;
        this.listener=listener;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.list_book,parent,false);

        return  new BookViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        Book book= list.get(position);

        holder.bindTo(book);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvName;
        private final TextView tvPage;
        private final TextView tvCharacter;
        private final TextView tvRelease;
        private Book book;

        public BookViewHolder(View itemView,final BookClickListener listener){
            super(itemView);

            Typeface typeSubHead =
                    Typeface.createFromAsset(itemView.getResources().getAssets(),"RobotoTTF/Roboto-Regular.ttf");
            Typeface typeBody =
                    Typeface.createFromAsset(itemView.getResources().getAssets(),"RobotoTTF/Roboto-Medium.ttf");

            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvName.setTypeface(typeSubHead);
            tvPage =(TextView)itemView.findViewById(R.id.tvPage);
            tvPage.setTypeface(typeBody);
            tvCharacter =(TextView)itemView.findViewById(R.id.tvCharacter);
            tvCharacter.setTypeface(typeBody);
            tvRelease =(TextView)itemView.findViewById(R.id.tvRelease);
            tvRelease.setTypeface(typeBody);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  listener.onBookClick(book);
                }
            });
        }

        public void bindTo(Book book) {
              this.book=book;

            tvName.setText(book.getName());
            tvPage.setText(String.valueOf(book.getNumberOfPages()));
            tvCharacter.setText(String.valueOf(book.getCharacters()));
            tvRelease.setText(book.getReleased());
        }
    }
    public interface BookClickListener{

        void onBookClick(Book book);
    }

}


