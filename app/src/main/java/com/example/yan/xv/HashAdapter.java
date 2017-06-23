package com.example.yan.xv;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yan on 27/02/2016.
 */
public class HashAdapter extends RecyclerView.Adapter<HashAdapter.ViewHolder> {

    private List<HashItem> hashItemList;
    private ArrayList<String> xItems;
    private ArrayList<String> oItems;
    private String gameStatus = "";
    private TextView statusTextView;
    ViewGroup group;
    int counter;

    public HashAdapter(HashGame hashGame) {
        hashItemList = hashGame.getItems();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        Resources resources = context.getResources();
        group = parent;

        oItems = new ArrayList<String>();
        xItems = new ArrayList<String>();

        if (gameStatus.equals("")) {
            int randomInt = (int) (Math.random() * 2);
            if (randomInt == 1) {
                gameStatus = resources.getString(R.string.x_turns);
            } else {
                gameStatus = resources.getString(R.string.o_turns);
            }
        }

        if (counter == 0) {
            View contactView = inflater.inflate(R.layout.header, parent, false);

            ViewHolder viewHolder = new ViewHolder(contactView, 0, this);
            counter++;
            return viewHolder;
        } else {
            View contactView = inflater.inflate(R.layout.list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(contactView, 1, this);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashItem item = hashItemList.get(position);
        ImageButton button = holder.hashButton;
        TextView status = holder.status;

        if (status == null) {
            status = statusTextView;
        }

        if (item.getPosition() < 1) {
            status.setText(gameStatus);
        } else
            button.setTag(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return hashItemList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        static TextView status;
        public ImageButton hashButton;
        public HashItem item;
        ArrayList<String> winList1 = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        ArrayList<String> winList2 = new ArrayList<String>(Arrays.asList("1", "4", "7"));
        ArrayList<String> winList3 = new ArrayList<String>(Arrays.asList("1", "5", "9"));
        ArrayList<String> winList4 = new ArrayList<String>(Arrays.asList("3", "5", "7"));
        ArrayList<String> winList5 = new ArrayList<String>(Arrays.asList("4", "5", "6"));
        ArrayList<String> winList6 = new ArrayList<String>(Arrays.asList("7", "8", "9"));
        ArrayList<String> winList7 = new ArrayList<String>(Arrays.asList("8", "5", "2"));
        ArrayList<String> winList8 = new ArrayList<String>(Arrays.asList("9", "6", "3"));

        public boolean isWinner(ArrayList<String> game) {
            if (game.containsAll(winList1)) {
                return true;
            }
            if (game.containsAll(winList2)) {
                return true;
            }
            if (game.containsAll(winList3)) {
                return true;
            }
            if (game.containsAll(winList4)) {
                return true;
            }
            if (game.containsAll(winList5)) {
                return true;
            }
            if (game.containsAll(winList6)) {
                return true;
            }
            if (game.containsAll(winList7)) {
                return true;
            }
            if (game.containsAll(winList8)) {
                return true;
            }
            return false;
        }

        public ViewHolder(final View itemView, int viewType, final HashAdapter adapter) {

            super(itemView);
            status = (TextView) itemView.findViewById(R.id.game_status);

            if (status == null) {
                status = adapter.statusTextView;
            } else {
                adapter.statusTextView = status;
            }

            if (viewType == 1) {
                hashButton = (ImageButton) itemView.findViewById(R.id.button);
                hashButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Resources resources = v.getContext().getResources();
                        hashButton.setEnabled(false);
                        boolean over = false;

                        if (adapter.gameStatus.equals(resources.getString(R.string.o_turns))) {
                            hashButton.setImageResource(R.mipmap.image_test);
                            adapter.oItems.add(String.valueOf(v.getTag()));

                            if (isWinner(adapter.oItems)) {
                                adapter.statusTextView.setText(resources.getString(R.string.o_wins));
                                over = true;
                            } else {
                                adapter.gameStatus = resources.getString(R.string.x_turns);
                            }
                        } else {
                            hashButton.setImageResource(R.mipmap.image_test2);
                            adapter.xItems.add(String.valueOf(v.getTag()));
                            if (isWinner(adapter.xItems)) {
                                adapter.statusTextView.setText(resources.getString(R.string.x_wins));
                                over = true;
                            } else {
                                adapter.gameStatus = resources.getString(R.string.o_turns);
                            }
                        }

                        if (((adapter.xItems.size() + adapter.oItems.size()) == 9) && (over == false)) {
                            adapter.statusTextView.setText(resources.getString(R.string.tied));
                            over = true;
                        }

                        if (over == false) {
                            adapter.statusTextView.setText(adapter.gameStatus);
                        } else {
                            for (int i = 1; i < 10; i++) {
                                ImageButton disableButton = (ImageButton) adapter.group.findViewWithTag(String.valueOf(i));
                                disableButton.setEnabled(false);
                            }
                        }
                    }
                });
            }
        }
    }
}