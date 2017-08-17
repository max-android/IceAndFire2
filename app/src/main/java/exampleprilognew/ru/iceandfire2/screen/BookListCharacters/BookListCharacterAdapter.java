package exampleprilognew.ru.iceandfire2.screen.BookListCharacters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import exampleprilognew.ru.iceandfire2.R;
import exampleprilognew.ru.iceandfire2.model.Character;

/**
 * Created by Максим on 11.08.2017.
 */

public class BookListCharacterAdapter extends RecyclerView.Adapter<BookListCharacterAdapter.CharacterViewHolder>  {

    private final List<Character> list;

    public BookListCharacterAdapter(List<Character> list) {
        this.list = list;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.list_characters,parent,false);

        return  new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {

        Character character= list.get(position);

        holder.bindTo(character);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvNameCharacter;
        private final TextView tvGender;

        private final TextView tvDignity;


        public CharacterViewHolder(View itemView){
            super(itemView);

            Typeface typeBody =
                    Typeface.createFromAsset(itemView.getResources().getAssets(),"RobotoTTF/Roboto-Medium.ttf");

            tvNameCharacter=(TextView)itemView.findViewById(R.id.tvNameCharacter);
            tvNameCharacter.setTypeface(typeBody);
            tvGender =(TextView)itemView.findViewById(R.id.tvGender);
            tvGender.setTypeface(typeBody);
            tvDignity =(TextView)itemView.findViewById(R.id.tvDignity);
            tvDignity.setTypeface(typeBody);
        }

        public void bindTo(Character character) {

            tvNameCharacter.setText(character.getNameHerou());
            tvGender.setText(character.getGender());
            tvDignity.setText(character.getTitle());

        }
    }
}

